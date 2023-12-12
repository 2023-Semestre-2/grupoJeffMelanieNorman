package com.mycompany.grupojeffmelanienorman;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RegistroProductos {

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
            listaArticulos = (JSONArray) obj.getOrDefault("Articulos", new JSONArray());
            actualizarUltimoCodigo(); // Actualizar el último código después de cargar los datos
        } catch (Exception e) {
            listaArticulos = new JSONArray();
        }
    }


    private void actualizarUltimoCodigo() {
        if (!listaArticulos.isEmpty()) {
            JSONObject ultimoArticulo = (JSONObject) listaArticulos.get(listaArticulos.size() - 1);
            ultimoCodigoArticulo = ((Long) ultimoArticulo.get("Codigo")).intValue();
        }
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
            obj.put("Articulos", listaArticulos); // Guarda la lista de artículos
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void modificar(JSONObject articulo) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Modificando artículo: " + articulo);
        System.out.println("Ingrese los nuevos valores (deje en blanco para no cambiar):");

        // Nombre
        System.out.println("Nombre (" + articulo.get("Nombre") + "): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            articulo.put("Nombre", nombre);
        }
        // Marca
        System.out.println("Marca (" + articulo.get("Marca") + "): ");
        String marca = scanner.nextLine();
        if (!marca.isEmpty()) {
            articulo.put("Marca", marca);
        }
        // Cantidad
        System.out.println("Cantidad (" + articulo.get("Cantidad") + "): ");
        String cantidadStr = scanner.nextLine();
        if (!cantidadStr.isEmpty()) {
            try {
                int cantidad = Integer.parseInt(cantidadStr);
                articulo.put("Cantidad", cantidad);
            } catch (NumberFormatException e) {
                System.out.println("Cantidad no válida, se mantiene el valor anterior.");
            }
        }
        // Precio
        System.out.println("Precio (" + articulo.get("Precio") + "): ");
        String precioStr = scanner.nextLine();
        if (!precioStr.isEmpty()) {
            try {
                int precio = Integer.parseInt(precioStr);
                articulo.put("Precio", precio);
            } catch (NumberFormatException e) {
                System.out.println("Precio no válido, se mantiene el valor anterior.");
            }
        }
        // Tipo
        System.out.println("Tipo (" + articulo.get("Tipo") + "): ");
        String tipo = scanner.nextLine();
        if (!tipo.isEmpty()) {
            articulo.put("Tipo", tipo);
        }
        // Tamaño
        System.out.println("Tamaño (" + articulo.get("Tamaño") + "): ");
        String tamaño = scanner.nextLine();
        if (!tamaño.isEmpty()) {
            articulo.put("Tamaño", tamaño);
        }

        guardarDatos(); // Guardar los cambios en el archivo JSON

        System.out.println("Modificación completada: " + articulo);
    }

    public void buscar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el criterio de búsqueda:");
        System.out.println("1. Por Código");
        System.out.println("2. Por Nombre");
        int criterioBusqueda = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        JSONObject encontrado = null;

        if (criterioBusqueda == 1) {
            System.out.println("Ingrese el código del artículo:");
            int codigo = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            encontrado = buscarPorCodigo(codigo, listaArticulos);
        } else if (criterioBusqueda == 2) {
            System.out.println("Ingrese el nombre del artículo:");
            String nombre = scanner.nextLine();
            encontrado = buscarPorNombre(nombre, listaArticulos);
        }
        if (encontrado != null) {
            System.out.println("Artículo encontrado: " + encontrado);

            // Presentar opciones al usuario
            System.out.println("Seleccione una opción:");
            System.out.println("1. Modificar");
            System.out.println("2. Eliminar");
            System.out.println("3. No hacer nada");
            int eleccion = scanner.nextInt();

            switch (eleccion) {
                case 1:
                    modificar(encontrado); //Le paso "encontrado" que es el articulo a modificar
                    break;
                case 2:
                    // Llamada a una función para eliminar (a implementar)
                    break;
                case 3:
                    // No hacer nada
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } else {
            System.out.println("No se encontró el artículo con el código proporcionado.");
        }
    }
    
    private JSONObject buscarPorCodigo(int codigo, JSONArray lista) {
        for (Object item : lista) {
            JSONObject obj = (JSONObject) item;
            if (((Number) obj.get("Codigo")).intValue() == codigo) {
                return obj;
            }
        }
        return null;
    }
    
    private JSONObject buscarPorNombre(String nombre, JSONArray lista) {
        for (Object item : lista) {
            JSONObject obj = (JSONObject) item;
            if (obj.get("Nombre").toString().equalsIgnoreCase(nombre)) {
                return obj;
            }
        }
        return null;
    }

   

    public static void main(String[] args) {
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
    }
}