package com.proyecto1.registroproductos;
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
    private int ultimoCodigoProducto;
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

            // Verifica si existen las claves "Productos" y "Articulos"
            if (obj.containsKey("Productos")) {
                listaProductos = (JSONArray) obj.get("Productos");
            } else {
                listaProductos = new JSONArray(); // Inicializa si no existe
            }

            if (obj.containsKey("Articulos")) {
                listaArticulos = (JSONArray) obj.get("Articulos");
            } else {
                listaArticulos = new JSONArray(); // Inicializa si no existe
            }

            actualizarUltimoCodigo();
        } catch (Exception e) {
            listaProductos = new JSONArray();
            listaArticulos = new JSONArray();
        }
    }
    private void actualizarUltimoCodigo() {
        if (!listaProductos.isEmpty()) { // Se ejecuta solo si listaProductos tiene al menos un elemento
            JSONObject ultimoProducto = (JSONObject) listaProductos.get(listaProductos.size() - 1);
            ultimoCodigoProducto = ((Long) ultimoProducto.get("Codigo")).intValue();
        }
        if (!listaArticulos.isEmpty()) {
            JSONObject ultimoArticulo = (JSONObject) listaArticulos.get(listaArticulos.size() - 1);
            ultimoCodigoArticulo = ((Long) ultimoArticulo.get("Codigo")).intValue();
        }
    }

    public void agregarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del producto:");
        String nombre = scanner.nextLine();

        ultimoCodigoProducto++;

        JSONObject nuevoProducto = new JSONObject();
        nuevoProducto.put("Codigo", ultimoCodigoProducto);
        nuevoProducto.put("Nombre", nombre);

        listaProductos.add(nuevoProducto);
        guardarDatos();

        System.out.println("Producto agregado con éxito: " + nuevoProducto);
    }

        public void agregarArticulo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del artículo:");
        String nombre = scanner.nextLine();

        System.out.println("Seleccione el tipo de artículo:\n1. Bicicleta\n2. Accesorios\n3. Suplementos Alimenticios");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea restante
        String tipoArticulo = "";
        String tamaño = "0";

        switch (tipo) {
            case 1:
                tipoArticulo = "Bicicleta";
                tamaño = elegirTamaño();
                break;
            case 2:
                tipoArticulo = "Accesorios";
                break;
            case 3:
                tipoArticulo = "Suplementos Alimenticios";
                break;
            default:
                System.out.println("Tipo de artículo no válido");
                return;
        }

        ultimoCodigoArticulo++;
        
        System.out.println("Ingrese la marca del artículo:");
        String marca = scanner.nextLine();

        System.out.println("Ingrese la cantidad del artículo:");
        int cantidad = scanner.nextInt();

        System.out.println("Ingrese el precio del artículo:");
        int precio = scanner.nextInt();
        scanner.nextLine();
        
        JSONObject nuevoArticulo = new JSONObject();
        nuevoArticulo.put("Codigo", ultimoCodigoArticulo);
        nuevoArticulo.put("Nombre", nombre);
        nuevoArticulo.put("Tipo", tipoArticulo);
        nuevoArticulo.put("Tamaño", tamaño);
        nuevoArticulo.put("Marca", marca);
        nuevoArticulo.put("Cantidad", cantidad); // Añadir cantidad al objeto JSON
        nuevoArticulo.put("Precio", precio);

        listaArticulos.add(nuevoArticulo);
        guardarDatos();

        System.out.println("Artículo agregado con éxito: " + nuevoArticulo);
    }


    private String elegirTamaño() {
        String[] tamaños = {"12", "16", "22", "26", "27", "27.5", "29"};
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el tamaño de la bicicleta:");
        for (int i = 0; i < tamaños.length; i++) {
            System.out.println((i + 1) + ". " + tamaños[i]);
        }
        int eleccion = scanner.nextInt();
        return tamaños[Math.max(0, Math.min(eleccion - 1, tamaños.length - 1))];
    }

    private void guardarDatos() {
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            JSONObject obj = new JSONObject();
            obj.put("Productos", listaProductos);
            obj.put("Articulos", listaArticulos);
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void modificar(JSONObject objeto) {
        Scanner scanner = new Scanner(System.in);
        boolean esProducto = objeto.containsKey("Marca"); // Suponiendo que solo los artículos tienen 'Marca'

        System.out.println("Modificando " + (esProducto ? "artículo" : "producto") + ": " + objeto);
        System.out.println("Ingrese los nuevos valores (deje en blanco para no cambiar):");

        // Nombre
        System.out.println("Nombre (" + objeto.get("Nombre") + "): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            objeto.put("Nombre", nombre);
        }

        // Marca y otros campos para artículos
        if (esProducto) {
            // Marca
            System.out.println("Marca (" + objeto.get("Marca") + "): ");
            String marca = scanner.nextLine();
            if (!marca.isEmpty()) {
                objeto.put("Marca", marca);
            }

            // Cantidad
            System.out.println("Cantidad (" + objeto.get("Cantidad") + "): ");
            String cantidad = scanner.nextLine();
            if (!cantidad.isEmpty()) {
                objeto.put("Cantidad", Integer.parseInt(cantidad));
            }

            // Precio
            System.out.println("Precio (" + objeto.get("Precio") + "): ");
            String precio = scanner.nextLine();
            if (!precio.isEmpty()) {
                objeto.put("Precio", Integer.parseInt(precio));
            }

            // Tipo
            System.out.println("Tipo (" + objeto.get("Tipo") + "): ");
            String tipo = scanner.nextLine();
            if (!tipo.isEmpty()) {
                objeto.put("Tipo", tipo);
            }

            // Tamaño
            System.out.println("Tamaño (" + objeto.get("Tamaño") + "): ");
            String tamaño = scanner.nextLine();
            if (!tamaño.isEmpty()) {
                objeto.put("Tamaño", tamaño);
            }
        }

        guardarDatos(); // Guardar los cambios en el archivo JSON

        System.out.println("Modificación completada: " + objeto);
    }
    
    
    private JSONObject buscarEnLista(int codigo, JSONArray lista) {
        for (Object item : lista) {
            JSONObject obj = (JSONObject) item;
            if (((Long) obj.get("Codigo")).intValue() == codigo) {
                return obj;
            }
        }
        return null;
    }

    public void buscar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Qué desea buscar?");
        System.out.println("1. Producto");
        System.out.println("2. Artículo");
        int tipoBusqueda = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.println("Ingrese el código del producto o artículo:");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        JSONObject encontrado = null;

        if (tipoBusqueda == 1) {
            encontrado = buscarEnLista(codigo, listaProductos);
            if (encontrado != null) {
                System.out.println("Producto encontrado: " + encontrado);
            }
        } else if (tipoBusqueda == 2) {
            encontrado = buscarEnLista(codigo, listaArticulos);
            if (encontrado != null) {
                System.out.println("Artículo encontrado: " + encontrado);
            }
        }

        if (encontrado == null) {
            System.out.println("No se encontró el producto o artículo con el código proporcionado.");
            return;
        }

        // Presentar opciones al usuario
        System.out.println("Seleccione una opción:");
        System.out.println("1. Modificar");
        System.out.println("2. Eliminar");
        System.out.println("3. No hacer nada");
        int eleccion = scanner.nextInt();

        switch (eleccion) {
            case 1:
                modificar(encontrado);
                break;
            case 2:
                // Llamada a una función para eliminar (no implementada en este ejemplo)
                break;
            case 3:
                // No hacer nada
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
    


    public static void main(String[] args) {
        RegistroProductos registro = new RegistroProductos();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Agregar Artículo");
            System.out.println("3. Buscar Producto/Artículo");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción: ");

            int eleccion = scanner.nextInt();

            switch (eleccion) {
                case 1:
                    registro.agregarProducto();
                    break;
                case 2:
                    registro.agregarArticulo();
                    break;
                case 3:
                    registro.buscar();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor intente nuevamente.");
            }
        }
    }

}