package vista;

import controlador.ControladorPrincipal;
import modelo.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Ventana para crear arriendos con cuotas
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public class VentanaArriendoConCuotas extends JDialog {

    private ControladorPrincipal controlador;

    // Componentes principales
    private JComboBox<Cliente> cmbCliente;
    private JComboBox<Vehiculo> cmbVehiculo;
    private JButton btnNuevoCliente;
    private JTextField txtFechaArriendo, txtDias, txtPrecioDia, txtCantidadCuotas;
    private JButton btnGuardarArriendo;
    private JTable tablaCuotas;
    private DefaultTableModel modeloTabla;
    private JButton btnPagarPrimera;

    private Arriendo arriendoActual;

    /**
     * Constructor de la ventana
     *
     * @param parent Ventana padre
     * @param controlador Controlador principal
     */
    public VentanaArriendoConCuotas(JFrame parent, ControladorPrincipal controlador) {
        super(parent, "Arriendos con Cuotas", true);
        this.controlador = controlador;
        this.arriendoActual = null;
        initializeComponents();
        setupLayout();
        setupEvents();
        setWindowProperties();
        cargarDatos();
    }

    /**
     * Inicializa los componentes
     */
    private void initializeComponents() {
        cmbCliente = new JComboBox<>();
        cmbVehiculo = new JComboBox<>();
        btnNuevoCliente = new JButton("Ingresar nuevo cliente");

        txtFechaArriendo = new JTextField(10);
        txtDias = new JTextField(5);
        txtPrecioDia = new JTextField(8);
        txtCantidadCuotas = new JTextField(5);
        btnGuardarArriendo = new JButton("Guardar arriendo y mostrar cuotas >>");

        // Configurar tabla de cuotas
        String[] columnas = {"Número", "Valor", "¿Pagada?"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 2) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCuotas = new JTable(modeloTabla);
        tablaCuotas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btnPagarPrimera = new JButton("Pagar Primera Cuota");
    }

    /**
     * Configura el layout
     */
    private void setupLayout() {
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.setBackground(Color.WHITE);

        // Título
        JLabel lblTitulo = new JLabel("ARRIENDOS CON CUOTAS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Panel de contenido principal
        JPanel panelContenido = new JPanel(new BorderLayout());
        panelContenido.setBackground(Color.WHITE);

        // Panel superior - Selección de cliente y vehículo
        JPanel panelSuperior = new JPanel(new GridBagLayout());
        panelSuperior.setBackground(Color.WHITE);
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Selección"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Selección de cliente
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelSuperior.add(new JLabel("Seleccione CLIENTE:"), gbc);
        gbc.gridx = 1;
        panelSuperior.add(cmbCliente, gbc);
        gbc.gridx = 2;
        panelSuperior.add(new JLabel("Seleccione AUTOMÓVIL:"), gbc);
        gbc.gridx = 3;
        panelSuperior.add(cmbVehiculo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        panelSuperior.add(btnNuevoCliente, gbc);

        panelContenido.add(panelSuperior, BorderLayout.NORTH);

        // Panel central - Datos del arriendo
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(Color.WHITE);

        // Panel izquierdo - Formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Arriendo"));

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(new JLabel("Fecha Arriendo:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtFechaArriendo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(new JLabel("Días:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtDias, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(new JLabel("Precio por día:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtPrecioDia, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelFormulario.add(new JLabel("MONTO A PAGAR:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelFormulario.add(new JLabel("Cantidad de cuotas:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtCantidadCuotas, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panelFormulario.add(btnGuardarArriendo, gbc);

        panelCentral.add(panelFormulario, BorderLayout.WEST);

        // Panel derecho - Cuotas
        JPanel panelCuotas = new JPanel(new BorderLayout());
        panelCuotas.setBackground(Color.WHITE);
        panelCuotas.setBorder(BorderFactory.createTitledBorder("CUOTAS A PAGAR"));

        JScrollPane scrollCuotas = new JScrollPane(tablaCuotas);
        scrollCuotas.setPreferredSize(new Dimension(300, 200));
        panelCuotas.add(scrollCuotas, BorderLayout.CENTER);

        JPanel panelBotonPagar = new JPanel(new FlowLayout());
        panelBotonPagar.setBackground(Color.WHITE);
        panelBotonPagar.add(btnPagarPrimera);
        panelCuotas.add(panelBotonPagar, BorderLayout.SOUTH);

        panelCentral.add(panelCuotas, BorderLayout.CENTER);

        panelContenido.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelContenido, BorderLayout.CENTER);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    /**
     * Configura los eventos
     */
    private void setupEvents() {
        btnNuevoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAgregarCliente();
            }
        });

        btnGuardarArriendo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarArriendo();
            }
        });

        btnPagarPrimera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagarPrimeraCuota();
            }
        });

        // Eventos para calcular automáticamente el monto
        ActionListener calcularMonto = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularMontoTotal();
            }
        };

        txtDias.addActionListener(calcularMonto);
        txtPrecioDia.addActionListener(calcularMonto);
    }

    /**
     * Configura las propiedades de la ventana
     */
    private void setWindowProperties() {
        setSize(900, 600);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Carga los datos iniciales
     */
    private void cargarDatos() {
        cargarClientes();
        cargarVehiculos();

        // Establecer fecha actual como ejemplo
        txtFechaArriendo.setText(java.time.LocalDate.now().toString());
    }

    /**
     * Carga los clientes en el combo
     */
    private void cargarClientes() {
        cmbCliente.removeAllItems();
        ArrayList<Cliente> clientes = controlador.getClientes();

        // Solo agregar clientes vigentes
        for (Cliente cliente : clientes) {
            if (cliente.isVigente()) {
                cmbCliente.addItem(cliente);
            }
        }
    }

    /**
     * Carga los vehículos en el combo
     */
    private void cargarVehiculos() {
        cmbVehiculo.removeAllItems();
        ArrayList<Vehiculo> vehiculos = controlador.getVehiculosDisponibles();

        System.out.println("Cargando vehículos disponibles: " + vehiculos.size());
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println("  - " + vehiculo.toString());
            cmbVehiculo.addItem(vehiculo);
        }
    }

    /**
     * Abre la ventana para agregar cliente
     */
    private void abrirVentanaAgregarCliente() {
        VentanaAgregarCliente ventana = new VentanaAgregarCliente((JFrame) getParent(), controlador);
        ventana.setVisible(true);
        // Recargar clientes después de cerrar la ventana
        cargarClientes();
    }

    /**
     * Calcula y muestra el monto total
     */
    private void calcularMontoTotal() {
        try {
            if (!txtDias.getText().trim().isEmpty() && !txtPrecioDia.getText().trim().isEmpty()) {
                int dias = Integer.parseInt(txtDias.getText().trim());
                int precioDia = Integer.parseInt(txtPrecioDia.getText().trim());
                int total = dias * precioDia;

                // Aquí podrías mostrar el total en algún label
                // Por ahora solo lo calculamos internamente
            }
        } catch (NumberFormatException e) {
            // Ignorar errores de formato
        }
    }

    /**
     * Guarda el arriendo y genera las cuotas
     */
    private void guardarArriendo() {
        if (!validarDatos()) {
            return;
        }

        try {
            // Obtener datos del formulario
            Cliente cliente = (Cliente) cmbCliente.getSelectedItem();
            Vehiculo vehiculo = (Vehiculo) cmbVehiculo.getSelectedItem();
            String fecha = txtFechaArriendo.getText().trim();
            int dias = Integer.parseInt(txtDias.getText().trim());
            int precioDia = Integer.parseInt(txtPrecioDia.getText().trim());
            int cantidadCuotas = Integer.parseInt(txtCantidadCuotas.getText().trim());

            // Debug: Verificar datos
            System.out.println("=== DATOS DEL ARRIENDO ===");
            System.out.println("Cliente: " + cliente.getNombre() + " - Vigente: " + cliente.isVigente());
            System.out.println("Vehículo: " + vehiculo.getPatente() + " - Condición: " + vehiculo.getCondicion());
            System.out.println("Días: " + dias + ", Precio: " + precioDia + ", Cuotas: " + cantidadCuotas);

            // Crear arriendo
            int numArriendo = controlador.getProximoNumeroArriendo();
            arriendoActual = new Arriendo(numArriendo, fecha, dias, precioDia, cliente, vehiculo);

            // Debug: Verificar evaluación antes de ingresar
            boolean evaluacionPrevia = arriendoActual.evaluarArriendo();
            System.out.println("Evaluación previa: " + evaluacionPrevia);

            // Ingresar arriendo con cuotas
            if (arriendoActual.ingresarArriendoConCuota(cantidadCuotas)) {
                System.out.println("Arriendo procesado correctamente, agregando al sistema...");

                // Agregar al sistema
                if (controlador.addArriendo(arriendoActual)) {
                    mostrarCuotas();
                    mostrarExito("Arriendo guardado exitosamente");
                    // Actualizar la lista de vehículos disponibles
                    cargarVehiculos();
                } else {
                    mostrarError("Error al guardar el arriendo en el sistema");
                }
            } else {
                mostrarError("Error al procesar el arriendo. Verifique que el cliente esté vigente y el vehículo disponible.");
            }

        } catch (NumberFormatException e) {
            mostrarError("Error en los datos numéricos. Verifique días, precio y cantidad de cuotas.");
        } catch (Exception e) {
            mostrarError("Error inesperado: " + e.getMessage());
            e.printStackTrace(); // Para debug
        }
    }

    /**
     * Valida los datos del formulario
     */
    private boolean validarDatos() {
        if (cmbCliente.getSelectedItem() == null) {
            mostrarError("Debe seleccionar un cliente");
            return false;
        }

        if (cmbVehiculo.getSelectedItem() == null) {
            mostrarError("Debe seleccionar un vehículo");
            return false;
        }

        if (txtFechaArriendo.getText().trim().isEmpty()) {
            mostrarError("La fecha es obligatoria");
            return false;
        }

        try {
            int dias = Integer.parseInt(txtDias.getText().trim());
            if (dias <= 0) {
                mostrarError("Los días deben ser mayor a 0");
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError("Días debe ser un número válido");
            return false;
        }

        try {
            int precio = Integer.parseInt(txtPrecioDia.getText().trim());
            if (precio <= 0) {
                mostrarError("El precio por día debe ser mayor a 0");
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError("Precio por día debe ser un número válido");
            return false;
        }

        try {
            int cuotas = Integer.parseInt(txtCantidadCuotas.getText().trim());
            if (cuotas <= 0) {
                mostrarError("La cantidad de cuotas debe ser mayor a 0");
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError("Cantidad de cuotas debe ser un número válido");
            return false;
        }

        return true;
    }

    /**
     * Muestra las cuotas en la tabla
     */
    private void mostrarCuotas() {
        if (arriendoActual == null) {
            return;
        }

        modeloTabla.setRowCount(0);
        ArrayList<CuotaArriendo> cuotas = arriendoActual.getCuotas();

        for (CuotaArriendo cuota : cuotas) {
            Object[] fila = {
                cuota.getNumCuota(),
                "$" + cuota.getValorCuota(),
                cuota.isPagada()
            };
            modeloTabla.addRow(fila);
        }

        btnPagarPrimera.setEnabled(true);
    }

    /**
     * Paga la primera cuota
     */
    private void pagarPrimeraCuota() {
        if (arriendoActual == null) {
            mostrarError("No hay arriendo activo");
            return;
        }

        if (arriendoActual.pagarCuota(1)) {
            mostrarCuotas(); // Actualizar tabla
            mostrarExito("Primera cuota pagada exitosamente");
        } else {
            mostrarError("Error al pagar la primera cuota o ya está pagada");
        }
    }

    /**
     * Muestra un mensaje de error
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de éxito
     */
    private void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
