package com.mycompany.grupojeffmelanienorman;
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
 * RegistroProductos
 * 
 * Clase que maneja el registro de productos y artículos.
 * 
 * @author Norman
 */

public class RegistroProductos {
    // Atributos
    /**
     * Lista de productos existentes.
     */
    public JSONArray listaProductos;
    /**
     * Lista de artículos existentes.
     */
    public JSONArray listaArticulos;
    private int ultimoCodigoArticulo;
    private static final String FILE_PATH = "Productos.json";
    
    /**
     * Constructor de la clase RegistroProductos.
     * Carga los datos de los productos.
     */
    public RegistroProductos() {
        cargarDatos();
    }

    /**
     * Carga los datos del archivo JSON en las listas de productos y artículos.
     * Si ocurre un error al cargar los datos, se inicializan las listas vacías.
     */
    private void cargarDatos() {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(FILE_PATH);
            JSONObject obj = (JSONObject) parser.parse(reader);

            // Cargar la lista de productos existente sin modificarla
            listaProductos = (JSONArray) obj.getOrDefault("Productos", new JSONArray());

            // Cargar la lista de artículos, que sí se modificará durante la ejecución del programa
            listaArticulos = (JSONArray) obj.getOrDefault("Articulos", new JSONArray());

            actualizarUltimoCodigo(); // Actualizar el último código después de cargar los datos
        } catch (Exception e) {
            listaArticulos = new JSONArray();
            listaProductos = new JSONArray(); // Inicializar también listaProductos en caso de error
        }
    }
    
    /**
     * Guarda los datos de los productos y artículos en un archivo JSON.
     */
    private void guardarDatos() {
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            JSONObject obj = new JSONObject();
            obj.put("Productos", listaProductos); // Incluye los productos existentes
            obj.put("Articulos", listaArticulos); // Y los artículos
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza el último código de artículo en base a la lista de artículos.
     * Si la lista de artículos no está vacía, obtiene el último artículo de la lista
     * y actualiza el valor de últimoCodigoArticulo con el código del último artículo.
     */
    private void actualizarUltimoCodigo() {
        if (!listaArticulos.isEmpty()) {
            JSONObject ultimoArticulo = (JSONObject) listaArticulos.get(listaArticulos.size() - 1);
            ultimoCodigoArticulo = ((Long) ultimoArticulo.get("Codigo")).intValue();
        }
    }


    /**
     * Agrega un nuevo artículo al registro de productos.
     * @param nombre el nombre del artículo
     * @param tipoArticulo el tipo de artículo
     * @param tamano el tamaño del artículo
     * @param marca la marca del artículo
     * @param cantidad la cantidad disponible del artículo
     * @param precio el precio del artículo
     * @return el objeto JSONObject del artículo recién creado
     */
    public JSONObject agregarArticulo(String nombre, String tipoArticulo, String tamano, String marca, int cantidad, int precio) {
        ultimoCodigoArticulo++;

        // Usar el nuevo método para obtener el código del tipo de producto desde JSON
        int codigoTipoProducto = obtenerCodigoTipoProductoDesdeJson(tipoArticulo);

        JSONObject nuevoArticulo = new JSONObject();
        nuevoArticulo.put("Codigo", ultimoCodigoArticulo);
        nuevoArticulo.put("Codigo Tipo", codigoTipoProducto); // Agregar código del tipo de producto
        nuevoArticulo.put("Nombre", nombre);
        nuevoArticulo.put("Tipo", tipoArticulo);
        if(tipoArticulo.equals("Bicicleta")){
            nuevoArticulo.put("Tamaño", tamano);
        }
        nuevoArticulo.put("Marca", marca);
        nuevoArticulo.put("Cantidad", cantidad);
        nuevoArticulo.put("Precio", precio);

        listaArticulos.add(nuevoArticulo);
        guardarDatos();

        return nuevoArticulo; // Retorna el artículo recién creado
    }
    

    /**
    * Busca un objeto JSONObject en la lista de artículos por su código.
    * @param codigo El código del artículo a buscar.
    * @return El objeto JSONObject correspondiente al código especificado, o null si no se encuentra.
    */
    private JSONObject buscarPorCodigo(int codigo) {
        for (Object item : listaArticulos) {
            JSONObject obj = (JSONObject) item;
            if (((Number) obj.get("Codigo")).intValue() == codigo) {
                return obj;
            }
        }
        return null;
    }

    
    /**
    * Busca un objeto JSONObject en la lista de artículos por su nombre.
    * @param nombre el nombre del artículo a buscar
    * @return el objeto JSONObject correspondiente al artículo encontrado, o null si no se encuentra
    */
    private JSONObject buscarPorNombre(String nombre) {
        for (Object item : listaArticulos) {
            JSONObject obj = (JSONObject) item;
            if (obj.get("Nombre").toString().equalsIgnoreCase(nombre)) {
                return obj;
            }
        }
        return null;
    }

    /**
     * Obtiene el código del tipo de producto a partir de un archivo JSON.
     * @param tipoArticulo el nombre del tipo de artículo a buscar
     * @return el código del tipo de producto encontrado, o 0 si no se encuentra o hay un error
     */
    private int obtenerCodigoTipoProductoDesdeJson(String tipoArticulo) {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(FILE_PATH);
            JSONObject obj = (JSONObject) parser.parse(reader);
            JSONArray listaProductos = (JSONArray) obj.get("Productos");

            for (Object item : listaProductos) {
                JSONObject producto = (JSONObject) item;
                String nombreProducto = (String) producto.get("Nombre");
                if (nombreProducto.equalsIgnoreCase(tipoArticulo)) {
                    return ((Long) producto.get("Codigo")).intValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción adecuadamente
        }
        return 0; // Retorna un código por defecto o para tipos no reconocidos
    }

    /**
     * Configura el JComboBox de tamaños con los valores proporcionados.
     * @param comboBoxTamanos El JComboBox de tamaños a configurar.
     */
    private void configurarComboBoxTamanos(JComboBox<String> comboBoxTamanos) {
        String[] tamanos = {"12", "16", "22", "26", "27", "27.5", "29"};
        for (String tamano : tamanos) {
            comboBoxTamanos.addItem(tamano);
        }
    }

    /**
    * Realiza una búsqueda en la lista de artículos según el criterio de búsqueda y el valor proporcionados.
    * @param criterioBusqueda el criterio de búsqueda (1 para búsqueda por código, 2 para búsqueda por nombre)
    * @param valorBusqueda el valor a buscar
    * @return el objeto JSONObject encontrado, o null si no se encontró ningún artículo que cumpla con el criterio de búsqueda
    */
    public JSONObject buscar(int criterioBusqueda, String valorBusqueda) {
        JSONObject encontrado = null;

        if (criterioBusqueda == 1) { // Búsqueda por código
            try {
                int codigo = Integer.parseInt(valorBusqueda);
                encontrado = buscarPorCodigo(codigo);
            } catch (NumberFormatException e) {
                // Manejar el caso en que valorBusqueda no es un número válido
            }
        } else if (criterioBusqueda == 2) { // Búsqueda por nombre
            encontrado = buscarPorNombre(valorBusqueda);
        }

        return encontrado;
    }
    
    /**
     * Modifica los atributos de un artículo en el registro de productos.
     * Si alguno de los parámetros es diferente de null y no está vacío, se actualiza el valor correspondiente en el objeto JSON del artículo.
     * Los parámetros 'cantidad' y 'precio' siempre se actualizan.
     * Después de realizar las modificaciones, se guardan los cambios en el archivo JSON.
     * @param articulo el objeto JSON del artículo a modificar
     * @param nombre el nuevo nombre del artículo (opcional)
     * @param marca la nueva marca del artículo (opcional)
     * @param cantidad la nueva cantidad del artículo
     * @param precio el nuevo precio del artículo
     * @param tipo el nuevo tipo del artículo (opcional)
     * @param tamano el nuevo tamaño del artículo (opcional)
     */
    public void modificar(JSONObject articulo, String nombre, String marca, int cantidad, int precio, String tipo, String tamano) {
        if (nombre != null && !nombre.isEmpty()) {
            articulo.put("Nombre", nombre);
        }
        if (marca != null && !marca.isEmpty()) {
            articulo.put("Marca", marca);
        }
        // Asumiendo que 'cantidad' y 'precio' son valores que siempre se actualizarán
        articulo.put("Cantidad", cantidad);
        articulo.put("Precio", precio);
        if (tipo != null && !tipo.isEmpty()) {
            articulo.put("Tipo", tipo);
        }
        if (tipo.equals("Bicileta") && tamano != null && !tamano.isEmpty()) {
            articulo.put("Tamaño", tamano);
        }

        guardarDatos(); // Guardar los cambios en el archivo JSON
    }

    /**
     * Elimina un artículo de la lista y guarda los cambios en el archivo JSON.
     * @param articulo el artículo a eliminar
     */
    public void eliminar(JSONObject articulo) {
        listaArticulos.remove(articulo); // Elimina el artículo de la lista
        guardarDatos(); // Guarda los cambios en el archivo JSON
    }
        
}