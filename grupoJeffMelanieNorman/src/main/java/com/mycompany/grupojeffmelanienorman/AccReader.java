/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupojeffmelanienorman;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;

public class AccReader {

    private static final String fileName = "usuarios.acc";
    private ArrayList<Usuario> usuarios;

    /**
     * Lee un archivo de acceso y devuelve una lista de objetos Usuario.
     * 
     * @return La lista de objetos Usuario leídos del archivo.
     */
    public static ArrayList<Usuario> leerArchivoAcc() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            System.out.println("Leyendo archivo " + fileName);
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            System.out.println("Archivo abierto");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String usuario = parts[0];
                    String contraseña = parts[1];
                    usuarios.add(new Usuario(usuario, contraseña));
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }

        return usuarios;
    }

    /**
     * Verifica si el nombre de usuario y la contraseña proporcionados coinciden con los datos almacenados en la lista de usuarios.
     * 
     * @param usuarios la lista de usuarios registrados
     * @param nombre el nombre de usuario a verificar
     * @param contraseña la contraseña a verificar
     * @return true si el nombre de usuario y la contraseña coinciden, false de lo contrario
     */
    public static boolean verificarInicio(ArrayList<Usuario> usuarios, String nombre, String contraseña) {
        int indice = buscarUsuario(usuarios, nombre);
        if (indice != -1) {
            Usuario usuario = usuarios.get(indice);
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
    private static int buscarUsuario(ArrayList<Usuario> usuarios, String nombre) {
        int numUsuarios = usuarios.size();
        for (int i = 0; i < numUsuarios; i++) {
            Usuario usuario = usuarios.get(i);
            String nombreGuardado = usuario.getUser();
            if (nombre.equals(nombreGuardado)) {
                return i;
            }
        }
        return -1;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = leerArchivoAcc();
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

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void main(String[] args) {
        ArrayList<Usuario> usuarios = leerArchivoAcc();
        for (Usuario usuario : usuarios) {
            System.out.println("User: "+usuario.getUser() + "\nPassword: " + usuario.getPassword());
        }
        boolean inicioCorrecto = verificarInicio(usuarios, "Jeff", "1234");
        System.out.println("Inicio de sesión con el usuario Jeff y la contraseña 1234: " + inicioCorrecto);
        inicioCorrecto = verificarInicio(usuarios, "Jana", "1711");
        System.out.println("Inicio de sesión con el usuario Jana y la contraseña 1711: " + inicioCorrecto);
        boolean inicioIncorrecto = verificarInicio(usuarios, "Jeffry", "1234");
        System.out.println("Inicio de sesión con el usuario Jeffry y la contraseña 1234: " + inicioIncorrecto);
        inicioIncorrecto = verificarInicio(usuarios, "Jeff", "12345");
        System.out.println("Inicio de sesión con el usuario Jeff y la contraseña 12345: " + inicioIncorrecto);
    }
}

