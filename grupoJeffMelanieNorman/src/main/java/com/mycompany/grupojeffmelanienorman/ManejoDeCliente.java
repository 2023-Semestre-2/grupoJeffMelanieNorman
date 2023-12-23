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
 * ManejoDeCliente
 * 
 * Esta clase se encarga de manejar los clientes
 *
 * @author Jeffry
 */
public class ManejoDeCliente {
    // Atributos
    private ArrayList<Cliente> clientes;
    private final String fileName = "Clientes.json";
    private int ultimoCodigo;
    
    /**
     * Constructor para la clase ManejoDeCliente.
     * Inicializa la lista de clientes leyendo el archivo JSON.
     */
    public ManejoDeCliente() {
        clientes=new ArrayList<Cliente>();
        cargarDatos();
    }

    /**
     * Crea un nuevo cliente
     * @param idCliente identificador del cliente
     * @param nombre nombre del cliente
     * @param apellido apellido del cliente
     * @param provincia provincia del cliente
     * @param canton canton del cliente
     * @param distrito distrito del cliente
     * @param telefono telefono del cliente
     * @param email email del cliente
     * @param fechaDeNacimiento fecha de nacimiento del cliente
     */
    private void actualizarUltimoCodigo() {
        if (clientes!=null && !clientes.isEmpty()) {
            Cliente ultimoCliente = (Cliente) clientes.get(clientes.size() - 1);
            ultimoCodigo = ultimoCliente.getIdCliente();
        }
    }
    
    /** 
     * Crea un nuevo cliente
     * @param nombre nombre del cliente 
     * @param apellido apellido del cliente
     * @param provincia provincia del cliente
     * @param canton canton del cliente
     * @param distrito distrito del cliente
     * @param telefono telefono del cliente
     * @param email email del cliente
     * @param fechaDeNacimiento fecha de nacimiento del cliente
     */
    public void crearCliente(String nombre, String apellido, String provincia, String canton, String distrito, String telefono, String email, String fechaDeNacimiento){
        ultimoCodigo++;
        if(telefonoValido(telefono)){
            if(correoValido(email)){
                if(fechaValida(fechaDeNacimiento)){
                    Cliente nuevoCliente = new Cliente(ultimoCodigo, nombre, apellido, provincia, canton, distrito, telefono, email, fechaDeNacimiento, new ArrayList<Factura>());
                    clientes.add(nuevoCliente);
                    guardarDatos();
                }else{
                    JOptionPane.showMessageDialog(null, "Debe ingresar una fecha de nacimiento valida.", "Error.", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Debe ingresar un correo electronico valido.", "Error.", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe ingresar un numero telefonico valido.", "Error.", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Devuelve la lista de clientes.
     * @return la lista de clientes.
     */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Establece la lista de clientes.
     * @param clientes la lista de clientes a establecer
     */
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * Carga los datos de los clientes desde un archivo JSON y los agrega a la lista de clientes.
     * Si el archivo no existe, crea uno nuevo.
     */
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
                Object facturasObj = cliente.get("facturas");
                ArrayList<Factura> facturas;
                if (facturasObj instanceof ArrayList<?>) {
                    facturas = (ArrayList<Factura>) facturasObj;
                } else {
                    facturas = new ArrayList<Factura>();
                }
                Cliente nuevoCliente = new Cliente(idCliente, nombre, apellido, provincia, canton, distrito, telefono, email, fechaDeNacimiento, facturas);
                clientes.add(nuevoCliente);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        actualizarUltimoCodigo();
    }

    /**
     * Guarda los datos de los clientes en un archivo JSON.
     * Si el archivo no existe, crea uno nuevo.
     */
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
            cliente.put("facturas", clientes.get(i).getFacturas());
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
     * Obtiene el largo de una cadena de texto.
     * @param numero la cadena de texto de la cual se desea obtener el largo.
     * @return el largo de la cadena de texto.
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

    /**
     * Obtiene el número de una cadena de texto.
     * @param numero la cadena de texto de la cual se desea obtener el número.
     * @return el número contenido en la cadena de texto.
     */
    public String obtenerNumero(String numero){
        String cont="";
        for (int i = 0; i < numero.length(); i++) {
            if (Character.isDigit(numero.charAt(i))) {
                cont+=numero.charAt(i);
            }
        }
        return cont;
    }

    /**
     * Verifica si un número de teléfono es válido.
     * @param num el número de teléfono a verificar
     * @return true si el número de teléfono es válido, false de lo contrario
     */
    public boolean telefonoValido(String num){
        int primerDigito = Character.getNumericValue(num.charAt(0));
        return largo(num)==8 && (primerDigito==2 || primerDigito==4 || primerDigito==6 || primerDigito==8);
    }

    /**
     * Verifica si un correo electrónico es válido.
     * @param email el correo electrónico a verificar
     * @return true si el correo electrónico es válido, false de lo contrario
     */
    public boolean correoValido(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Verifica si una fecha dada es válida.
     * @param fecha La fecha en formato "dd/MM/yyyy" a validar.
     * @return true si la fecha es válida, false de lo contrario.
     */
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

    /**
     * Modifica los datos de un cliente existente en la lista de clientes.
     * @param idCliente el identificador del cliente a modificar
     * @param nombre el nuevo nombre del cliente
     * @param apellido el nuevo apellido del cliente
     * @param provincia la nueva provincia del cliente
     * @param canton el nuevo cantón del cliente
     * @param distrito el nuevo distrito del cliente
     * @param telefono el nuevo número de teléfono del cliente
     * @param email el nuevo correo electrónico del cliente
     * @param fechaDeNacimiento la nueva fecha de nacimiento del cliente
     */
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
                        clientes.get(i).setFacturas(clientes.get(i).getFacturas());
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
     * @param idCliente el identificador del cliente a eliminar
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
