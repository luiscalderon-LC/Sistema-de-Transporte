package vallegrande.edu.pe.sistematransporte;

import vallegrande.edu.pe.sistematransporte.view.Vista;
import vallegrande.edu.pe.sistematransporte.controller.VehiculoController;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Vista vista = new Vista();
            new VehiculoController(vista);
            vista.setVisible(true);
        });
    }
}