package com.mycompany.grupojeffmelanienorman;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ManejoFacturas
 * 
 * Clase que maneja los servicios de mantenimiento de bicicletas.
 * 
 * @author Norman
 */

public class Mantenimiento {
    // Atributos
    /**
     * Lista de servicios de mantenimiento.
     */
    public JSONArray listaServicios;
    private int ultimoCodigoServicio;
    private static final String FILE_PATH = "grupoJeffMelanieNorman/Mantenimiento.json";

    /**
     * Constructor para la clase Mantenimiento.
     * Inicializa la lista de servicios leyendo el archivo JSON.
     */
    public Mantenimiento() {
        cargarDatos();
    }

    /**
     * Carga los datos de un archivo JSON y actualiza la lista de servicios.
     * Si ocurre algún error al cargar los datos, se inicializa una nueva lista de servicios vacía.
     */
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

    /**
     * Guarda los datos de los servicios en un archivo JSON.
     */
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

    /**
     * Actualiza el último código de servicio.
     * Si la lista de servicios no está vacía, obtiene el último servicio de la lista y actualiza el código del último servicio sumándole 1.
     * Si la lista de servicios está vacía, establece el código del último servicio como 1.
     */
    private void actualizarUltimoCodigo() {
        if (!listaServicios.isEmpty()) {
            JSONObject ultimoServicio = (JSONObject) listaServicios.get(listaServicios.size() - 1);
            ultimoCodigoServicio = ((Long) ultimoServicio.get("Codigo")).intValue() + 1;
        } else {
            ultimoCodigoServicio = 1;
        }
    }

    /**
     * Busca un cliente por su código y devuelve el nombre del cliente.
     * @param codigoCliente el código del cliente a buscar
     * @return el nombre del cliente si se encuentra, o null si no se encuentra el cliente
     */
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
    
    /**
     * Agrega un nuevo servicio a la lista de servicios. El nuevo servicio se crea con los datos recibidos como parámetros.
     * @param codigoCliente código del cliente
     * @param marcaBicicleta marca de la bicicleta
     * @param descripcionBicicleta descripción de la bicicleta
     * @param precio precio del servicio
     * @param fechaRecibido fecha en formato "dd/MM/yyyy"
     * @param fechaEntrega fecha en formato "dd/MM/yyyy"
     * @param observaciones texto con las observaciones del servicio
     * @param estado "Abierto" o "Cerrado"
     * @param cliente el cliente seleccionado
     * @return el servicio recién creado
     */
    public JSONObject agregarServicio(Cliente cliente, String marcaBicicleta, String descripcionBicicleta, int precio, String fechaRecibido, String fechaEntrega, String observaciones, String estado) {
        ultimoCodigoServicio++;

        JSONObject nuevoServicio = new JSONObject();
        nuevoServicio.put("Codigo", ultimoCodigoServicio);
        nuevoServicio.put("Cliente", cliente);
        nuevoServicio.put("Codigo Cliente", cliente.getIdCliente());
        nuevoServicio.put("Marca Bicicleta", marcaBicicleta.trim());
        nuevoServicio.put("Descripcion Bicicleta", descripcionBicicleta.trim());
        nuevoServicio.put("Precio", precio);
        nuevoServicio.put("Fecha Recibido", fechaRecibido); // Asumiendo que la fecha viene en un formato correcto
        nuevoServicio.put("Fecha Entrega", fechaEntrega); // Asumiendo que la fecha viene en un formato correcto
        nuevoServicio.put("Observaciones", observaciones.trim());
        nuevoServicio.put("Estado", estado); // "Abierto" o "Cerrado"

        listaServicios.add(nuevoServicio);
        guardarDatos();

        return nuevoServicio; // Retorna el servicio recién creado
    }  
    
    /**
     * Modifica los datos de un servicio en el sistema.
     * @param servicio el objeto JSON que representa el servicio a modificar
     * @param marcaBicicleta la marca de la bicicleta del servicio
     * @param descripcionBicicleta la descripción de la bicicleta del servicio
     * @param precio el precio del servicio
     * @param fechaRecibido la fecha de recepción del servicio
     * @param fechaEntrega la fecha de entrega del servicio
     * @param observaciones las observaciones del servicio
     * @param estado el estado del servicio
     */
    public void modificar(JSONObject servicio, Cliente cliente, String marcaBicicleta, String descripcionBicicleta, int precio, String fechaRecibido, String fechaEntrega, String observaciones, String estado) {
        servicio.put("Cliente", cliente);
        servicio.put("Codigo Cliente", cliente.getIdCliente());
        servicio.put("Marca Bicicleta", marcaBicicleta.trim());
        servicio.put("Descripcion Bicicleta", descripcionBicicleta.trim());
        servicio.put("Precio", precio);
        servicio.put("Fecha Recibido", fechaRecibido);
        servicio.put("Fecha Entrega", fechaEntrega);
        servicio.put("Observaciones", observaciones.trim());
        servicio.put("Estado", estado);

        guardarDatos();
    }
    
    /**
     * Busca un servicio por su código y devuelve el servicio.
     * @param criterioBusqueda 1 para buscar por código del servicio, 2 para buscar por nombre del cliente
     * @param valorBusqueda  el valor a buscar
     * @return el servicio si se encuentra, o null si no se encuentra el servicio
     */
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
    
    /**
    * Procesa todos los servicios con estado 'Cerrado' para facturación.
    */
    public void procesarServiciosCerradosParaFacturacion() {
        for (Object item : listaServicios) {
            JSONObject servicio = (JSONObject) item;
            if ("Cerrado".equals(servicio.get("Estado").toString())) {
                // Aquí llamar a alguna función en ManejoFacturas para procesar este servicio
                // Por ejemplo: manejoFacturas.crearFactura(servicio);
            }
        }
    }


    /**
     * Modifica un servicio existente en el sistema.
     * @param servicio el objeto JSON que representa el servicio a modificar
     * @param marcaBicicleta la marca de la bicicleta asociada al servicio
     * @param descripcionBicicleta la descripción de la bicicleta asociada al servicio
     * @param precio el precio del servicio
     * @param fechaRecibido la fecha en que se recibió la bicicleta para el servicio
     * @param fechaEntrega la fecha de entrega del servicio
     * @param observaciones las observaciones adicionales del servicio
     * @param estado el estado actual del servicio
     * @param cliente el cliente seleccionado
     */
    public void modificarServicio(JSONObject servicio, Cliente cliente, String marcaBicicleta, String descripcionBicicleta, int precio, String fechaRecibido, String fechaEntrega, String observaciones, String estado) {
        servicio.put("Cliente", cliente);
        servicio.put("Codigo Cliente", cliente.getIdCliente());
        servicio.put("Marca Bicicleta", marcaBicicleta.trim());
        servicio.put("Descripcion Bicicleta", descripcionBicicleta.trim());
        servicio.put("Precio", precio);
        servicio.put("Fecha Recibido", fechaRecibido);
        servicio.put("Fecha Entrega", fechaEntrega);
        servicio.put("Observaciones", observaciones.trim());
        servicio.put("Estado", estado);

        guardarDatos();
    }
    
    /**
     * Elimina un servicio del sistema.
     * @param servicio el objeto JSON que representa el servicio a eliminar
     */
    public void eliminar(JSONObject servicio) {
        listaServicios.remove(servicio);
        guardarDatos();
    }

}

