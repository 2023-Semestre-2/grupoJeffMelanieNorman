/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupojeffmelanienorman;

/**
 *
 * @author XPC
 */
public class Usuario {
    // Atributos
    private String user;
    private String password;
    

    // Constructor
    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     * Devuelve el usuario.
     * 
     * @return el usuario
     */
    public String getUser() {
        return user;
    }

    /**
     * Establece el usuario para el control de acceso.
     * 
     * @param user el nombre de usuario a establecer
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Devuelve la contraseña.
     * 
     * @return la contraseña como una cadena de caracteres.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña para el control de acceso.
     * 
     * @param password la contraseña a establecer
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
