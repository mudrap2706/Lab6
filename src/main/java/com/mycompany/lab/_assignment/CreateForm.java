/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab._assignment;

/**
 *
 * @author mudra
 */
import javax.swing.*;
import utils.DatabaseUtils;
import models.Product;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateForm extends JPanel {
    private DatabaseUtils dbUtils;
    private ViewForm viewForm;

    public CreateForm(DatabaseUtils dbUtils, ViewForm viewForm) {
        this.dbUtils = dbUtils;
        this.viewForm = viewForm;

        // UI setup with enhanced styling
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Set panel background color
        setBackground(new Color(250, 250, 250));

        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField nameField = new JTextField(20);
        
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField priceField = new JTextField(20);

        JButton addButton = new JButton("Add Product");
        addButton.setBackground(new Color(72, 133, 237));
        addButton.setForeground(Color.BLUE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                dbUtils.insertProduct(new Product(0, name, price));
                viewForm.populateTable(); // Refresh ViewForm
                JOptionPane.showMessageDialog(CreateForm.this, "Product added successfully!");
                nameField.setText(""); // Clear the input fields
                priceField.setText("");
            }
        });

        // Layout setup for components
        gbc.gridx = 0; gbc.gridy = 0;
        add(nameLabel, gbc);
        
        gbc.gridx = 1;
        add(nameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        add(priceLabel, gbc);
        
        gbc.gridx = 1;
        add(priceField, gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(addButton, gbc);
    }
}
