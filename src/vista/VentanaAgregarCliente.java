package vista;

import controlador.ControladorPrincipal;
import modelo.Cliente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana para agregar clientes al sistema
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public class VentanaAgregarCliente extends JDialog {

    private ControladorPrincipal controlador;
    private JTextField txtCedula, txtNombre;
    private JCheckBox chkVigente;
    private JButton btnAgregar;

    /**
     * Constructor de la ventana
     *
     * @param parent Ventana padre
     * @param controlador Controlador principal
     */
    public VentanaAgregarCliente(JFrame parent, ControladorPrincipal controlador) {
        super(parent, "Agregar Cliente", true);
        this.controlador = controlador;
        initializeComponents();
        setupLayout();
        setupEvents();
        setWindowProperties();
    }

    /**
     * Inicializa los componentes
     */
    private void initializeComponents() {
        txtCedula = new JTextField(15);
        txtNombre = new JTextField(20);
        chkVigente = new JCheckBox("Vigente", true);
        btnAgregar = new JButton("Agregar");
    }

    /**
     * Configura el layout
     */
    private void setupLayout() {
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(235, 245, 255));

        // Título
        JLabel lblTitulo = new JLabel("CLIENTES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createVerticalStrut(20));

        // Panel de campos
        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBackground(new Color(235, 245, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Campo cédula
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panelCampos.add(new JLabel("Cédula:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelCampos.add(txtCedula, gbc);

        // Campo nombre
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panelCampos.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelCampos.add(txtNombre, gbc);

        // Checkbox vigente
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        chkVigente.setBackground(new Color(235, 245, 255));
        panelCampos.add(chkVigente, gbc);

        panelPrincipal.add(panelCampos);
        panelPrincipal.add(Box.createVerticalStrut(20));

        // Panel de botón
        JPanel panelBoton = new JPanel(new FlowLayout());
        panelBoton.setBackground(new Color(235, 245, 255));
        panelBoton.add(btnAgregar);
        panelPrincipal.add(panelBoton);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    /**
     * Configura los eventos
     */
    private void setupEvents() {
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
            }
        });

        // Enter en los campos de texto
        ActionListener enterAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    agregarCliente();
                }
            }
        };

        txtCedula.addActionListener(enterAction);
        txtNombre.addActionListener(enterAction);
    }

    /**
     * Configura las propiedades de la ventana
     */
    private void setWindowProperties() {
        setSize(400, 300);
        setLocationRelativeTo(getParent());
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Valida los campos del formulario
     *
     * @return true si son válidos, false en caso contrario
     */
    private boolean validarCampos() {
        if (txtCedula.getText().trim().isEmpty()) {
            mostrarError("La cédula es obligatoria");
            txtCedula.requestFocus();
            return false;
        }

        if (txtNombre.getText().trim().isEmpty()) {
            mostrarError("El nombre es obligatorio");
            txtNombre.requestFocus();
            return false;
        }

        // Validar formato de cédula (básico)
        String cedula = txtCedula.getText().trim();
        if (!cedula.matches("\\d{8}-[\\dkK]")) {
            mostrarError("Formato de cédula inválido. Use: 12345678-9");
            txtCedula.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Agrega el cliente al sistema
     */
    private void agregarCliente() {
        if (!validarCampos()) {
            return;
        }

        try {
            String cedula = txtCedula.getText().trim();
            String nombre = txtNombre.getText().trim();
            boolean vigente = chkVigente.isSelected();

            // Verificar si el cliente ya existe
            if (controlador.getCliente(cedula) != null) {
                mostrarError("Ya existe un cliente con esta cédula");
                txtCedula.requestFocus();
                return;
            }

            // Crear y agregar el cliente
            Cliente cliente = new Cliente(cedula, nombre, vigente);

            if (controlador.addCliente(cliente)) {
                mostrarExito("Cliente agregado exitosamente");
                limpiarCampos();
                txtCedula.requestFocus();
            } else {
                mostrarError("Error al agregar el cliente");
            }

        } catch (Exception ex) {
            mostrarError("Error inesperado: " + ex.getMessage());
        }
    }

    /**
     * Limpia los campos del formulario
     */
    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombre.setText("");
        chkVigente.setSelected(true);
    }

    /**
     * Muestra un mensaje de error
     *
     * @param mensaje Mensaje a mostrar
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de éxito
     *
     * @param mensaje Mensaje a mostrar
     */
    private void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
