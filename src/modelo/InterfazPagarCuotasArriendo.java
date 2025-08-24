package modelo;

import java.util.ArrayList;

/**
 * Interfaz para el manejo de pagos de cuotas de arriendo
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public interface InterfazPagarCuotasArriendo {

    /**
     * Busca un cliente por cédula
     *
     * @param cedula Cédula del cliente a buscar
     * @return Cliente encontrado o null si no existe
     */
    Cliente getCliente(String cedula);

    /**
     * Busca un arriendo por número
     *
     * @param numArriendo Número del arriendo a buscar
     * @return Arriendo encontrado o null si no existe
     */
    Arriendo getArriendo(int numArriendo);

    /**
     * Obtiene los arriendos de un cliente específico
     *
     * @param cedula Cédula del cliente
     * @return ArrayList con los arriendos del cliente
     */
    ArrayList<Arriendo> getArriendosCliente(String cedula);

    /**
     * Obtiene las cuotas pendientes de un arriendo
     *
     * @param numArriendo Número del arriendo
     * @return ArrayList con las cuotas pendientes
     */
    ArrayList<CuotaArriendo> getCuotasPendientes(int numArriendo);

    /**
     * Procesa el pago de una cuota
     *
     * @param numArriendo Número del arriendo
     * @param numCuota Número de la cuota a pagar
     * @return true si se procesó el pago correctamente, false en caso contrario
     */
    boolean pagarCuota(int numArriendo, int numCuota);

    /**
     * Verifica si todas las cuotas de un arriendo están pagadas
     *
     * @param numArriendo Número del arriendo
     * @return true si todas están pagadas, false en caso contrario
     */
    boolean arriendoCompletamentePagado(int numArriendo);

    /**
     * Obtiene el total de cuotas pagadas de un arriendo
     *
     * @param numArriendo Número del arriendo
     * @return Número de cuotas pagadas
     */
    int getCuotasPagadas(int numArriendo);

    /**
     * Obtiene el monto total pendiente de un arriendo
     *
     * @param numArriendo Número del arriendo
     * @return Monto total pendiente
     */
    int getMontoPendiente(int numArriendo);
}
