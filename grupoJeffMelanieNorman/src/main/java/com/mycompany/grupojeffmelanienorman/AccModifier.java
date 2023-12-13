/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupojeffmelanienorman;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.ArrayList;

public class AccModifier {

    private static final String fileName = "usuarios.acc";

    /**
     * Escribe los datos de acceso de los usuarios en un archivo.
     * 
     * @param usuarios La lista de usuarios con sus datos de acceso.
     */
    public static void escribirArchivoAcc(ArrayList<ControlDeAcceso> usuarios) {
        
        RandomAccessFile raf = null;
        try {
            
            raf = new RandomAccessFile(fileName, "rw");
            
            int numUsuarios = usuarios.size();
            
            for (int i = 0; i < numUsuarios; i++) {
                ControlDeAcceso usuario = usuarios.get(i);
                String nombre = usuario.getUser();
                String contraseña = usuario.getPassword();
                raf.writeUTF(nombre + "," + contraseña + " ");
            }
            raf.close();
        } catch (IOException e) {
            // Ocurrió un error al escribir en el archivo
            e.printStackTrace();
        }
    }

    /**
     * Lee un archivo de acceso y devuelve una lista de objetos ControlDeAcceso.
     * 
     * @return La lista de objetos ControlDeAcceso leídos del archivo.
     */
    public static ArrayList<ControlDeAcceso> leerArchivoAcc() {
        RandomAccessFile raf = null;
        ArrayList<ControlDeAcceso> usuarios = new ArrayList<ControlDeAcceso>();
        try {
            raf = new RandomAccessFile(fileName, "r");
            byte[] signatureRead = new byte[4];
            raf.read(signatureRead);
            String hexSignature = bytesToHex(signatureRead);
            if (hexSignature.equals("41434331")) {
                String text = raf.readUTF();
                String[] lines = text.split(" ");
                for (String line : lines) {
                    String[] fields = line.split(",");
                    String nombre = fields[0];
                    String contraseña = fields[1];
                    ControlDeAcceso usuario = new ControlDeAcceso(nombre, contraseña);
                    usuarios.add(usuario);
                }
            } else {
                System.out.println("El archivo no es un archivo de usuarios y contraseñas");
            }
            raf.close();
        } catch (IOException e) {
            // Ocurrió un error al leer el archivo
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Agrega un nuevo usuario al ArrayList de ControlDeAcceso.
     * Si el usuario ya existe, muestra un mensaje de error.
     * 
     * @param usuarios el ArrayList de ControlDeAcceso donde se agregará el usuario
     * @param nombre el nombre del usuario a agregar
     * @param contraseña la contraseña del usuario a agregar
     */
    public static void agregarUsuario(ArrayList<ControlDeAcceso> usuarios, String nombre, String contraseña) {
        int indice = buscarUsuario(usuarios, nombre);
        if (indice == -1) {
            ControlDeAcceso usuario = new ControlDeAcceso(nombre, contraseña);
            usuarios.add(usuario);
            System.out.println("Usuario " + nombre + " agregado con éxito.");
        } else {
            System.out.println("El usuario " + nombre + " ya existe.");
        }
    }

    /**
     * Elimina un usuario de la lista de control de acceso.
     * 
     * @param usuarios La lista de control de acceso.
     * @param nombre El nombre del usuario a borrar.
     */
    public static void borrarUsuario(ArrayList<ControlDeAcceso> usuarios, String nombre) {
        int indice = buscarUsuario(usuarios, nombre);
        if (indice != -1) {
            usuarios.remove(indice);
            System.out.println("Usuario " + nombre + " borrado con éxito.");
        } else {
            System.out.println("El usuario " + nombre + " no existe.");
        }
    }

    /**
     * Verifica si el nombre de usuario y la contraseña proporcionados coinciden con los datos almacenados en la lista de usuarios.
     * 
     * @param usuarios la lista de usuarios registrados
     * @param nombre el nombre de usuario a verificar
     * @param contraseña la contraseña a verificar
     * @return true si el nombre de usuario y la contraseña coinciden, false de lo contrario
     */
    public static boolean verificarInicio(ArrayList<ControlDeAcceso> usuarios, String nombre, String contraseña) {
        int indice = buscarUsuario(usuarios, nombre);
        if (indice != -1) {
            ControlDeAcceso usuario = usuarios.get(indice);
            String contraseñaGuardada = usuario.getPassword();
            if (contraseña.equals(contraseñaGuardada)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Busca un usuario en la lista de usuarios y devuelve su índice.
     * 
     * @param usuarios La lista de usuarios.
     * @param nombre El nombre del usuario a buscar.
     * @return El índice del usuario en la lista, o -1 si no se encuentra.
     */
    private static int buscarUsuario(ArrayList<ControlDeAcceso> usuarios, String nombre) {
        int numUsuarios = usuarios.size();
        for (int i = 0; i < numUsuarios; i++) {
            ControlDeAcceso usuario = usuarios.get(i);
            String nombreGuardado = usuario.getUser();
            if (nombre.equals(nombreGuardado)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Convierte un arreglo de bytes en una cadena hexadecimal.
     * 
     * @param bytes el arreglo de bytes a convertir
     * @return la cadena hexadecimal resultante
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        // Crear un ArrayList de usuarios
        ArrayList<ControlDeAcceso> usuarios = new ArrayList<ControlDeAcceso>();
        // Agregar usuarios al ArrayList
        agregarUsuario(usuarios, "usuario1", "contraseña1");
        agregarUsuario(usuarios, "usuario2", "contraseña2");
        agregarUsuario(usuarios, "usuario3", "contraseña3");
        // Escribir el ArrayList en el archivo usuarios.acc
        escribirArchivoAcc(usuarios);
        // Leer el archivo usuarios.acc
        ArrayList<ControlDeAcceso> usuariosLeidos = leerArchivoAcc();
        // Mostrar los usuarios leídos
        System.out.println("Usuarios leídos:");
        for (ControlDeAcceso usuario : usuariosLeidos) {
            System.out.println(usuario.getUser() + " " + usuario.getPassword());
        }
        // Borrar un usuario del ArrayList
        borrarUsuario(usuarios, "usuario2");
        // Escribir el ArrayList en el archivo usuarios.acc
        escribirArchivoAcc(usuarios);
        // Leer el archivo usuarios.acc
        usuariosLeidos = leerArchivoAcc();
        // Mostrar los usuarios leídos
        System.out.println("Usuarios leídos:");
        for (ControlDeAcceso usuario : usuariosLeidos) {
            System.out.println(usuario.getUser() + " " + usuario.getPassword());
        }
        // Verificar el inicio de sesión
        boolean accesoValido = verificarInicio(usuarios, "usuario1", "contraseña1");
        if (accesoValido) {
            System.out.println("Acceso válido");
        } else {
            System.out.println("Acceso inválido");
        }
        accesoValido = verificarInicio(usuarios, "usuario1", "contraseña2");
        if (accesoValido) {
            System.out.println("Acceso válido");
        } else {
            System.out.println("Acceso inválido");
        }
        accesoValido = verificarInicio(usuarios, "usuario2", "contraseña2");
        if (accesoValido) {
            System.out.println("Acceso válido");
        } else {
            System.out.println("Acceso inválido");
        }
    }
}

