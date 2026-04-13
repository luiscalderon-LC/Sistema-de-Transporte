package vallegrande.edu.pe.sistematransporte.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Vista extends JFrame {

    public JComboBox<String> cbTipo;
    public JTextField txtMarca;
    public JTextField txtPlaca;
    public JTextField txtBuscar;

    public JButton btnAgregar;
    public JButton btnEliminar;
    public JButton btnActualizar;
    public JButton btnBuscar;
    public JButton btnMostrarTodos;

    public JTable tablaVehiculos;
    public DefaultTableModel modeloTabla;

    public JLabel lblTotal;

    public Vista() {
        setTitle("Sistema de Transporte");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        Color fondoGeneral = new Color(245, 247, 250);
        Color fondoPanel = Color.WHITE;
        Color azul = new Color(52, 152, 219);
        Color verde = new Color(46, 204, 113);
        Color rojo = new Color(231, 76, 60);
        Color naranja = new Color(243, 156, 18);
        Color grisTexto = new Color(44, 62, 80);

        Font fuenteTitulo = new Font("Segoe UI", Font.BOLD, 24);
        Font fuenteLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fuenteCampo = new Font("Segoe UI", Font.PLAIN, 14);
        Font fuenteBoton = new Font("Segoe UI Emoji", Font.BOLD, 14);

        getContentPane().setBackground(fondoGeneral);

        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(fondoGeneral);
        panelTitulo.setBorder(new EmptyBorder(15, 20, 0, 20));

        JLabel titulo = new JLabel("Sistema de Gestión de Vehículos");
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(grisTexto);
        panelTitulo.add(titulo, BorderLayout.WEST);

        lblTotal = new JLabel("Total de vehículos: 0");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotal.setForeground(azul);
        panelTitulo.add(lblTotal, BorderLayout.EAST);

        add(panelTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(15, 15));
        panelCentro.setBackground(fondoGeneral);
        panelCentro.setBorder(new EmptyBorder(0, 20, 20, 20));

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(fondoPanel);
        panelFormulario.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(fuenteLabel);
        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setFont(fuenteLabel);
        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setFont(fuenteLabel);
        JLabel lblBuscarMarca = new JLabel("Buscar por marca:");
        lblBuscarMarca.setFont(fuenteLabel);

        cbTipo = new JComboBox<>(new String[]{"Bus", "Taxi", "Motocicleta"});
        cbTipo.setFont(fuenteCampo);

        txtMarca = new JTextField();
        txtMarca.setFont(fuenteCampo);

        txtPlaca = new JTextField();
        txtPlaca.setFont(fuenteCampo);

        txtBuscar = new JTextField();
        txtBuscar.setFont(fuenteCampo);

        btnAgregar = new JButton("➕ Agregar");
        btnActualizar = new JButton("✏️ Actualizar");
        btnEliminar = new JButton("🗑️ Eliminar");
        btnBuscar = new JButton("🔍 Buscar");
        btnMostrarTodos = new JButton("📋 Mostrar todos");

        estilizarBoton(btnAgregar, verde, Color.WHITE, fuenteBoton);
        estilizarBoton(btnActualizar, naranja, Color.WHITE, fuenteBoton);
        estilizarBoton(btnEliminar, rojo, Color.WHITE, fuenteBoton);
        estilizarBoton(btnBuscar, azul, Color.WHITE, fuenteBoton);
        estilizarBoton(btnMostrarTodos, new Color(108, 117, 125), Color.WHITE, fuenteBoton);

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(lblTipo, gbc);
        gbc.gridx = 1;
        panelFormulario.add(cbTipo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(lblMarca, gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtMarca, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(lblPlaca, gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtPlaca, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(lblBuscarMarca, gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtBuscar, gbc);

        JPanel panelBotones = new JPanel(new GridLayout(1, 5, 10, 10));
        panelBotones.setBackground(fondoPanel);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnMostrarTodos);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(18, 8, 8, 8);
        panelFormulario.add(panelBotones, gbc);

        String[] columnas = {"Tipo", "Marca", "Placa"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaVehiculos = new JTable(modeloTabla);
        tablaVehiculos.setRowHeight(30);
        tablaVehiculos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaVehiculos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaVehiculos.getTableHeader().setBackground(azul);
        tablaVehiculos.getTableHeader().setForeground(Color.WHITE);
        tablaVehiculos.setSelectionBackground(new Color(174, 214, 241));
        tablaVehiculos.setGridColor(new Color(220, 220, 220));
        tablaVehiculos.setShowHorizontalLines(true);
        tablaVehiculos.setShowVerticalLines(false);

        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tablaVehiculos.getColumnCount(); i++) {
            tablaVehiculos.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }

        JScrollPane scrollPane = new JScrollPane(tablaVehiculos);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Listado de Vehículos"));
        scrollPane.getViewport().setBackground(Color.WHITE);

        panelCentro.add(panelFormulario, BorderLayout.NORTH);
        panelCentro.add(scrollPane, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);
    }

    private void estilizarBoton(JButton boton, Color fondo, Color texto, Font fuente) {
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setFocusPainted(false);
        boton.setFont(fuente);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
    }

    public void limpiarCampos() {
        cbTipo.setSelectedIndex(0);
        txtMarca.setText("");
        txtPlaca.setText("");
        txtMarca.requestFocus();
    }
}