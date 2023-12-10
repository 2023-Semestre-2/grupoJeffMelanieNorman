/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupojeffmelanienorman;

/**
 *
 * @author XPC
 */
public class ControlDeAcceso {
    // Atributos
    private String user;
    private String password;

    // Constructor
    public ControlDeAcceso(String user, String password) {
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

    /**
     * Comprueba si el usuario y la contraseña son correctos.
     * 
     * @param user el usuario a comprobar
     * @param password la contraseña a comprobar
     * @return true si el usuario y la contraseña son correctos, false en caso
     * contrario
     */
    public boolean check(String user, String password) {
        return this.user.equals(user) && this.password.equals(password);
    }
}
