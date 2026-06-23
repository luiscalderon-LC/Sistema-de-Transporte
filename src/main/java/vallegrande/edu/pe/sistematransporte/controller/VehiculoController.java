package vallegrande.edu.pe.sistematransporte.controller;

import vallegrande.edu.pe.sistematransporte.model.Vehiculo;
import vallegrande.edu.pe.sistematransporte.view.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class VehiculoController {
    // Mejora realizada por Luis para optimizar el control de vehículos
    private Vista vista;
    private ArrayList<Vehiculo> listaVehiculos;
    private int filaSeleccionada = -1;

    public VehiculoController(Vista vista) {
        this.vista = vista;
        this.listaVehiculos = new ArrayList<>();

        this.vista.btnAgregar.addActionListener(e -> agregarVehiculo());
        this.vista.btnEliminar.addActionListener(e -> eliminarVehiculo());
        this.vista.btnActualizar.addActionListener(e -> actualizarVehiculo());
        this.vista.btnBuscar.addActionListener(e -> buscarPorMarca());
        this.vista.btnMostrarTodos.addActionListener(e -> mostrarVehiculos());

        this.vista.tablaVehiculos.getSelectionModel().addListSelectionListener(e -> cargarDatosSeleccionados());

        mostrarVehiculos();
    }

    private void agregarVehiculo() {
        String tipo = vista.cbTipo.getSelectedItem().toString();
        String marca = vista.txtMarca.getText().trim();
        String placa = vista.txtPlaca.getText().trim();

        if (marca.isEmpty() || placa.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Completa todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Vehiculo vehiculo = new Vehiculo(tipo, marca, placa);
        listaVehiculos.add(vehiculo);

        mostrarVehiculos();
        vista.limpiarCampos();

        JOptionPane.showMessageDialog(vista, "Vehículo registrado correctamente.");
    }

    private void eliminarVehiculo() {
        int fila = vista.tablaVehiculos.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona un registro para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                vista,
                "¿Seguro que deseas eliminar este vehículo?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            listaVehiculos.remove(fila);
            mostrarVehiculos();
            vista.limpiarCampos();
            filaSeleccionada = -1;
            JOptionPane.showMessageDialog(vista, "Vehículo eliminado correctamente.");
        }
    }

    private void actualizarVehiculo() {
        int fila = vista.tablaVehiculos.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona un registro para actualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String tipo = vista.cbTipo.getSelectedItem().toString();
        String marca = vista.txtMarca.getText().trim();
        String placa = vista.txtPlaca.getText().trim();

        if (marca.isEmpty() || placa.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Completa todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Vehiculo vehiculo = listaVehiculos.get(fila);
        vehiculo.setTipo(tipo);
        vehiculo.setMarca(marca);
        vehiculo.setPlaca(placa);

        mostrarVehiculos();
        vista.limpiarCampos();
        filaSeleccionada = -1;

        JOptionPane.showMessageDialog(vista, "Vehículo actualizado correctamente.");
    }

    private void buscarPorMarca() {
        String textoBusqueda = vista.txtBuscar.getText().trim().toLowerCase();

        DefaultTableModel modelo = vista.modeloTabla;
        modelo.setRowCount(0);

        if (textoBusqueda.isEmpty()) {
            mostrarVehiculos();
            return;
        }

        for (Vehiculo v : listaVehiculos) {
            if (v.getMarca().toLowerCase().contains(textoBusqueda)) {
                modelo.addRow(new Object[]{v.getTipo(), v.getMarca(), v.getPlaca()});
            }
        }
    }

    private void cargarDatosSeleccionados() {
        int fila = vista.tablaVehiculos.getSelectedRow();

        if (fila != -1 && fila < listaVehiculos.size()) {
            filaSeleccionada = fila;
            Vehiculo vehiculo = listaVehiculos.get(fila);

            vista.cbTipo.setSelectedItem(vehiculo.getTipo());
            vista.txtMarca.setText(vehiculo.getMarca());
            vista.txtPlaca.setText(vehiculo.getPlaca());
        }
    }

    private void mostrarVehiculos() {
        DefaultTableModel modelo = vista.modeloTabla;
        modelo.setRowCount(0);

        for (Vehiculo v : listaVehiculos) {
            modelo.addRow(new Object[]{v.getTipo(), v.getMarca(), v.getPlaca()});
        }

        vista.lblTotal.setText("Total de vehículos: " + listaVehiculos.size());
    }
}