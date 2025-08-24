package vista;

import controlador.ControladorPrincipal;
import modelo.Vehiculo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Ventana para gestionar vehículos del sistema (visualizar, agregar, modificar)
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public class VentanaGestionVehiculos extends JDialog {

    private final ControladorPrincipal controlador;

    // Componentes de la tabla
    private JTable tablaVehiculos;
    private DefaultTableModel modeloTabla;

    // Componentes del formulario
    private JTextField txtPatente;
    private JTextField txtMarca;
    private JComboBox<String> cmbCondicion;

    // Botones
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnActualizar;

    // Variables de control
    private int filaSeleccionada = -1;
    private boolean modoEdicion = false;

    /**
     * Constructor de la ventana de gestión de vehículos
     *
     * @param parent Ventana padre
     * @param controlador Controlador principal
     */
    public VentanaGestionVehiculos(JFrame parent, ControladorPrincipal controlador) {
        super(parent, "Gestión de Vehículos - Car-REnt", true);
        this.controlador = controlador;

        initializeComponents();
        setupLayout();
        setupEvents();
        setWindowProperties();
        cargarDatos();
    }

    /**
     * Inicializa los componentes de la interfaz
     */
    private void initializeComponents() {
        // Crear modelo de tabla
        String[] columnas = {"Patente", "Marca", "Condición", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable directamente
            }
        };

        // Crear tabla
        tablaVehiculos = new JTable(modeloTabla);
        tablaVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaVehiculos.getTableHeader().setReorderingAllowed(false);

        // Ajustar ancho de columnas
        tablaVehiculos.getColumnModel().getColumn(0).setPreferredWidth(100); // Patente
        tablaVehiculos.getColumnModel().getColumn(1).setPreferredWidth(150); // Marca
        tablaVehiculos.getColumnModel().getColumn(2).setPreferredWidth(80);  // Condición
        tablaVehiculos.getColumnModel().getColumn(3).setPreferredWidth(120); // Estado

        // Crear componentes del formulario con tamaños fijos
        txtPatente = new JTextField();
        txtPatente.setPreferredSize(new Dimension(150, 25));
        txtPatente.setMinimumSize(new Dimension(150, 25));
        txtPatente.setMaximumSize(new Dimension(150, 25));

        txtMarca = new JTextField();
        txtMarca.setPreferredSize(new Dimension(200, 25));
        txtMarca.setMinimumSize(new Dimension(200, 25));
        txtMarca.setMaximumSize(new Dimension(200, 25));

        // ComboBox para condición del vehículo
        String[] condiciones = {"D - Disponible", "A - Arrendado", "M - Mantenimiento"};
        cmbCondicion = new JComboBox<>(condiciones);
        cmbCondicion.setPreferredSize(new Dimension(150, 25));
        cmbCondicion.setMinimumSize(new Dimension(150, 25));
        cmbCondicion.setMaximumSize(new Dimension(150, 25));

        // Crear botones con tamaños fijos
        btnAgregar = new JButton("Agregar Vehículo");
        btnAgregar.setPreferredSize(new Dimension(140, 30));
        btnAgregar.setMinimumSize(new Dimension(140, 30));

        btnModificar = new JButton("Modificar Vehículo");
        btnModificar.setPreferredSize(new Dimension(140, 30));
        btnModificar.setMinimumSize(new Dimension(140, 30));

        btnEliminar = new JButton("Eliminar Vehículo");
        btnEliminar.setPreferredSize(new Dimension(140, 30));
        btnEliminar.setMinimumSize(new Dimension(140, 30));

        btnLimpiar = new JButton("Limpiar Formulario");
        btnLimpiar.setPreferredSize(new Dimension(140, 30));
        btnLimpiar.setMinimumSize(new Dimension(140, 30));

        btnActualizar = new JButton("Actualizar Lista");
        btnActualizar.setPreferredSize(new Dimension(140, 30));
        btnActualizar.setMinimumSize(new Dimension(140, 30));

        // Configurar estado inicial de botones
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    /**
     * Configura el layout de la ventana
     */
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(25, 25, 112));
        JLabel lblTitulo = new JLabel("GESTIÓN DE VEHÍCULOS", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);

        // Panel central dividido en dos partes
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel izquierdo - Tabla de vehículos
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Lista de Vehículos"));

        JScrollPane scrollTabla = new JScrollPane(tablaVehiculos);
        scrollTabla.setPreferredSize(new Dimension(450, 300));
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        JPanel panelBotonesTabla = new JPanel(new FlowLayout());
        panelBotonesTabla.add(btnActualizar);
        panelTabla.add(panelBotonesTabla, BorderLayout.SOUTH);

        // Panel derecho - Formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Vehículo"));
        panelFormulario.setPreferredSize(new Dimension(300, 300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Campo Patente
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(new JLabel("Patente:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtPatente, gbc);

        // Campo Marca
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(new JLabel("Marca:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtMarca, gbc);

        // Campo Condición
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(new JLabel("Condición:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(cmbCondicion, gbc);

        // Panel de botones del formulario
        JPanel panelBotonesForm = new JPanel(new GridLayout(2, 2, 5, 5));
        panelBotonesForm.add(btnAgregar);
        panelBotonesForm.add(btnModificar);
        panelBotonesForm.add(btnEliminar);
        panelBotonesForm.add(btnLimpiar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 5, 5, 5);
        panelFormulario.add(panelBotonesForm, gbc);

        // Agregar paneles al centro
        panelCentral.add(panelTabla, BorderLayout.CENTER);
        panelCentral.add(panelFormulario, BorderLayout.EAST);

        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior con información
        JPanel panelInfo = new JPanel();
        panelInfo.setBorder(BorderFactory.createEtchedBorder());
        JLabel lblInfo = new JLabel("Seleccione un vehículo de la tabla para modificar o eliminar");
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 12));
        panelInfo.add(lblInfo);
        add(panelInfo, BorderLayout.SOUTH);
    }

    /**
     * Configura los eventos de los componentes
     */
    private void setupEvents() {
        // Evento de selección en la tabla
        tablaVehiculos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                filaSeleccionada = tablaVehiculos.getSelectedRow();
                if (filaSeleccionada != -1) {
                    cargarDatosEnFormulario();
                    btnModificar.setEnabled(true);
                    btnEliminar.setEnabled(true);
                    modoEdicion = true;
                } else {
                    limpiarFormulario();
                    btnModificar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    modoEdicion = false;
                }
            }
        });

        // Evento del botón Agregar
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarVehiculo();
            }
        });

        // Evento del botón Modificar
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarVehiculo();
            }
        });

        // Evento del botón Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarVehiculo();
            }
        });

        // Evento del botón Limpiar
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                tablaVehiculos.clearSelection();
            }
        });

        // Evento del botón Actualizar
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        });
    }

    /**
     * Configura las propiedades de la ventana
     */
    private void setWindowProperties() {
        setSize(900, 600);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
    }

    /**
     * Carga los datos de vehículos en la tabla
     */
    private void cargarDatos() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        // Obtener vehículos del controlador
        ArrayList<Vehiculo> vehiculos = controlador.getVehiculos();

        // Agregar cada vehículo a la tabla
        for (Vehiculo vehiculo : vehiculos) {
            Object[] fila = {
                vehiculo.getPatente(),
                vehiculo.getMarca(),
                vehiculo.getCondicion(),
                obtenerDescripcionCondicion(vehiculo.getCondicion())
            };
            modeloTabla.addRow(fila);
        }
    }

    /**
     * Obtiene la descripción textual de la condición del vehículo
     *
     * @param condicion Carácter de condición
     * @return Descripción de la condición
     */
    private String obtenerDescripcionCondicion(char condicion) {
        switch (condicion) {
            case 'D':
                return "Disponible";
            case 'A':
                return "Arrendado";
            case 'M':
                return "Mantenimiento";
            default:
                return "Desconocido";
        }
    }

    /**
     * Obtiene el carácter de condición desde el ComboBox
     *
     * @return Carácter de condición
     */
    private char obtenerCondicionSeleccionada() {
        String seleccion = (String) cmbCondicion.getSelectedItem();
        return seleccion.charAt(0); // Obtiene el primer carácter (D, A, o M)
    }

    /**
     * Carga los datos del vehículo seleccionado en el formulario
     */
    private void cargarDatosEnFormulario() {
        if (filaSeleccionada != -1) {
            try {
                String patente = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
                String marca = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
                Object condicionObj = modeloTabla.getValueAt(filaSeleccionada, 2);

                char condicion;

                // Manejar tanto String como Character
                if (condicionObj instanceof Character) {
                    condicion = (Character) condicionObj;
                } else if (condicionObj instanceof String) {
                    String condicionStr = (String) condicionObj;
                    condicion = condicionStr.length() > 0 ? condicionStr.charAt(0) : 'D';
                } else {
                    condicion = 'D'; // Valor por defecto
                }

                // Cargar datos en los campos del formulario
                txtPatente.setText(patente);
                txtMarca.setText(marca);

                // Seleccionar la condición correcta en el ComboBox
                switch (condicion) {
                    case 'D':
                        cmbCondicion.setSelectedIndex(0);
                        break;
                    case 'A':
                        cmbCondicion.setSelectedIndex(1);
                        break;
                    case 'M':
                        cmbCondicion.setSelectedIndex(2);
                        break;
                    default:
                        cmbCondicion.setSelectedIndex(0);
                        break;
                }

                // En modo edición, la patente no debe ser editable
                txtPatente.setEditable(false);

                // Actualizar el foco para que el usuario vea los cambios
                txtPatente.setBackground(new Color(240, 240, 240)); // Gris claro para indicar no editable
                txtMarca.requestFocus();

            } catch (Exception e) {
                System.err.println("ERROR al cargar datos en formulario: " + e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al cargar los datos del vehículo: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Limpia el formulario
     */
    private void limpiarFormulario() {
        txtPatente.setText("");
        txtMarca.setText("");
        cmbCondicion.setSelectedIndex(0);
        txtPatente.setEditable(true);
        txtPatente.setBackground(Color.WHITE); // Restaurar fondo blanco
        modoEdicion = false;
        filaSeleccionada = -1;
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    /**
     * Valida los datos del formulario
     *
     * @return true si los datos son válidos
     */
    private boolean validarDatos() {
        if (txtPatente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La patente es obligatoria",
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
            txtPatente.requestFocus();
            return false;
        }

        if (txtMarca.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La marca es obligatoria",
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
            txtMarca.requestFocus();
            return false;
        }

        // Validar formato de patente (puede ser personalizable)
        String patente = txtPatente.getText().trim().toUpperCase();
        if (patente.length() < 6) {
            JOptionPane.showMessageDialog(this, "La patente debe tener al menos 6 caracteres",
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
            txtPatente.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Agrega un nuevo vehículo
     */
    private void agregarVehiculo() {
        if (!validarDatos()) {
            return;
        }

        String patente = txtPatente.getText().trim().toUpperCase();
        String marca = txtMarca.getText().trim();
        char condicion = obtenerCondicionSeleccionada();

        // Verificar si ya existe un vehículo con esa patente
        if (controlador.getVehiculo(patente) != null) {
            JOptionPane.showMessageDialog(this, "Ya existe un vehículo con esa patente",
                    "Error", JOptionPane.ERROR_MESSAGE);
            txtPatente.requestFocus();
            return;
        }

        // Crear nuevo vehículo
        Vehiculo nuevoVehiculo = new Vehiculo(patente, marca, condicion);

        // Agregar al sistema
        if (controlador.addVehiculo(nuevoVehiculo)) {
            JOptionPane.showMessageDialog(this, "Vehículo agregado exitosamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarDatos();
            limpiarFormulario();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el vehículo",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Modifica el vehículo seleccionado
     */
    private void modificarVehiculo() {
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo para modificar",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarDatos()) {
            return;
        }

        String patente = txtPatente.getText().trim().toUpperCase();
        String marca = txtMarca.getText().trim();
        char condicion = obtenerCondicionSeleccionada();

        // Obtener vehículo existente
        Vehiculo vehiculo = controlador.getVehiculo(patente);
        if (vehiculo != null) {
            // Verificar si se puede cambiar la condición
            if (vehiculo.getCondicion() == 'A' && condicion != 'A') {
                int respuesta = JOptionPane.showConfirmDialog(this,
                        "El vehículo está actualmente arrendado.\n¿Está seguro que desea cambiar su estado?",
                        "Confirmar Cambio", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (respuesta != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            // Actualizar datos
            vehiculo.setMarca(marca);
            vehiculo.setCondicion(condicion);

            JOptionPane.showMessageDialog(this, "Vehículo modificado exitosamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarDatos();
            limpiarFormulario();
        } else {
            JOptionPane.showMessageDialog(this, "Error: No se encontró el vehículo",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Elimina el vehículo seleccionado
     */
    private void eliminarVehiculo() {
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo para eliminar",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String patente = txtPatente.getText().trim();
        Vehiculo vehiculo = controlador.getVehiculo(patente);

        if (vehiculo != null) {
            // Verificar si el vehículo está arrendado
            if (vehiculo.getCondicion() == 'A') {
                JOptionPane.showMessageDialog(this,
                        "No se puede eliminar un vehículo que está actualmente arrendado",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Confirmar eliminación
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar el vehículo " + patente + "?",
                    "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                if (controlador.deleteVehiculo(patente)) {
                    JOptionPane.showMessageDialog(this, "Vehículo eliminado exitosamente",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarDatos();
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el vehículo",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error: No se encontró el vehículo",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
