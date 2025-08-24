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

    private final ControladorPrincipal controlador;
    private JTextField txtCedula, txtNombre;
    private JCheckBox chkVigente;
    private JButton btnAgregar;
    private JLabel lblFormatoRut;

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
        txtCedula = new JTextField();
        txtCedula.setPreferredSize(new Dimension(200, 25));
        txtCedula.setMinimumSize(new Dimension(200, 25));
        txtCedula.setMaximumSize(new Dimension(200, 25));

        txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(250, 25));
        txtNombre.setMinimumSize(new Dimension(250, 25));
        txtNombre.setMaximumSize(new Dimension(250, 25));

        chkVigente = new JCheckBox("Vigente", true);

        btnAgregar = new JButton("Agregar Cliente");
        btnAgregar.setPreferredSize(new Dimension(150, 35));
        btnAgregar.setMinimumSize(new Dimension(150, 35));
        btnAgregar.setMaximumSize(new Dimension(150, 35));

        // Etiqueta informativa del formato RUT
        lblFormatoRut = new JLabel("<html><i>Formato: 123.456-7 (min 6 dígitos) o 12.345.678-9 (max 8 dígitos)</i></html>");
        lblFormatoRut.setFont(new Font("Arial", Font.ITALIC, 10));
        lblFormatoRut.setForeground(new Color(80, 80, 80));
        lblFormatoRut.setPreferredSize(new Dimension(350, 20));
        lblFormatoRut.setMinimumSize(new Dimension(350, 20));
        lblFormatoRut.setMaximumSize(new Dimension(350, 20));
    }

    /**
     * Configura el layout
     */
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(25, 25, 112));
        JLabel lblTitulo = new JLabel("GESTIÓN DE CLIENTES", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(235, 245, 255));

        // Panel de campos
        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBackground(new Color(235, 245, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Campo cédula
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panelCampos.add(new JLabel("RUT:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelCampos.add(txtCedula, gbc);

        // Etiqueta informativa del formato (debajo del campo RUT)
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 10, 10); // Menos espacio arriba
        panelCampos.add(lblFormatoRut, gbc);

        // Campo nombre
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado normal
        panelCampos.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelCampos.add(txtNombre, gbc);

        // Checkbox vigente
        gbc.gridx = 0;
        gbc.gridy = 3;
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
        setSize(650, 380);
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
            mostrarError("El RUT es obligatorio");
            txtCedula.requestFocus();
            return false;
        }

        if (txtNombre.getText().trim().isEmpty()) {
            mostrarError("El nombre es obligatorio");
            txtNombre.requestFocus();
            return false;
        }

        // Validar formato de RUT
        String rut = txtCedula.getText().trim();
        if (!validarFormatoRUT(rut)) {
            mostrarError("Formato de RUT inválido.\nFormato correcto:\n• Mínimo: 123.456-7 (6 dígitos)\n• Máximo: 12.345.678-9 (8 dígitos)");
            txtCedula.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Valida el formato del RUT chileno Acepta formatos: 123.456-7 (mínimo 6
     * dígitos) hasta 12.345.678-9 (máximo 8 dígitos)
     *
     * @param rut RUT a validar
     * @return true si el formato es válido
     */
    private boolean validarFormatoRUT(String rut) {
        // Patrón para RUT chileno:
        // - 6 dígitos: 123.456-7
        // - 7 dígitos: 1.234.567-8
        // - 8 dígitos: 12.345.678-9

        // Remover espacios
        rut = rut.trim();

        // Patrón flexible para diferentes longitudes
        String patron6 = "^\\d{3}\\.\\d{3}-[\\dkK]$";        // 123.456-7
        String patron7 = "^\\d{1}\\.\\d{3}\\.\\d{3}-[\\dkK]$"; // 1.234.567-8
        String patron8 = "^\\d{2}\\.\\d{3}\\.\\d{3}-[\\dkK]$"; // 12.345.678-9

        if (!rut.matches(patron6) && !rut.matches(patron7) && !rut.matches(patron8)) {
            return false;
        }

        // Extraer números y dígito verificador
        String[] partes = rut.split("-");
        if (partes.length != 2) {
            return false;
        }

        String numeros = partes[0].replaceAll("[^0-9]", "");
        String digitoVerificador = partes[1].toUpperCase();

        // Verificar longitud de números (6-8 dígitos)
        if (numeros.length() < 6 || numeros.length() > 8) {
            return false;
        }

        // Validar dígito verificador
        char dvCalculado = calcularDigitoVerificador(numeros);
        return digitoVerificador.equals(String.valueOf(dvCalculado));
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

    /**
     * Calcula el dígito verificador de un RUT chileno automáticamente
     */
    private char calcularDigitoVerificador(String numero) {
        try {
            int rut = Integer.parseInt(numero);
            int suma = 0;
            int multiplicador = 2;

            while (rut > 0) {
                suma += (rut % 10) * multiplicador;
                rut /= 10;
                multiplicador++;
                if (multiplicador > 7) {
                    multiplicador = 2;
                }
            }

            int resto = suma % 11;

            if (resto == 0) {
                return '0';
            } else if (resto == 1) {
                return 'K';
            } else {
                return (char) ('0' + (11 - resto));
            }

        } catch (NumberFormatException e) {
            return '0';
        }
    }

}
