/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupojeffmelanienorman;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * ManejoFacturas
 * 
 * Esta clase se encarga de manejar las facturas
 *
 * @author Jeffry
 */
public class ManejoFacturas {
    // Atributos
    private ArrayList<Factura> facturas;
    private final String fileName = "Facturas.json";
    private int ultimoCodigo;

    /**
     * Constructor para la clase ManejoFacturas.
     * Inicializa la lista de facturas leyendo el archivo JSON.
     */
    public ManejoFacturas() {
        cargarDatos();
    }
    
    /**
     * Carga los datos de las facturas desde un archivo JSON.
     */
    private void cargarDatos(){
        JSONParser parser = new JSONParser();
        try{
            FileReader reader = new FileReader(fileName);
            Object obj = parser.parse(reader);

            JSONArray listaFacturas = (JSONArray) obj;
            for(int i = 0; i < listaFacturas.length(); i++){
                JSONObject facturaJson = (JSONObject) listaFacturas.get(i);
                int idFactura = Integer.parseInt(facturaJson.get("idFactura").toString());
                String fecha = (String) facturaJson.get("fecha");
                boolean estado = Boolean.parseBoolean(facturaJson.get("estado").toString());
                JSONObject mantenimiento = facturaJson.getJSONObject("mantenimiento");
                Factura factura = new Factura(idFactura, fecha, estado, mantenimiento);
                this.facturas.add(factura);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Obtiene la lista de facturas.
     * @return la lista de facturas
     */
    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    /**
     * Guarda los datos de las facturas en un archivo JSON.
     */
    private void guardarDatos(){
        JSONArray listaFacturas = new JSONArray();
        for(int i = 0; i < this.facturas.size(); i++){
            Factura factura = this.facturas.get(i);
            JSONObject facturaJson = new JSONObject();
            
            facturaJson.put("idFactura", factura.getIdFactura());
            facturaJson.put("subTotal", factura.getSubTotal());
            facturaJson.put("impuesto", factura.getImpuesto());
            facturaJson.put("total", factura.getTotal());
            facturaJson.put("codigo servicio", factura.getCodigoServicio());
            facturaJson.put("precio unitario", factura.getPrecioUnitario());
            facturaJson.put("codigo cliente", factura.getCodigoCliente());
            facturaJson.put("fecha", factura.getFechaR());
            facturaJson.put("estado", factura.isEstado());
            facturaJson.put("mantenimiento", factura.getMantenimiento());
            listaFacturas.put(facturaJson);
        }
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(listaFacturas.toString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void actualizarUltimoCodigo() {
        if (facturas!=null && !facturas.isEmpty()) {
            Factura ultimoCliente = (Factura) facturas.get(facturas.size() - 1);
            ultimoCodigo = ultimoCliente.getIdFactura();
        }
    }
    /**
     * Agrega una factura al conjunto de facturas y guarda los datos actualizados.
     * @param factura la factura a agregar
     */
    public void agregarFactura(String fecha, boolean estado, JSONObject mantenimiento){
        actualizarUltimoCodigo();
        ultimoCodigo++;
        Factura factura=new Factura(ultimoCodigo, fecha, estado, mantenimiento);
        this.facturas.add(factura);
        this.guardarDatos();
    }

    /**
     * Anula una factura dado su ID.
     * @param idFactura el ID de la factura a anular
     */
    public void anularFactura(int idFactura){
        for(int i = 0; i < this.facturas.size(); i++){
            Factura factura = this.facturas.get(i);
            if(factura.getIdFactura() == idFactura){
                factura.setEstado(false);
                this.guardarDatos();
                break;
            }
        }
    }
}
