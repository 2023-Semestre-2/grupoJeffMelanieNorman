/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupojeffmelanienorman;

/**
 *
 * @author Melanie
 */
public class VentanaDetalleElemento extends javax.swing.JFrame {

    private String elementoSeleccionado;

    public VentanaDetalleElemento(String elemento) {
        this.elementoSeleccionado = elemento;
        initComponents();
        jLabelElemento.setText(elemento);
    }

    private void initComponents() {
        jLabelElemento = new javax.swing.JLabel();
        jButtonModificar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        // Layout para la ventana y sus componentes
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelElemento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelElemento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {
        // Aquí el código para modificar el elemento
        System.out.println("Modificar " + elementoSeleccionado);
    }
    public void abrirVentanaModificarProducto(String elemento) {
        VentanaModificarProducto ventana = new VentanaModificarProducto(elemento);
        ventana.setVisible(true);
    }

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        // Aquí el código para eliminar el elemento
        abrirVentanaModificarProducto(elementoSeleccionado);
        System.out.println("Eliminar " + elementoSeleccionado);
    }

    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JLabel jLabelElemento;
}
