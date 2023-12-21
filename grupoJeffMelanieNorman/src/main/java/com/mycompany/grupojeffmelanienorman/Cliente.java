/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupojeffmelanienorman;

import java.util.ArrayList;

/**
 *
 * @author Jeffry
 */
public class Cliente {
    // Atributos
    private int idCliente;
    private String nombre;
    private String apellido;
    private String provincia;
    private String canton;
    private String distrito;
    private String telefono; 
    private String email;
    private String fechaDeNacimiento;
    private ArrayList<Factura> facturas = new ArrayList<Factura>();

    /**
     * @param idCliente
     * @param nombre
     * @param apellido
     * @param provincia
     * @param canton
     * @param distrito
     * @param telefono
     * @param email
     * @param fechaDeNacimiento
     * @param facturas 
     */
    public Cliente(int idCliente, String nombre, String apellido, String provincia, String canton, String distrito, String telefono, String email, String fechaDeNacimiento, ArrayList<Factura> facturas) {
        this.idCliente = idCliente;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.telefono = telefono;
        this.email = email;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.facturas = facturas;
    }

    /**
     * Devuelve el nombre del cliente.
     *
     * @return el nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     * 
     * @param nombre el nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del cliente.
     * 
     * @return el apellido del cliente
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del cliente.
     * 
     * @param apellido el apellido del cliente
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Devuelve la provincia del cliente.
     * 
     * @return la provincia del cliente
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Establece la provincia del cliente.
     * 
     * @param provincia la provincia del cliente
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Devuelve el cantón del cliente.
     * 
     * @return el cantón del cliente
     */
    public String getCanton() {
        return canton;
    }

    /**
     * Establece el cantón del cliente.
     * 
     * @param canton el cantón del cliente
     */
    public void setCanton(String canton) {
        this.canton = canton;
    }

    /**
     * Devuelve el distrito del cliente.
     * 
     * @return el distrito del cliente
     */
    public String getDistrito() {
        return distrito;
    }

    /**
     * Establece el distrito del cliente.
     * 
     * @param distrito el distrito del cliente
     */
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     * 
     * @return el número de teléfono del cliente
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del cliente.
     * 
     * @param telefono el número de teléfono a establecer
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Devuelve el correo electrónico del cliente.
     * 
     * @return el correo electrónico del cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del cliente.
     * 
     * @param email el correo electrónico a establecer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la fecha de nacimiento del cliente.
     * 
     * @return la fecha de nacimiento del cliente
     */
    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del cliente.
     * 
     * @param fechaDeNacimiento la fecha de nacimiento a establecer
     */
    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    /**
     * @return 
     */
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    /**
     * @return facturas
     */
    public ArrayList<Factura> getFacturas() {
        return facturas;
    }
    /**
     * 
     * @param facturas 
     */
    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }
}
