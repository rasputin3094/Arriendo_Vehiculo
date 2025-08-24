
import javax.swing.SwingUtilities;
import vista.VentanaPrincipal;

/**
 * Clase principal que inicia el sistema de arriendo de vehículos Car-REnt
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public class App {

    public static void main(String[] args) {
        // Iniciar la aplicación en el EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            try {
                // Crear y mostrar la ventana principal
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
                ventanaPrincipal.setVisible(true);

                System.out.println("Sistema Car-REnt iniciado exitosamente");
                System.out.println("====================================");
                System.out.println("Funcionalidades disponibles:");
                System.out.println("- Gestión de clientes");
                System.out.println("- Arriendo de vehículos con cuotas");
                System.out.println("- Pago de cuotas de arriendo");
                System.out.println("====================================");

            } catch (Exception e) {
                System.err.println("Error al iniciar la aplicación: " + e.getMessage());
                System.err.println("Detalles del error:");
                for (StackTraceElement element : e.getStackTrace()) {
                    System.err.println("  " + element.toString());
                }
            }
        });
    }
}
