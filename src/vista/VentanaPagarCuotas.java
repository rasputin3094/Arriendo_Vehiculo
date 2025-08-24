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
 * Ventana para pagar cuotas de arriendos
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public class VentanaPagarCuotas extends JDialog {

    private ControladorPrincipal controlador;

    // Componentes principales
    private JComboBox<Cliente> cmbCliente;
    private JList<Arriendo> listaArriendos;
    private DefaultListModel<Arriendo> modeloLista;
    private JButton btnMostrarPagos;
    private JTable tablaPagos;
    private DefaultTableModel modeloTabla;
    private JButton btnRealizarPago;

    private Arriendo arriendoSeleccionado;

    /**
     * Constructor de la ventana
     *
     * @param parent Ventana padre
     * @param controlador Controlador principal
     */
    public VentanaPagarCuotas(JFrame parent, ControladorPrincipal controlador) {
        super(parent, "Pagar Cuotas de Arriendos", true);
        this.controlador = controlador;
        this.arriendoSeleccionado = null;
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

        modeloLista = new DefaultListModel<>();
        listaArriendos = new JList<>(modeloLista);
        listaArriendos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btnMostrarPagos = new JButton("Mostrar pagos arriendo seleccionado >>>");

        // Configurar tabla de pagos
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
                return column == 2; // Solo la columna de pagada es editable
            }
        };
        tablaPagos = new JTable(modeloTabla);
        tablaPagos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btnRealizarPago = new JButton("Realizar Pago");
    }

    /**
     * Configura el layout
     */
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(25, 25, 112));
        JLabel lblTitulo = new JLabel("PAGAR CUOTAS ARRIENDOS", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.setBackground(new Color(255, 240, 245));

        // Panel de contenido
        JPanel panelContenido = new JPanel(new BorderLayout());
        panelContenido.setBackground(new Color(255, 240, 245));

        // Panel superior - Selección de cliente
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setBackground(new Color(255, 240, 245));
        panelSuperior.add(new JLabel("Seleccione cliente:"));
        panelSuperior.add(cmbCliente);

        panelContenido.add(panelSuperior, BorderLayout.NORTH);

        // Panel central - Lista de arriendos y pagos
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(new Color(255, 240, 245));

        // Panel izquierdo - Arriendos
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBackground(new Color(255, 240, 245));
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Seleccione arriendo:"));
        panelIzquierdo.setPreferredSize(new Dimension(300, 0));

        JScrollPane scrollArriendos = new JScrollPane(listaArriendos);
        scrollArriendos.setPreferredSize(new Dimension(280, 150));
        panelIzquierdo.add(scrollArriendos, BorderLayout.CENTER);

        JPanel panelBotonMostrar = new JPanel(new FlowLayout());
        panelBotonMostrar.setBackground(new Color(255, 240, 245));
        panelBotonMostrar.add(btnMostrarPagos);
        panelIzquierdo.add(panelBotonMostrar, BorderLayout.SOUTH);

        // Panel derecho - Pagos
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(new Color(255, 240, 245));
        panelDerecho.setBorder(BorderFactory.createTitledBorder("PAGOS"));

        JScrollPane scrollPagos = new JScrollPane(tablaPagos);
        panelDerecho.add(scrollPagos, BorderLayout.CENTER);

        JPanel panelBotonPagar = new JPanel(new FlowLayout());
        panelBotonPagar.setBackground(new Color(255, 240, 245));
        panelBotonPagar.add(btnRealizarPago);
        panelDerecho.add(panelBotonPagar, BorderLayout.SOUTH);

        panelCentral.add(panelIzquierdo, BorderLayout.WEST);
        panelCentral.add(panelDerecho, BorderLayout.CENTER);

        panelContenido.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelContenido, BorderLayout.CENTER);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    /**
     * Configura los eventos
     */
    private void setupEvents() {
        cmbCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarArriendosCliente();
            }
        });

        btnMostrarPagos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPagosArriendo();
            }
        });

        btnRealizarPago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarPago();
            }
        });

        listaArriendos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                arriendoSeleccionado = listaArriendos.getSelectedValue();
            }
        });
    }

    /**
     * Configura las propiedades de la ventana
     */
    private void setWindowProperties() {
        setSize(800, 500);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Carga los datos iniciales
     */
    private void cargarDatos() {
        cargarClientes();
        btnRealizarPago.setEnabled(false);
    }

    /**
     * Carga los clientes en el combo
     */
    private void cargarClientes() {
        cmbCliente.removeAllItems();
        cmbCliente.addItem(null); // Opción vacía

        ArrayList<Cliente> clientes = controlador.getClientes();
        for (Cliente cliente : clientes) {
            // Solo agregar clientes que tengan arriendos
            if (!controlador.getArriendosPorCliente(cliente.getCedula()).isEmpty()) {
                cmbCliente.addItem(cliente);
            }
        }
    }

    /**
     * Carga los arriendos del cliente seleccionado
     */
    private void cargarArriendosCliente() {
        modeloLista.clear();
        modeloTabla.setRowCount(0);
        arriendoSeleccionado = null;
        btnRealizarPago.setEnabled(false);

        Cliente clienteSeleccionado = (Cliente) cmbCliente.getSelectedItem();
        if (clienteSeleccionado != null) {
            ArrayList<Arriendo> arriendos = controlador.getArriendosPorCliente(clienteSeleccionado.getCedula());
            for (Arriendo arriendo : arriendos) {
                modeloLista.addElement(arriendo);
            }
        }
    }

    /**
     * Muestra los pagos del arriendo seleccionado
     */
    private void mostrarPagosArriendo() {
        if (arriendoSeleccionado == null) {
            mostrarError("Debe seleccionar un arriendo");
            return;
        }

        modeloTabla.setRowCount(0);
        ArrayList<CuotaArriendo> cuotas = arriendoSeleccionado.getCuotas();

        boolean tienePendientes = false;
        for (CuotaArriendo cuota : cuotas) {
            Object[] fila = {
                cuota.getNumCuota(),
                "$" + cuota.getValorCuota(),
                cuota.isPagada()
            };
            modeloTabla.addRow(fila);

            if (!cuota.isPagada()) {
                tienePendientes = true;
            }
        }

        btnRealizarPago.setEnabled(tienePendientes);

        if (!tienePendientes) {
            mostrarInfo("Todas las cuotas de este arriendo están pagadas");
        }
    }

    /**
     * Realiza el pago de una cuota
     */
    private void realizarPago() {
        if (arriendoSeleccionado == null) {
            mostrarError("Debe seleccionar un arriendo");
            return;
        }

        int filaSeleccionada = tablaPagos.getSelectedRow();
        if (filaSeleccionada == -1) {
            mostrarError("Debe seleccionar una cuota para pagar");
            return;
        }

        // Obtener número de cuota
        int numCuota = (Integer) modeloTabla.getValueAt(filaSeleccionada, 0);
        boolean yaPagada = (Boolean) modeloTabla.getValueAt(filaSeleccionada, 2);

        if (yaPagada) {
            mostrarError("Esta cuota ya está pagada");
            return;
        }

        // Confirmar pago
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea pagar la cuota #" + numCuota + "?",
                "Confirmar Pago",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            if (controlador.pagarCuota(arriendoSeleccionado.getNumArriendo(), numCuota)) {
                mostrarPagosArriendo(); // Actualizar tabla
                mostrarExito("Cuota #" + numCuota + " pagada exitosamente");

                // Verificar si todas las cuotas están pagadas
                if (controlador.arriendoCompletamentePagado(arriendoSeleccionado.getNumArriendo())) {
                    mostrarInfo("¡Felicitaciones! Todas las cuotas del arriendo han sido pagadas");
                    // Liberar el vehículo
                    arriendoSeleccionado.getVehiculo().setCondicion('D');
                }
            } else {
                mostrarError("Error al procesar el pago");
            }
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

    /**
     * Muestra un mensaje informativo
     */
    private void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
