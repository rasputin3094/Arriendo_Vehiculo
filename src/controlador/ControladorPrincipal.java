package controlador;

import java.util.ArrayList;
import modelo.*;

/**
 * Controlador principal del sistema de arriendo de vehículos Implementa todas
 * las interfaces del modelo
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public class ControladorPrincipal implements InterfazClientes, InterfazArriendoConCuotas, InterfazPagarCuotasArriendo {

    private ArrayList<Cliente> clientes;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Arriendo> arriendos;

    /**
     * Constructor del controlador principal
     */
    public ControladorPrincipal() {
        this.clientes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.arriendos = new ArrayList<>();
        inicializarDatos();
    }

    /**
     * Inicializa el sistema con datos de prueba
     */
    private void inicializarDatos() {
        // Crear algunos clientes de prueba
        clientes.add(new Cliente("12345678-9", "Juan Pérez", true));
        clientes.add(new Cliente("98765432-1", "María González", true));
        clientes.add(new Cliente("11111111-1", "Pedro Rodríguez", false));

        // Crear algunos vehículos de prueba
        vehiculos.add(new Vehiculo("ABC123", "Toyota Corolla", 'D'));
        vehiculos.add(new Vehiculo("XYZ789", "Honda Civic", 'D'));
        vehiculos.add(new Vehiculo("DEF456", "Nissan Sentra", 'M'));
    }

    // Implementación de InterfazClientes
    @Override
    public Cliente getCliente(String cedula) {
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public boolean addCliente(Cliente cliente) {
        if (cliente == null || getCliente(cliente.getCedula()) != null) {
            return false;
        }
        return clientes.add(cliente);
    }

    @Override
    public ArrayList<Cliente> getClientes() {
        return new ArrayList<>(clientes);
    }

    @Override
    public boolean updateCliente(Cliente cliente) {
        if (cliente == null) {
            return false;
        }

        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCedula().equals(cliente.getCedula())) {
                clientes.set(i, cliente);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteCliente(String cedula) {
        return clientes.removeIf(cliente -> cliente.getCedula().equals(cedula));
    }

    // Implementación de InterfazArriendoConCuotas
    @Override
    public Arriendo getArriendo(int numArriendo) {
        for (Arriendo arriendo : arriendos) {
            if (arriendo.getNumArriendo() == numArriendo) {
                return arriendo;
            }
        }
        return null;
    }

    @Override
    public boolean addArriendo(Arriendo arriendo) {
        if (arriendo == null || getArriendo(arriendo.getNumArriendo()) != null) {
            return false;
        }

        // La validación de evaluarArriendo() ya fue hecha antes de llamar ingresarArriendoConCuota()
        // No necesitamos volver a validar aquí porque el vehículo ya cambió su estado
        return arriendos.add(arriendo);
    }

    @Override
    public ArrayList<Arriendo> getArriendos() {
        return new ArrayList<>(arriendos);
    }

    @Override
    public ArrayList<Arriendo> getArriendosPorCliente(String cedula) {
        ArrayList<Arriendo> resultado = new ArrayList<>();
        for (Arriendo arriendo : arriendos) {
            if (arriendo.getCliente() != null && arriendo.getCliente().getCedula().equals(cedula)) {
                resultado.add(arriendo);
            }
        }
        return resultado;
    }

    @Override
    public ArrayList<Arriendo> getArriendosPorVehiculo(String patente) {
        ArrayList<Arriendo> resultado = new ArrayList<>();
        for (Arriendo arriendo : arriendos) {
            if (arriendo.getVehiculo() != null && arriendo.getVehiculo().getPatente().equals(patente)) {
                resultado.add(arriendo);
            }
        }
        return resultado;
    }

    @Override
    public boolean updateArriendo(Arriendo arriendo) {
        if (arriendo == null) {
            return false;
        }

        for (int i = 0; i < arriendos.size(); i++) {
            if (arriendos.get(i).getNumArriendo() == arriendo.getNumArriendo()) {
                arriendos.set(i, arriendo);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteArriendo(int numArriendo) {
        return arriendos.removeIf(arriendo -> arriendo.getNumArriendo() == numArriendo);
    }

    @Override
    public boolean calcularPagoCuota(int numArriendo, int numCuota) {
        Arriendo arriendo = getArriendo(numArriendo);
        if (arriendo != null) {
            return arriendo.pagarCuota(numCuota);
        }
        return false;
    }

    // Implementación de InterfazPagarCuotasArriendo
    @Override
    public ArrayList<Arriendo> getArriendosCliente(String cedula) {
        return getArriendosPorCliente(cedula);
    }

    @Override
    public ArrayList<CuotaArriendo> getCuotasPendientes(int numArriendo) {
        Arriendo arriendo = getArriendo(numArriendo);
        if (arriendo == null) {
            return new ArrayList<>();
        }

        ArrayList<CuotaArriendo> pendientes = new ArrayList<>();
        for (CuotaArriendo cuota : arriendo.getCuotas()) {
            if (!cuota.isPagada()) {
                pendientes.add(cuota);
            }
        }
        return pendientes;
    }

    @Override
    public boolean pagarCuota(int numArriendo, int numCuota) {
        return calcularPagoCuota(numArriendo, numCuota);
    }

    @Override
    public boolean arriendoCompletamentePagado(int numArriendo) {
        Arriendo arriendo = getArriendo(numArriendo);
        if (arriendo == null) {
            return false;
        }

        for (CuotaArriendo cuota : arriendo.getCuotas()) {
            if (!cuota.isPagada()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getCuotasPagadas(int numArriendo) {
        Arriendo arriendo = getArriendo(numArriendo);
        if (arriendo == null) {
            return 0;
        }

        int pagadas = 0;
        for (CuotaArriendo cuota : arriendo.getCuotas()) {
            if (cuota.isPagada()) {
                pagadas++;
            }
        }
        return pagadas;
    }

    @Override
    public int getMontoPendiente(int numArriendo) {
        Arriendo arriendo = getArriendo(numArriendo);
        if (arriendo == null) {
            return 0;
        }

        int montoPendiente = 0;
        for (CuotaArriendo cuota : arriendo.getCuotas()) {
            if (!cuota.isPagada()) {
                montoPendiente += cuota.getValorCuota();
            }
        }
        return montoPendiente;
    }

    // Métodos adicionales para manejo de vehículos
    /**
     * Obtiene un vehículo por patente
     *
     * @param patente Patente del vehículo
     * @return Vehículo encontrado o null si no existe
     */
    public Vehiculo getVehiculo(String patente) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPatente().equals(patente)) {
                return vehiculo;
            }
        }
        return null;
    }

    /**
     * Agrega un nuevo vehículo
     *
     * @param vehiculo Vehículo a agregar
     * @return true si se agregó correctamente, false en caso contrario
     */
    public boolean addVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null || getVehiculo(vehiculo.getPatente()) != null) {
            return false;
        }
        return vehiculos.add(vehiculo);
    }

    /**
     * Obtiene todos los vehículos
     *
     * @return ArrayList con todos los vehículos
     */
    public ArrayList<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos);
    }

    /**
     * Obtiene vehículos disponibles para arriendo
     *
     * @return ArrayList con vehículos disponibles
     */
    public ArrayList<Vehiculo> getVehiculosDisponibles() {
        ArrayList<Vehiculo> disponibles = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getCondicion() == 'D') {
                disponibles.add(vehiculo);
            }
        }
        return disponibles;
    }

    /**
     * Actualiza la información de un vehículo
     *
     * @param vehiculo Vehículo con la información actualizada
     * @return true si se actualizó correctamente, false en caso contrario
     */
    public boolean updateVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            return false;
        }

        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getPatente().equals(vehiculo.getPatente())) {
                vehiculos.set(i, vehiculo);
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina un vehículo del sistema
     *
     * @param patente Patente del vehículo a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean deleteVehiculo(String patente) {
        return vehiculos.removeIf(vehiculo -> vehiculo.getPatente().equals(patente));
    }

    /**
     * Genera el próximo número de arriendo disponible
     *
     * @return Número de arriendo disponible
     */
    public int getProximoNumeroArriendo() {
        int maximo = 0;
        for (Arriendo arriendo : arriendos) {
            if (arriendo.getNumArriendo() > maximo) {
                maximo = arriendo.getNumArriendo();
            }
        }
        return maximo + 1;
    }
}
