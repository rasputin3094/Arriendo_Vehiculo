package vista;

import controlador.ControladorPrincipal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana principal del sistema de arriendo de vehículos
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public class VentanaPrincipal extends JFrame {

    private ControladorPrincipal controlador;
    private JMenuBar menuBar;
    private JMenu menuArriendo, menuCliente, menuAyuda;
    private JMenuItem itemArriendoConCuota, itemPagarCuota, itemAgregarCliente, itemAcercaDe;

    /**
     * Constructor de la ventana principal
     */
    public VentanaPrincipal() {
        controlador = new ControladorPrincipal();
        initializeComponents();
        setupLayout();
        setupEvents();
        setWindowProperties();
    }

    /**
     * Inicializa los componentes de la interfaz
     */
    private void initializeComponents() {
        // Crear barra de menús
        menuBar = new JMenuBar();

        // Crear menús
        menuArriendo = new JMenu("Arriendo");
        menuCliente = new JMenu("Cliente");
        menuAyuda = new JMenu("Ayuda");

        // Crear items del menú
        itemArriendoConCuota = new JMenuItem("Arriendo con Cuotas");
        itemPagarCuota = new JMenuItem("Pagar Cuota");
        itemAgregarCliente = new JMenuItem("Agregar Cliente");
        itemAcercaDe = new JMenuItem("Acerca de");

        // Agregar items a los menús
        menuArriendo.add(itemArriendoConCuota);
        menuArriendo.add(itemPagarCuota);

        menuCliente.add(itemAgregarCliente);

        menuAyuda.add(itemAcercaDe);

        // Agregar menús a la barra
        menuBar.add(menuArriendo);
        menuBar.add(menuCliente);
        menuBar.add(menuAyuda);
    }

    /**
     * Configura el layout de la ventana
     */
    private void setupLayout() {
        setJMenuBar(menuBar);

        // Panel principal con imagen de fondo
        JPanel panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(240, 248, 255));
                g.fillRect(0, 0, getWidth(), getHeight());

                // Dibujar título
                g.setColor(new Color(25, 25, 112));
                g.setFont(new Font("Arial", Font.BOLD, 24));
                FontMetrics fm = g.getFontMetrics();
                String titulo = "Sistema de Arriendo de Vehículos Car-REnt";
                int x = (getWidth() - fm.stringWidth(titulo)) / 2;
                int y = 100;
                g.drawString(titulo, x, y);

                // Dibujar subtítulo
                g.setFont(new Font("Arial", Font.ITALIC, 16));
                fm = g.getFontMetrics();
                String subtitulo = "Seleccione una opción del menú para comenzar";
                x = (getWidth() - fm.stringWidth(subtitulo)) / 2;
                y = 140;
                g.drawString(subtitulo, x, y);
            }
        };

        add(panelPrincipal, BorderLayout.CENTER);
    }

    /**
     * Configura los eventos de los componentes
     */
    private void setupEvents() {
        itemArriendoConCuota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaArriendoConCuotas();
            }
        });

        itemPagarCuota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaPagarCuotas();
            }
        });

        itemAgregarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAgregarCliente();
            }
        });

        itemAcercaDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAcercaDe();
            }
        });
    }

    /**
     * Configura las propiedades de la ventana
     */
    private void setWindowProperties() {
        setTitle("Car-REnt - Sistema de Arriendo de Vehículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(600, 400));

        // Icono de la aplicación (opcional)
        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
        } catch (Exception e) {
            // Ignorar si no se encuentra el icono
        }
    }

    /**
     * Abre la ventana de arriendo con cuotas
     */
    private void abrirVentanaArriendoConCuotas() {
        VentanaArriendoConCuotas ventana = new VentanaArriendoConCuotas(this, controlador);
        ventana.setVisible(true);
    }

    /**
     * Abre la ventana de pago de cuotas
     */
    private void abrirVentanaPagarCuotas() {
        VentanaPagarCuotas ventana = new VentanaPagarCuotas(this, controlador);
        ventana.setVisible(true);
    }

    /**
     * Abre la ventana de agregar cliente
     */
    private void abrirVentanaAgregarCliente() {
        VentanaAgregarCliente ventana = new VentanaAgregarCliente(this, controlador);
        ventana.setVisible(true);
    }

    /**
     * Muestra el diálogo acerca de
     */
    private void mostrarAcercaDe() {
        String mensaje = "Sistema de Arriendo de Vehículos Car-REnt\n\n"
                + "Versión: 1.0\n"
                + "Desarrollado con Java Swing\n"
                + "Patrón de diseño: MVC\n"
                + "Programación Orientada a Eventos (POE)\n\n"
                + "© 2025 - Todos los derechos reservados";

        JOptionPane.showMessageDialog(this, mensaje, "Acerca de Car-REnt",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Obtiene el controlador principal
     *
     * @return Controlador principal
     */
    public ControladorPrincipal getControlador() {
        return controlador;
    }
}
