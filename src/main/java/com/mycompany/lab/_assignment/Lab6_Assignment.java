/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lab._assignment;

/**
 *
 * @author mudra
 */
//Initial Setup and Database Connection

import utils.DatabaseUtils;
import javax.swing.SwingUtilities;

public class Lab6_Assignment {
    public static void main(String[] args) {
        DatabaseUtils dbUtils = new DatabaseUtils();

        try {
            dbUtils.connect();
            SwingUtilities.invokeLater(() -> {
                MainAppFrame app = new MainAppFrame(dbUtils);
                app.setVisible(true);
            });
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Runtime.getRuntime().addShutdownHook(new Thread(dbUtils::close));
        }
    }
}