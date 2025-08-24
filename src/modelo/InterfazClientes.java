package modelo;

import java.util.ArrayList;

/**
 * Interfaz para el manejo de clientes
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public interface InterfazClientes {

    /**
     * Busca un cliente por cédula
     *
     * @param cedula Cédula del cliente a buscar
     * @return Cliente encontrado o null si no existe
     */
    Cliente getCliente(String cedula);

    /**
     * Agrega un nuevo cliente al sistema
     *
     * @param cliente Cliente a agregar
     * @return true si se agregó correctamente, false en caso contrario
     */
    boolean addCliente(Cliente cliente);

    /**
     * Obtiene la lista de todos los clientes
     *
     * @return ArrayList con todos los clientes
     */
    ArrayList<Cliente> getClientes();

    /**
     * Actualiza la información de un cliente
     *
     * @param cliente Cliente con la información actualizada
     * @return true si se actualizó correctamente, false en caso contrario
     */
    boolean updateCliente(Cliente cliente);

    /**
     * Elimina un cliente del sistema
     *
     * @param cedula Cédula del cliente a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    boolean deleteCliente(String cedula);
}
