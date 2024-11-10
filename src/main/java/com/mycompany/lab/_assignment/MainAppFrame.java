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
import java.awt.*;
import utils.DatabaseUtils;

public class MainAppFrame extends JFrame {
    public MainAppFrame(DatabaseUtils dbUtils) {
        // Create a single instance of ViewForm
        ViewForm viewForm = new ViewForm(dbUtils);
        
        // Pass the ViewForm instance to CreateForm for refreshing the table
        CreateForm createForm = new CreateForm(dbUtils, viewForm);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Create Product", createForm);
        tabbedPane.addTab("View Products", viewForm);

        add(tabbedPane);

        // Set custom UI design for the frame
        setTitle("Product Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set background color for the main panel
        getContentPane().setBackground(new Color(245, 245, 245));
    }
}