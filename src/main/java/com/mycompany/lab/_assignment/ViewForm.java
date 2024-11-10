/*viewform 
//Creates View page 
//JTable Component for Data Viewing
//Data Update and Delete Functionalit
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab._assignment;

/**
 *
 * @author mudra
 */
import utils.DatabaseUtils;
import models.Product;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewForm extends JPanel {
    private JTable productTable;
    private DatabaseUtils dbUtils;

    public ViewForm(DatabaseUtils dbUtils) {
        this.dbUtils = dbUtils;
        setLayout(new BorderLayout());

        // Set up the table with customized style
        String[] columnNames = {"ID", "Name", "Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(model);
        productTable.setFont(new Font("Arial", Font.PLAIN, 14));
        productTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for update and delete buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton updateButton = new JButton("Update Product");
        JButton deleteButton = new JButton("Delete Product");
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));

        updateButton.setBackground(new Color(255, 193, 7));
        updateButton.setForeground(Color.BLACK);

        deleteButton.setBackground(new Color(244, 67, 54));
        deleteButton.setForeground(Color.BLACK);

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        populateTable();

        // Action listener for the Update button
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) model.getValueAt(selectedRow, 0);
                    String name = (String) model.getValueAt(selectedRow, 1);
                    double price = (double) model.getValueAt(selectedRow, 2);
                    dbUtils.updateProduct(new Product(id, name, price));
                    populateTable(); // Refresh table
                } else {
                    JOptionPane.showMessageDialog(ViewForm.this, "Please select a product to update.");
                }
            }
        });

        // Action listener for the Delete button
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) model.getValueAt(selectedRow, 0);
                    dbUtils.deleteProduct(id);
                    populateTable(); // Refresh table
                } else {
                    JOptionPane.showMessageDialog(ViewForm.this, "Please select a product to delete.");
                }
            }
        });
    }

    // Populate the table with data from the database
    public void populateTable() {
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0); // Clear existing data
        List<Product> products = dbUtils.getAllProducts();
        for (Product product : products) {
            model.addRow(new Object[]{product.getId(), product.getName(), product.getPrice()});
        }
    }
}