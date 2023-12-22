/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.grupojeffmelanienorman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;

/**
 * VentanaModificarServicio
 * 
 * Esta clase se encarga de mostrar la ventana de modificar servicio
 *
 * @author Melanie
 */
public class VentanaModificarServicio extends javax.swing.JFrame {
    Map<String,Cliente>mapClientes;
    /**
     * Creates new form VentanaModificarServicio
     */
    public VentanaModificarServicio(JSONObject servicio) {
        mapClientes=null;
        initComponents();
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
                            String estado=(String) jComboBox2.getSelectedItem();
                            estado=estado.trim();
                            String observaciones=jTextField4.getText().trim();
                            String label=servicio.get("Codigo").toString();
                            jLabel11.setText(label);
                            int precio=0;
                            try{
                                precio=Integer.parseInt(jTextField3.getText());
                            }catch (Exception E){
                                JOptionPane.showMessageDialog(null, "Debe ingresar un precio valido.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            try{
                                mantenimiento.modificarServicio(servicio, selectedCliente, marca, descripcion, precio, fechaR, fechaE, observaciones, estado);
                                JOptionPane.showMessageDialog(null, "Servicio de mantenimiento modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                            }catch(Exception E){
                                JOptionPane.showMessageDialog(null, "Error al modificar servicio de mantenimiento.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Debe ingresar fechas validas (dd/mm/yyyy).", "Error.", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        ManejoDeCliente manejoClientes = new ManejoDeCliente();
        
        ArrayList<Cliente> clientes = manejoClientes.getClientes(); 
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
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Modificar servicio:");

        jLabel2.setText("Cliente:");

        jLabel3.setText("Marca de Bicicleta:");

        jLabel4.setText("Descripcion de Bicicleta:");

        jLabel5.setText("Precio:");

        jLabel6.setText("colones");

        jLabel7.setText("Fecha de Recibido:");

        jTextField4.setText("(dd/mm/yyyy)");

        jLabel8.setText("Fecha de Entrega: ");

        jTextField5.setText("(dd/mm/yyyy)");

        jLabel9.setText("Observaciones:");

        jButton1.setText("Modificar");

        jLabel10.setText("Estado:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Abierto", "Cerrado" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, 0, 200, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4)
                                    .addComponent(jTextField5)
                                    .addComponent(jTextField6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jButton1)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * main para iniciar el programa.
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Boton para modificar
     */
    private javax.swing.JButton jButton1;
    /**
     * ComboBox para seleccionar
     */
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    /**
     * Label para mostrar
     */
    private javax.swing.JLabel jLabel3;
    /**
     * Label para mostrar
     */
    private javax.swing.JLabel jLabel4;
    /**
     * Label para mostrar
     */
    private javax.swing.JLabel jLabel5;
    /**
     * Label para mostrar
     */
    private javax.swing.JLabel jLabel6;
    /**
     * Label para mostrar
     */
    private javax.swing.JLabel jLabel7;
    /**
     * Label para mostrar
     */
    private javax.swing.JLabel jLabel8;
    /**
     * Label para mostrar
     */
    private javax.swing.JLabel jLabel9;
    /**
     * TextField para ingresar
     */
    private javax.swing.JTextField jTextField1;
    /**
     * TextField para ingresar
     */
    private javax.swing.JTextField jTextField2;
    /**
     * TextField para ingresar
     */
    private javax.swing.JTextField jTextField3;
    /**
     * TextField para ingresar
     */
    private javax.swing.JTextField jTextField4;
    /**
     * TextField para ingresar
     */
    private javax.swing.JTextField jTextField5;
    /**
     * TextField para ingresar
     */
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
