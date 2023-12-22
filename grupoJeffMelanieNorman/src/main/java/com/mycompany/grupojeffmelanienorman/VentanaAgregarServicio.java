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
 * VentanaAgregarServicio
 * 
 * Esta es la ventana para agregar un servicio de mantenimiento.
 *
 * @author Melanie
 */
public class VentanaAgregarServicio extends javax.swing.JFrame {
    /**
     * mapClientes para agregar un servicio de mantenimiento
     */
    Map<String, Cliente> mapClientes;
    
    /**
     * Creates new form VentanaAgregarServicio
     */
    public VentanaAgregarServicio() {
        mapClientes=new HashMap();
        
        initComponents();
        //agregarServicio(int codigoCliente, String marcaBicicleta, String descripcionBicicleta, int precio, String fechaRecibido, String fechaEntrega, String observaciones, String estado) {
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedInfo = (String) jComboBox1.getSelectedItem();
                if(mapClientes!=null){
                    Cliente selectedCliente = mapClientes.get(selectedInfo);
                    if(selectedCliente != null) {
                        String fechaR=jTextField5.getText().trim();
                        String fechaE=jTextField6.getText().trim();
                        if(ManejoDeCliente.fechaValida(fechaR) && ManejoDeCliente.fechaValida(fechaE)){
                            Mantenimiento mantenimiento=new Mantenimiento();
                            String marca=jTextField1.getText().trim();
                            String descripcion=jTextField2.getText().trim();
                           
                            String observaciones=jTextField4.getText().trim();
                            int codigoCliente=selectedCliente.getIdCliente();
                            int precio=0;
                            try{
                                precio=Integer.parseInt(jTextField3.getText());
                            }catch (Exception E){
                                JOptionPane.showMessageDialog(null, "Debe ingresar un precio valido.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            try{
                                mantenimiento.agregarServicio(codigoCliente, marca, descripcion, precio, fechaR, fechaE, observaciones, "Abierto");
                                JOptionPane.showMessageDialog(null, "Servicio de mantenimiento agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                            }catch(Exception E){
                                JOptionPane.showMessageDialog(null, "Error al agregar servicio de mantenimiento.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Debe ingresar fechas validas (dd/mm/yyyy).", "Error.", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Agregar Servicio de Mantenimiento");

        jLabel2.setText("Cliente:");

        jLabel3.setText("Marca de Bicicleta:");

        jLabel4.setText("Descripcion de Bicicleta:");

        jLabel5.setText("Precio:");

        jLabel6.setText("colones");

        jLabel7.setText("Fecha de Recibido:");

        jLabel8.setText("Fecha de Entrega:");

        jLabel9.setText("Observaciones:");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField5.setText("(dd/mm/yyyy)");

        jTextField6.setText("(dd/mm/yyyy)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jButton1))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /** 
     * jButton1ActionPerformed
     * @param args[] the command line arguments
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * main para iniciar el programa.
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
            java.util.logging.Logger.getLogger(VentanaAgregarServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaAgregarServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaAgregarServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAgregarServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAgregarServicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * jButton1 para agregar un servicio de mantenimiento
     */
    private javax.swing.JButton jButton1;
    /**
     * jComboBox1 para seleccionar un cliente
     */
    private javax.swing.JComboBox<String> jComboBox1;
    /**
     * jLabel1 para agregar un servicio de mantenimiento
     */
    private javax.swing.JLabel jLabel1;
    /**
     * jLabel2 para agregar un servicio de mantenimiento
     */
    private javax.swing.JLabel jLabel2;
    /**
     * jLabel3 para agregar un servicio de mantenimiento
     */
    private javax.swing.JLabel jLabel3;
    /**
     * jLabel4 para agregar un servicio de mantenimiento
     */
    private javax.swing.JLabel jLabel4;
    /**
     * jLabel5 para agregar un servicio de mantenimiento
     */
    private javax.swing.JLabel jLabel5;
    /**
     * jLabel6 para agregar un servicio de mantenimiento
     */
    private javax.swing.JLabel jLabel6;
    /**
     * jLabel7 para agregar un servicio de mantenimiento
     */
    private javax.swing.JLabel jLabel7;
    /**
     * jLabel8 para agregar un servicio de mantenimiento
     */
    private javax.swing.JLabel jLabel8;
    /**
     * jLabel9 para agregar un servicio de mantenimiento
     */
    private javax.swing.JLabel jLabel9;
    /**
     * jTextField1 para agregar un servicio de mantenimiento
     */
    private javax.swing.JTextField jTextField1;
    /**
     * jTextField2 para agregar un servicio de mantenimiento
     */
    private javax.swing.JTextField jTextField2;
    /**
     * jTextField3 para agregar un servicio de mantenimiento
     */
    private javax.swing.JTextField jTextField3;
    /**
     * jTextField4 para agregar un servicio de mantenimiento
     */
    private javax.swing.JTextField jTextField4;
    /**
     * jTextField5 para agregar un servicio de mantenimiento
     */
    private javax.swing.JTextField jTextField5;
    /**
     * jTextField6 para agregar un servicio de mantenimiento
     */
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
