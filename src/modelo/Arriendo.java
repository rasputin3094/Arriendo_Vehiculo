package modelo;

import java.util.ArrayList;

/**
 * Clase Arriendo que representa un arriendo de vehículo
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public class Arriendo {

    private int numArriendo;
    private String fechaArriendo;
    private int diasArriendo;
    private int precioDia;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private ArrayList<CuotaArriendo> cuotas;

    /**
     * Constructor por defecto
     */
    public Arriendo() {
        this.numArriendo = 0;
        this.fechaArriendo = "";
        this.diasArriendo = 0;
        this.precioDia = 0;
        this.cliente = null;
        this.vehiculo = null;
        this.cuotas = new ArrayList<>();
    }

    /**
     * Constructor con parámetros
     *
     * @param numArriendo Número del arriendo
     * @param fechaArriendo Fecha del arriendo
     * @param diasArriendo Días de arriendo
     * @param precioDia Precio por día
     * @param cliente Cliente que arrienda
     * @param vehiculo Vehículo arrendado
     */
    public Arriendo(int numArriendo, String fechaArriendo, int diasArriendo, int precioDia,
            Cliente cliente, Vehiculo vehiculo) {
        this.numArriendo = numArriendo;
        this.fechaArriendo = fechaArriendo;
        this.diasArriendo = diasArriendo;
        this.precioDia = precioDia;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.cuotas = new ArrayList<>();
    }

    // Métodos getter y setter
    public int getNumArriendo() {
        return numArriendo;
    }

    public void setNumArriendo(int numArriendo) {
        if (numArriendo > 0) {
            this.numArriendo = numArriendo;
        }
    }

    public String getFechaArriendo() {
        return fechaArriendo;
    }

    public void setFechaArriendo(String fechaArriendo) {
        if (fechaArriendo != null && !fechaArriendo.trim().isEmpty()) {
            this.fechaArriendo = fechaArriendo;
        }
    }

    public int getDiasArriendo() {
        return diasArriendo;
    }

    public void setDiasArriendo(int diasArriendo) {
        if (diasArriendo > 0) {
            this.diasArriendo = diasArriendo;
        }
    }

    public int getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(int precioDia) {
        if (precioDia > 0) {
            this.precioDia = precioDia;
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public ArrayList<CuotaArriendo> getCuotas() {
        return cuotas;
    }

    public void setCuotas(ArrayList<CuotaArriendo> cuotas) {
        this.cuotas = cuotas;
    }

    /**
     * Calcula y retorna el monto total del arriendo
     *
     * @return Monto total del arriendo
     */
    public int obtenerMonto() {
        return diasArriendo * precioDia;
    }

    /**
     * Evalúa si el arriendo puede realizarse
     *
     * @return true si es válido, false en caso contrario
     */
    public boolean evaluarArriendo() {
        System.out.println("=== EVALUANDO ARRIENDO ===");

        if (cliente == null) {
            System.out.println("Error: Cliente es null");
            return false;
        }

        if (vehiculo == null) {
            System.out.println("Error: Vehiculo es null");
            return false;
        }

        if (!cliente.isVigente()) {
            System.out.println("Error: Cliente no está vigente");
            return false;
        }

        if (vehiculo.getCondicion() != 'D') {
            System.out.println("Error: Vehículo no está disponible. Condición: " + vehiculo.getCondicion());
            return false;
        }

        if (diasArriendo <= 0) {
            System.out.println("Error: Días de arriendo inválidos: " + diasArriendo);
            return false;
        }

        if (precioDia <= 0) {
            System.out.println("Error: Precio por día inválido: " + precioDia);
            return false;
        }

        System.out.println("Evaluación exitosa - Arriendo válido");
        return true;
    }

    /**
     * Genera las cuotas del arriendo
     *
     * @param numCuotas Número de cuotas a generar
     * @return ArrayList con las cuotas generadas
     */
    public ArrayList<CuotaArriendo> generarCuotas(int numCuotas) {
        if (numCuotas <= 0) {
            return cuotas;
        }

        cuotas.clear();
        int montoTotal = obtenerMonto();
        int valorCuota = montoTotal / numCuotas;

        for (int i = 1; i <= numCuotas; i++) {
            CuotaArriendo cuota = new CuotaArriendo(i, valorCuota, false);
            cuotas.add(cuota);
        }

        return cuotas;
    }

    /**
     * Ingresa un arriendo con cuotas al sistema
     *
     * @param numCuotas Número de cuotas
     * @return true si se ingresó correctamente, false en caso contrario
     */
    public boolean ingresarArriendoConCuota(int numCuotas) {
        if (!evaluarArriendo()) {
            return false;
        }

        // Cambiar estado del vehículo a arrendado
        vehiculo.setCondicion('A');

        // Generar cuotas
        generarCuotas(numCuotas);

        return true;
    }

    /**
     * Paga una cuota específica
     *
     * @param numCuota Número de cuota a pagar
     * @return true si se pagó correctamente, false en caso contrario
     */
    public boolean pagarCuota(int numCuota) {
        for (CuotaArriendo cuota : cuotas) {
            if (cuota.getNumCuota() == numCuota && !cuota.isPagada()) {
                cuota.setPagada(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Método para mostrar información del arriendo
     *
     * @param mensaje Mensaje a mostrar
     * @param tipo Tipo de mensaje
     * @return String con la información formateada
     */
    public String mostrarInfo(String mensaje, String tipo) {
        return tipo + ": " + mensaje + " - Arriendo #" + numArriendo
                + " - Cliente: " + (cliente != null ? cliente.getNombre() : "N/A")
                + " - Vehículo: " + (vehiculo != null ? vehiculo.getPatente() : "N/A")
                + " - Monto: $" + obtenerMonto();
    }

    @Override
    public String toString() {
        return "Arriendo #" + numArriendo + " - " + fechaArriendo
                + " - " + (cliente != null ? cliente.getNombre() : "N/A")
                + " - " + (vehiculo != null ? vehiculo.getPatente() : "N/A")
                + " - $" + obtenerMonto();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Arriendo arriendo = (Arriendo) obj;
        return numArriendo == arriendo.numArriendo;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numArriendo);
    }
}
