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
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.text.ParseException;

//import org.json.simple.parser.ParseException;

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

    /**
     * Modifica un cliente existente
     * @param idCliente
     * @param nombre
     * @param apellido
     * @param provincia
     * @param canton
     * @param distrito
     * @param telefono
     * @param email
     * @param fechaDeNacimiento
     */
    public int largo(String numero){
        int cont=0;
        for (int i = 0; i < numero.length(); i++) {
            if (Character.isDigit(numero.charAt(i))) {
                cont++;
            }
        }
        return cont;
    }
    public String obtenerNumero(String numero){
        String cont="";
        for (int i = 0; i < numero.length(); i++) {
            if (Character.isDigit(numero.charAt(i))) {
                cont+=numero.charAt(i);
            }
        }
        return cont;
    }
    public boolean telefonoValido(String num){
        int primerDigito = Character.getNumericValue(num.charAt(0));
        return largo(num)==8 && (primerDigito==2 || primerDigito==4 || primerDigito==6 || primerDigito==8);
    }
    public boolean correoValido(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean fechaValida(String fecha) {
        String fechaRegex = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern pattern = Pattern.compile(fechaRegex);
        Matcher matcher = pattern.matcher(fecha);
        if (!matcher.matches()) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); 
        try {
            dateFormat.parse(fecha);
            return true; 
        } catch (ParseException e) {
            return false; 
        }
    }
    public void modificarCliente(int idCliente, String nombre, String apellido, String provincia, String canton, String distrito, String telefono, String email, String fechaDeNacimiento){
        if(telefonoValido(telefono)){
            if(correoValido(email)){
                for(int i = 0; i < clientes.size(); i++){
                    if(clientes.get(i).getIdCliente() == idCliente){
                        clientes.get(i).setNombre(nombre);
                        clientes.get(i).setApellido(apellido);
                        clientes.get(i).setProvincia(provincia);
                        clientes.get(i).setCanton(canton);
                        clientes.get(i).setDistrito(distrito);
                        clientes.get(i).setTelefono(telefono);
                        clientes.get(i).setEmail(email);
                        clientes.get(i).setFechaDeNacimiento(fechaDeNacimiento);
                        guardarDatos();
                        break;
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Debe ingresar un correo electronico valido.", "Error.", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe ingresar un numero telefonico valido.", "Error.", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Elimina un cliente existente
     * @param idCliente
     */
    public void eliminarCliente(int idCliente){
        for(int i = 0; i < clientes.size(); i++){
            if(clientes.get(i).getIdCliente() == idCliente){
                clientes.remove(i);
                guardarDatos();
                break;
            }
        }
    }
}
