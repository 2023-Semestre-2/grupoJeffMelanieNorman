/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.grupojeffmelanienorman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 * VentanaClientes
 * 
 * Esta es la ventana que se encarga de mostrar los clientes registrados en el sistema.
 *
 * @author Melanie
 */
public class VentanaClientes extends javax.swing.JFrame {
    /**
     * Mapa de clientes
     */
    Map<String, Cliente> mapClientes;
    /**
     * Cliente actual
     */
    Cliente clienteActual;
    /**
     * Creates new form VentanaClientes
     */
    public VentanaClientes() {
        clienteActual=null;
        mapClientes=new HashMap();
        initComponents();
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaCrearUsuario ventana=new VentanaCrearUsuario();
                ventana.setVisible(true);
                
            }
        });
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton3.setVisible(true);
                        jButton4.setVisible(true);
                String selectedInfo = (String) jComboBox1.getSelectedItem();
                if(mapClientes!=null){
                    Cliente selectedCliente = mapClientes.get(selectedInfo);
                    if(selectedCliente != null) {
                        clienteActual=selectedCliente;
                        jButton3.setVisible(true);
                        jButton4.setVisible(true);
                    }
                }
            }
        });
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(clienteActual!=null){
                    VentanaModificarCliente ventana=new VentanaModificarCliente(clienteActual);
                    ventana.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente.", "Error.", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        jButton4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(clienteActual!=null){
                    String mensaje="Desea eliminar al cliente: "+clienteActual.getNombre()+", codigo: "+String.valueOf(clienteActual.getIdCliente())+"?";
                    int opcion=JOptionPane.showConfirmDialog(null, mensaje, "Confirmacion de eliminar.", JOptionPane.YES_NO_OPTION);
                    if(opcion==JOptionPane.YES_OPTION){
                        ManejoDeCliente manejo=new ManejoDeCliente();
                        manejo.eliminarCliente(clienteActual.getIdCliente());
                    }else{
                        JOptionPane.showMessageDialog(null, "Cancelado.", "Cancelado.", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente.", "Error.", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jComboBox1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jButton3.setVisible(false);
                jButton4.setVisible(false);
            }
        });
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Registro Clientes");

        jButton1.setText("Seleccionar");

        jButton2.setText("Agregar");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton3.setText("Modificar");
        jButton3.setVisible(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar");
        jButton4.setVisible(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        ManejoDeCliente manejoClientes = new ManejoDeCliente();
        ArrayList<Cliente> clientes = manejoClientes.getClientes(); // Asumiendo que hay un método getClientes
        if(mapClientes!=null){
            mapClientes.clear();
            for(Cliente cliente : clientes) {
                String clienteInfo = cliente.getIdCliente() + " - " + cliente.getNombre();
                jComboBox1.addItem(clienteInfo);
                mapClientes.put(clienteInfo, cliente);
            }
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(86, 86, 86)
                                .addComponent(jButton4))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(0, 43, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)))
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /** 
     * Este método se encarga de seleccionar un cliente.
     * @param args[] Acciona el botón de seleccionar.
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    
    /** 
     * Este método se encarga de eliminar un cliente.
     * @param args[] Acciona el botón de eliminar.
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * Este método se encarga de agregar un cliente.
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Boton para agregar
     */
    private javax.swing.JButton jButton1;
    /**
     * Boton para agregar
     */
    private javax.swing.JButton jButton2;
    /**
     * Boton para agregar
     */
    private javax.swing.JButton jButton3;
    /**
     * Boton para agregar
     */
    private javax.swing.JButton jButton4;
    /**
     * ComboBox para seleccionar
     */
    private javax.swing.JComboBox<String> jComboBox1;
    /**
     * Label para mostrar
     */
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
