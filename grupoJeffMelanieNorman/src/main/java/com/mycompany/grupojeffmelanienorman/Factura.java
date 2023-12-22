/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupojeffmelanienorman;

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
    private int subTotal;
    private int total = subTotal + (subTotal * IMPUESTO / 100);
    private boolean estado = true;

    /**
     * Constructor para la clase Factura.
     * @param idFactura El identificador único de la factura.
     * @param subTotal El subtotal de la factura.
     * @param estado El estado de la factura.
     */
    public Factura(int idFactura, int subTotal, boolean estado) {
        this.idFactura = idFactura;
        this.subTotal = subTotal;
        this.estado = estado;
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
    public int getTotal() {
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
}
