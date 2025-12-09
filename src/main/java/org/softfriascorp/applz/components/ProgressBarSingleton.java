/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.components;

/**
 *
 * @author usuario
 */
import javax.swing.*;
import java.awt.*;

public class ProgressBarSingleton {

    private static ProgressBarSingleton instance;
    private final JProgressBar progressBar;

    private ProgressBarSingleton() {
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        // Estilo opcional
        progressBar.setPreferredSize(new Dimension(300, 25));
    }

    public static synchronized ProgressBarSingleton getInstance() {
        if (instance == null) {
            instance = new ProgressBarSingleton();
        }
        return instance;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgress(int value) {
        SwingUtilities.invokeLater(() -> progressBar.setValue(value));
    }

    public void setIndeterminate(boolean indeterminate) {
        SwingUtilities.invokeLater(() -> progressBar.setIndeterminate(indeterminate));
    }

    public void reset() {
        SwingUtilities.invokeLater(() -> {
            progressBar.setIndeterminate(false);
            progressBar.setValue(0);
        });
    }
}
