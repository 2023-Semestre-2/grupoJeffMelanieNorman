/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.grupojeffmelanienorman;

import org.json.simple.JSONObject;

/**
 * VentanaDetalleProducto
 * 
 * Clase que muestra los detalles de un producto.
 *
 * @author Melanie
 */
public class VentanaDetalleProducto extends javax.swing.JFrame {

    /**
     * Creates new form VentanaDetalleProducto
     * @param producto producto del cual se quieren ver los detalles
     */
    public VentanaDetalleProducto(JSONObject producto) {
        
        initComponents();
        jLabel3.setText((String) producto.get("Nombre"));
        jLabel5.setText((String) producto.get("Marca"));
        String tipo=(String) producto.get("Tipo");
        jLabel7.setText(tipo);
        jLabel9.setText(String.valueOf(((Long) producto.get("Codigo Tipo")).intValue()));
        if(tipo.equals("Bicicleta")){
            jLabel10.setVisible(true);
            jLabel11.setVisible(true);
            jLabel11.setText((String) producto.get("Tamaño"));
        }
        jLabel13.setText(String.valueOf(producto.get("Cantidad")));
        jLabel15.setText(String.valueOf(producto.get("Precio")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Detalle Producto Seleccionado");

        jLabel2.setText("Nombre:");

        jLabel3.setText("(nombre)");

        jLabel4.setText("Marca:");

        jLabel5.setText("(marca)");

        jLabel6.setText("Tipo de Producto:");

        jLabel7.setText("(tipo)");

        jLabel8.setText("Codigo de Producto:");

        jLabel9.setText("(codigo)");

        jLabel10.setText("Tamaño:");

        jLabel11.setText("(tamano)");

        jLabel12.setText("Cantidad:");

        jLabel13.setText("(cantidad)");

        jLabel14.setText("Precio:");

        jLabel15.setText("(precio)");

        jLabel16.setText("colones");

        jLabel10.setVisible(false);
        jLabel11.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * jLabel1
     */
    private javax.swing.JLabel jLabel1;
    /**
     * jLabel10
     */
    private javax.swing.JLabel jLabel10;
    /**
     * jLabel11
     */
    private javax.swing.JLabel jLabel11;
    /**
     * jLabel12
     */
    private javax.swing.JLabel jLabel12;
    /**
     * jLabel13
     */
    private javax.swing.JLabel jLabel13;
    /**
     * jLabel14
     */
    private javax.swing.JLabel jLabel14;
    /**
     * jLabel15
     */
    private javax.swing.JLabel jLabel15;
    /**
     * jLabel16
     */
    private javax.swing.JLabel jLabel16;
    /**
     * jLabel2
     */
    private javax.swing.JLabel jLabel2;
    /**
     * jLabel3
     */
    private javax.swing.JLabel jLabel3;
    /**
     * jLabel4
     */
    private javax.swing.JLabel jLabel4;
    /**
     * jLabel5
     */
    private javax.swing.JLabel jLabel5;
    /**
     * jLabel6
     */
    private javax.swing.JLabel jLabel6;
    /**
     * jLabel7
     */
    private javax.swing.JLabel jLabel7;
    /**
     * jLabel8
     */
    private javax.swing.JLabel jLabel8;
    /**
     * jLabel9
     */
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
