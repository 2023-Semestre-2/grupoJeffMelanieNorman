/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupojeffmelanienorman;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Jeffry
 */
public class ManejoDeCliente {
    // Atributos
    private ArrayList<Cliente> clientes;
    private final String fileName = "grupoJeffMelanieNorman/Clientes.json";

    // Constructor
    public ManejoDeCliente() {
        this.cargarDatos();
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    private void cargarDatos(){
        JSONParser parser = new JSONParser();
        try{
            FileReader reader = new FileReader(fileName);
            Object obj = parser.parse(reader);

            JSONArray listaClientes = (JSONArray) obj;
            for(int i = 0; i < listaClientes.size(); i++){
                JSONObject cliente = (JSONObject) listaClientes.get(i);
                int idCliente = Integer.parseInt(cliente.get("idCliente").toString());
                String nombre = cliente.get("nombre").toString();
                String apellido = cliente.get("apellido").toString();
                String provincia = cliente.get("provincia").toString();
                String canton = cliente.get("canton").toString();
                String distrito = cliente.get("distrito").toString();
                String telefono = cliente.get("telefono").toString();
                String email = cliente.get("email").toString();
                String fechaDeNacimiento = cliente.get("fechaDeNacimiento").toString();
                Cliente nuevoCliente = new Cliente(idCliente, nombre, apellido, provincia, canton, distrito, telefono, email, fechaDeNacimiento);
                clientes.add(nuevoCliente);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void guardarDatos(){
        JSONArray listaClientes = new JSONArray();
        for(int i = 0; i < clientes.size(); i++){
            JSONObject cliente = new JSONObject();
            cliente.put("idCliente", clientes.get(i).getIdCliente());
            cliente.put("nombre", clientes.get(i).getNombre());
            cliente.put("apellido", clientes.get(i).getApellido());
            cliente.put("provincia", clientes.get(i).getProvincia());
            cliente.put("canton", clientes.get(i).getCanton());
            cliente.put("distrito", clientes.get(i).getDistrito());
            cliente.put("telefono", clientes.get(i).getTelefono());
            cliente.put("email", clientes.get(i).getEmail());
            cliente.put("fechaDeNacimiento", clientes.get(i).getFechaDeNacimiento());
            listaClientes.add(cliente);
        }
        try{
            FileWriter file = new FileWriter(fileName);
            file.write(listaClientes.toJSONString());
            file.flush();
            file.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    
}
