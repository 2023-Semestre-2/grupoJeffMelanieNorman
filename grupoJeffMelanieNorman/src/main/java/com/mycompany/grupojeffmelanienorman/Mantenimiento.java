package com.mycompany.grupojeffmelanienorman;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mantenimiento {

    public JSONArray listaServicios;
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
    
    
    
    public String buscarClientePorCodigo(int codigoCliente) {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader("Clientes.json");
            JSONObject obj = (JSONObject) parser.parse(reader);
            JSONArray listaClientes = (JSONArray) obj.get("Clientes");

            for (Object item : listaClientes) {
                JSONObject cliente = (JSONObject) item;
                Long codigo = (Long) cliente.get("Codigo");
                if (codigo != null && codigo.intValue() == codigoCliente) {
                    return (String) cliente.get("Nombre"); // Retorna el nombre del cliente
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra el cliente
    }

    


    public JSONObject agregarServicio(int codigoCliente, String marcaBicicleta, String descripcionBicicleta, int precio, String fechaRecibido, String fechaEntrega, String observaciones, String estado) {
        String nombreCliente = buscarClientePorCodigo(codigoCliente);

        if (nombreCliente != null) {
            // El cliente existe, proceder con la creación del servicio
            ultimoCodigoServicio++;

            JSONObject nuevoServicio = new JSONObject();
            nuevoServicio.put("Codigo", ultimoCodigoServicio);
            nuevoServicio.put("Codigo Cliente", codigoCliente);
            nuevoServicio.put("Nombre Cliente", nombreCliente); // Agregar nombre del cliente
            nuevoServicio.put("Marca Bicicleta", marcaBicicleta.trim());
            nuevoServicio.put("Descripcion Bicicleta", descripcionBicicleta.trim());
            nuevoServicio.put("Precio", precio);
            nuevoServicio.put("Fecha Recibido", fechaRecibido); // Asumiendo que la fecha viene en un formato correcto
            nuevoServicio.put("Fecha Entrega", fechaEntrega); // Asumiendo que la fecha viene en un formato correcto
            nuevoServicio.put("Observaciones", observaciones.trim());
            nuevoServicio.put("Estado", estado);

            listaServicios.add(nuevoServicio);
            guardarDatos();

            return nuevoServicio; // Retorna el servicio recién creado
        } else {
            // Cliente no encontrado, manejar según sea necesario
            return null; // O manejar de otra manera según la lógica de tu aplicación
        }
    }

    
    
    public JSONObject buscar(int criterioBusqueda, String valorBusqueda) {
        JSONObject encontrado = null;

        if (criterioBusqueda == 1) { // Búsqueda por código del servicio
            try {
                int codigoServicio = Integer.parseInt(valorBusqueda);
                for (Object item : listaServicios) {
                    JSONObject servicio = (JSONObject) item;
                    if (((Long) servicio.get("Codigo")).intValue() == codigoServicio) {
                        encontrado = servicio;
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                // Manejar el error si valorBusqueda no es un número válido
            }
        } else if (criterioBusqueda == 2) { // Búsqueda por nombre del cliente
            for (Object item : listaServicios) {
                JSONObject servicio = (JSONObject) item;
                if (servicio.get("Nombre Cliente").toString().equalsIgnoreCase(valorBusqueda)) {
                    encontrado = servicio;
                    break;
                }
            }
        }

        return encontrado;
    }

    public void modificar(JSONObject servicio, String marcaBicicleta, String descripcionBicicleta, int precio, String fechaRecibido, String fechaEntrega, String observaciones, String estado) {
        servicio.put("Marca Bicicleta", marcaBicicleta.trim());
        servicio.put("Descripcion Bicicleta", descripcionBicicleta.trim());
        servicio.put("Precio", precio);
        servicio.put("Fecha Recibido", fechaRecibido);
        servicio.put("Fecha Entrega", fechaEntrega);
        servicio.put("Observaciones", observaciones.trim());
        servicio.put("Estado", estado);

        guardarDatos();
    }
    
    
    public void eliminar(JSONObject servicio) {
        listaServicios.remove(servicio);
        guardarDatos();
    }

}

