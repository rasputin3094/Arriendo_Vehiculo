package modelo;

import java.util.ArrayList;

/**
 * Interfaz para el manejo de arriendos con cuotas
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public interface InterfazArriendoConCuotas {

    /**
     * Busca un arriendo por número
     *
     * @param numArriendo Número del arriendo a buscar
     * @return Arriendo encontrado o null si no existe
     */
    Arriendo getArriendo(int numArriendo);

    /**
     * Agrega un nuevo arriendo al sistema
     *
     * @param arriendo Arriendo a agregar
     * @return true si se agregó correctamente, false en caso contrario
     */
    boolean addArriendo(Arriendo arriendo);

    /**
     * Obtiene la lista de todos los arriendos
     *
     * @return ArrayList con todos los arriendos
     */
    ArrayList<Arriendo> getArriendos();

    /**
     * Busca arriendos por cliente
     *
     * @param cedula Cédula del cliente
     * @return ArrayList con los arriendos del cliente
     */
    ArrayList<Arriendo> getArriendosPorCliente(String cedula);

    /**
     * Busca arriendos por vehículo
     *
     * @param patente Patente del vehículo
     * @return ArrayList con los arriendos del vehículo
     */
    ArrayList<Arriendo> getArriendosPorVehiculo(String patente);

    /**
     * Actualiza la información de un arriendo
     *
     * @param arriendo Arriendo con la información actualizada
     * @return true si se actualizó correctamente, false en caso contrario
     */
    boolean updateArriendo(Arriendo arriendo);

    /**
     * Elimina un arriendo del sistema
     *
     * @param numArriendo Número del arriendo a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    boolean deleteArriendo(int numArriendo);

    /**
     * Calcula el pago de una cuota específica
     *
     * @param numArriendo Número del arriendo
     * @param numCuota Número de la cuota
     * @return true si se procesó el pago correctamente, false en caso contrario
     */
    boolean calcularPagoCuota(int numArriendo, int numCuota);
}
