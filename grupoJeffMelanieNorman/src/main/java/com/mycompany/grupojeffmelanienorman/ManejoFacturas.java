/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupojeffmelanienorman;

import java.io.FileReader;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author XPC
 */
public class ManejoFacturas {
    // Atributos
    private ArrayList<Factura> facturas;
    private final String fileName = "grupoJeffMelanieNorman/Facturas.json";

    public ManejoFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }
    
    private void cargarDatos(){
        JSONParser parser = new JSONParser();
        try{
            FileReader reader = new FileReader(fileName);
            Object obj = parser.parse(reader);

            JSONArray listaFacturas = (JSONArray) obj;
            for(int i = 0; i < listaFacturas.length(); i++){
                JSONObject facturaJson = (JSONObject) listaFacturas.get(i);
                int idFactura = Integer.parseInt(facturaJson.get("idFactura").toString());
                int subTotal = Integer.parseInt(facturaJson.get("subTotal").toString());
                boolean estado = Boolean.parseBoolean(facturaJson.get("estado").toString());
                Factura factura = new Factura(idFactura, subTotal, estado);
                this.facturas.add(factura);
            }
    }
}
