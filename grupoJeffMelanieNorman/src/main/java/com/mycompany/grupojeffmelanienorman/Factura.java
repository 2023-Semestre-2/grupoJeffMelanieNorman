/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupojeffmelanienorman;

import org.json.JSONObject;

/**
 * Factura
 * 
 * Esta clase se encarga de manejar las facturas
 * 
 * @author Jeffry
 */
public class Factura {
    // Atributos
    private int idFactura;
    private final int IMPUESTO = 13;
    private long impuesto=0;
    private int subTotal;
    private long total = 0;
    private boolean estado;
    private JSONObject mantenimiento;

    public long getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(long impuesto) {
        this.impuesto = impuesto;
    }

    public int getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getFechaR() {
        return fechaR;
    }

    public void setFechaR(String fechaR) {
        this.fechaR = fechaR;
    }
    private int codigoServicio;
    private int precioUnitario;
    private int codigoCliente;
    private String fechaR;
    

    /**
     * Constructor para la clase Factura.
     * @param idFactura El identificador único de la factura.
     * @param subTotal El subtotal de la factura.
     * @param estado El estado de la factura.
     * @param mantenimiento El mantenimiento asociado a la factura.
     */
    public Factura(int idFactura, String fecha, boolean estado, JSONObject mantenimiento) {
        this.estado=true;
        this.idFactura = idFactura;
        this.mantenimiento = mantenimiento;
        this.subTotal=(int) mantenimiento.get("Precio");
        this.impuesto=subTotal*IMPUESTO/100;
        this.total = subTotal + impuesto;
        this.codigoServicio=(int) mantenimiento.get("Codigo");
        this.precioUnitario=subTotal;
        this.codigoCliente=(int) mantenimiento.get("Codigo Cliente");
        this.fechaR=fecha;
    }

    /**
     * Obtiene el ID de la factura.
     * @return el ID de la factura.
     */
    public int getIdFactura() {
        return idFactura;
    }

    /**
     * Establece el ID de la factura.
     * @param idFactura el ID de la factura a establecer
     */
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    /**
     * Devuelve el total de la factura.
     * @return el total de la factura
     */
    public long getTotal() {
        return total;
    }

    /**
     * Devuelve el subtotal de la factura.
     * @return el subtotal de la factura.
     */
    public int getSubTotal() {
        return subTotal;
    }

    /**
     * Establece el subtotal de la factura.
     * @param subTotal el subtotal de la factura
     */
    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * Devuelve el estado de la factura.
     * @return true si la factura está en estado activo, false si está en estado inactivo.
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Establece el estado de la factura.
     * @param estado el estado de la factura
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Devuelve el mantenimiento asociado a la factura.
     * @return el mantenimiento asociado a la factura
     */
    public JSONObject getMantenimiento() {
        return mantenimiento;
    }

    /**
     * Establece el mantenimiento asociado a la factura.
     * @param mantenimiento el mantenimiento asociado a la factura
     */
    public void setMantenimiento(JSONObject mantenimiento) {
        this.mantenimiento = mantenimiento;
    }
}
