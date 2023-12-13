package com.mycompany.grupojeffmelanienorman;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RegistroProductos {
    
    private JSONArray listaProductos;
    private JSONArray listaArticulos;
    private int ultimoCodigoArticulo;
    private static final String FILE_PATH = "Productos.json";

    public RegistroProductos() {
        cargarDatos();
        
    }

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

    private void actualizarUltimoCodigo() {
        if (!listaArticulos.isEmpty()) {
            JSONObject ultimoArticulo = (JSONObject) listaArticulos.get(listaArticulos.size() - 1);
            ultimoCodigoArticulo = ((Long) ultimoArticulo.get("Codigo")).intValue();
        }
    }


    public void agregarArticulo(String nombre, String tipoArticulo, String tamano, String marca, int cantidad, int precio) {
        ultimoCodigoArticulo++;

        JSONObject nuevoArticulo = new JSONObject();
        nuevoArticulo.put("Codigo", ultimoCodigoArticulo);
        nuevoArticulo.put("Nombre", nombre);
        nuevoArticulo.put("Tipo", tipoArticulo);
        nuevoArticulo.put("Tamaño", tamano);
        nuevoArticulo.put("Marca", marca);
        nuevoArticulo.put("Cantidad", cantidad);
        nuevoArticulo.put("Precio", precio);

        listaArticulos.add(nuevoArticulo);
        guardarDatos();

        JOptionPane.showMessageDialog(null, "Artículo agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }




    private void configurarComboBoxTamanos(JComboBox<String> comboBoxTamanos) {
        String[] tamanos = {"12", "16", "22", "26", "27", "27.5", "29"};
        for (String tamano : tamanos) {
            comboBoxTamanos.addItem(tamano);
        }
    }

   
    public JSONObject buscar(int criterioBusqueda, String valorBusqueda) {
        JSONObject encontrado = null;

        if (criterioBusqueda == 1) { // Búsqueda por código
            try {
                int codigo = Integer.parseInt(valorBusqueda);
                encontrado = buscarPorCodigo(codigo, listaArticulos);
            } catch (NumberFormatException e) {
                // Manejar el caso en que valorBusqueda no es un número válido
            }
        } else if (criterioBusqueda == 2) { // Búsqueda por nombre
            encontrado = buscarPorNombre(valorBusqueda, listaArticulos);
        }

        return encontrado;
    }

    
    
    private JSONObject buscarPorCodigo(int codigo) {
        for (Object item : listaArticulos) {
            JSONObject obj = (JSONObject) item;
            if (((Number) obj.get("Codigo")).intValue() == codigo) {
                return obj;
            }
        }
        return null;
    }

    
    private JSONObject buscarPorNombre(String nombre) {
        for (Object item : listaArticulos) {
            JSONObject obj = (JSONObject) item;
            if (obj.get("Nombre").toString().equalsIgnoreCase(nombre)) {
                return obj;
            }
        }
        return null;
    }

    
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
        if (tamano != null && !tamano.isEmpty()) {
            articulo.put("Tamaño", tamano);
        }

        guardarDatos(); // Guardar los cambios en el archivo JSON
    }
    
    
    public void eliminar(JSONObject articulo) {
        listaArticulos.remove(articulo); // Elimina el artículo de la lista
        guardarDatos(); // Guarda los cambios en el archivo JSON
    }
   
    
    /*public static void main(String[] args) {
        RegistroProductos registro = new RegistroProductos();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar Artículo");
            System.out.println("2. Buscar Artículo");
            System.out.println("3. Salir");
            System.out.print("Ingrese su opción: ");

            int eleccion = scanner.nextInt();

            switch (eleccion) {
                case 1:
                    registro.agregarArticulo();
                    break;
                case 2:
                    registro.buscar();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor intente nuevamente.");
            }
        }
    }*/
}