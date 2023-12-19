package com.mycompany.grupojeffmelanienorman;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Mantenimiento {

    private JSONArray listaServicios;
    private int ultimoCodigoServicio;
    private static final String FILE_PATH = "Mantenimientos.json";

    public Mantenimiento() {
        cargarDatos();
    }

    private void cargarDatos() {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(FILE_PATH);
            JSONObject obj = (JSONObject) parser.parse(reader);
            listaServicios = (JSONArray) obj.getOrDefault("Servicios", new JSONArray());
            actualizarUltimoCodigo();
        } catch (Exception e) {
            listaServicios = new JSONArray();
        }
    }

    private void guardarDatos() {
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            JSONObject obj = new JSONObject();
            obj.put("Servicios", listaServicios);
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarUltimoCodigo() {
        if (!listaServicios.isEmpty()) {
            JSONObject ultimoServicio = (JSONObject) listaServicios.get(listaServicios.size() - 1);
            ultimoCodigoServicio = ((Long) ultimoServicio.get("Codigo")).intValue() + 1;
        } else {
            ultimoCodigoServicio = 1;
        }
    }

    public JSONObject agregarServicio(String marcaBicicleta, String descripcionBicicleta, int precio) {
        JSONObject nuevoServicio = new JSONObject();
        nuevoServicio.put("Codigo", ultimoCodigoServicio++);
        nuevoServicio.put("Marca Bicicleta", marcaBicicleta.trim());
        nuevoServicio.put("Descripcion Bicicleta", descripcionBicicleta.trim());
        nuevoServicio.put("Precio", precio);

        listaServicios.add(nuevoServicio);
        guardarDatos();

        return nuevoServicio;
    }
}

