
import controlador.ControladorPrincipal;
import java.util.ArrayList;
import modelo.*;

/**
 * Clase para probar todas las funcionalidades del sistema
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public class PruebasSistema {

    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DEL SISTEMA CAR-RENT ===\n");

        ControladorPrincipal controlador = new ControladorPrincipal();

        // Probar gestión de clientes
        probarGestionClientes(controlador);

        // Probar gestión de vehículos
        probarGestionVehiculos(controlador);

        // Probar creación de arriendos
        probarCreacionArriendos(controlador);

        // Probar pago de cuotas
        probarPagoCuotas(controlador);

        System.out.println("\n=== PRUEBAS COMPLETADAS ===");
    }

    private static void probarGestionClientes(ControladorPrincipal controlador) {
        System.out.println("1. PROBANDO GESTIÓN DE CLIENTES");
        System.out.println("================================");

        // Mostrar clientes existentes
        ArrayList<Cliente> clientes = controlador.getClientes();
        System.out.println("Clientes en el sistema: " + clientes.size());
        for (Cliente cliente : clientes) {
            System.out.println("  - " + cliente.toString());
        }

        // Agregar nuevo cliente
        Cliente nuevoCliente = new Cliente("55555555-5", "Cliente Prueba", true);
        boolean agregado = controlador.addCliente(nuevoCliente);
        System.out.println("Agregar nuevo cliente: " + (agregado ? "EXITOSO" : "FALLIDO"));

        // Verificar que se agregó
        Cliente buscado = controlador.getCliente("55555555-5");
        System.out.println("Buscar cliente agregado: " + (buscado != null ? "ENCONTRADO" : "NO ENCONTRADO"));

        System.out.println();
    }

    private static void probarGestionVehiculos(ControladorPrincipal controlador) {
        System.out.println("2. PROBANDO GESTIÓN DE VEHÍCULOS");
        System.out.println("=================================");

        // Mostrar vehículos existentes
        ArrayList<Vehiculo> vehiculos = controlador.getVehiculos();
        System.out.println("Vehículos en el sistema: " + vehiculos.size());
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println("  - " + vehiculo.toString());
        }

        // Mostrar vehículos disponibles
        ArrayList<Vehiculo> disponibles = controlador.getVehiculosDisponibles();
        System.out.println("Vehículos disponibles: " + disponibles.size());
        for (Vehiculo vehiculo : disponibles) {
            System.out.println("  - " + vehiculo.toString());
        }

        System.out.println();
    }

    private static void probarCreacionArriendos(ControladorPrincipal controlador) {
        System.out.println("3. PROBANDO CREACIÓN DE ARRIENDOS");
        System.out.println("==================================");

        // Obtener un cliente vigente y un vehículo disponible
        Cliente cliente = null;
        for (Cliente c : controlador.getClientes()) {
            if (c.isVigente()) {
                cliente = c;
                break;
            }
        }

        Vehiculo vehiculo = null;
        for (Vehiculo v : controlador.getVehiculosDisponibles()) {
            vehiculo = v;
            break;
        }

        if (cliente != null && vehiculo != null) {
            System.out.println("Cliente seleccionado: " + cliente.getNombre());
            System.out.println("Vehículo seleccionado: " + vehiculo.getPatente());

            // Crear arriendo
            int numArriendo = controlador.getProximoNumeroArriendo();
            Arriendo arriendo = new Arriendo(numArriendo, "2025-08-23", 5, 25000, cliente, vehiculo);

            // Probar evaluación
            boolean evaluacion = arriendo.evaluarArriendo();
            System.out.println("Evaluación del arriendo: " + (evaluacion ? "VÁLIDO" : "INVÁLIDO"));

            if (evaluacion) {
                // Ingresar arriendo con cuotas
                boolean ingresado = arriendo.ingresarArriendoConCuota(3);
                System.out.println("Ingresar arriendo con cuotas: " + (ingresado ? "EXITOSO" : "FALLIDO"));

                if (ingresado) {
                    // Agregar al controlador
                    boolean agregado = controlador.addArriendo(arriendo);
                    System.out.println("Agregar al sistema: " + (agregado ? "EXITOSO" : "FALLIDO"));

                    // Mostrar cuotas generadas
                    ArrayList<CuotaArriendo> cuotas = arriendo.getCuotas();
                    System.out.println("Cuotas generadas: " + cuotas.size());
                    for (CuotaArriendo cuota : cuotas) {
                        System.out.println("  - " + cuota.toString());
                    }
                }
            }
        } else {
            System.out.println("No hay clientes vigentes o vehículos disponibles para probar");
        }

        System.out.println();
    }

    private static void probarPagoCuotas(ControladorPrincipal controlador) {
        System.out.println("4. PROBANDO PAGO DE CUOTAS");
        System.out.println("===========================");

        ArrayList<Arriendo> arriendos = controlador.getArriendos();
        if (!arriendos.isEmpty()) {
            Arriendo arriendo = arriendos.get(0);
            System.out.println("Probando pago en arriendo #" + arriendo.getNumArriendo());

            // Mostrar cuotas antes del pago
            System.out.println("Cuotas antes del pago:");
            for (CuotaArriendo cuota : arriendo.getCuotas()) {
                System.out.println("  - " + cuota.toString());
            }

            // Pagar primera cuota
            boolean pagada = controlador.pagarCuota(arriendo.getNumArriendo(), 1);
            System.out.println("Pagar primera cuota: " + (pagada ? "EXITOSO" : "FALLIDO"));

            // Mostrar cuotas después del pago
            System.out.println("Cuotas después del pago:");
            for (CuotaArriendo cuota : arriendo.getCuotas()) {
                System.out.println("  - " + cuota.toString());
            }

            // Verificar cuotas pendientes
            ArrayList<CuotaArriendo> pendientes = controlador.getCuotasPendientes(arriendo.getNumArriendo());
            System.out.println("Cuotas pendientes: " + pendientes.size());

        } else {
            System.out.println("No hay arriendos para probar el pago de cuotas");
        }

        System.out.println();
    }
}
