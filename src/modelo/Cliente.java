package modelo;

/**
 * Clase Cliente que representa a un cliente en el sistema de arriendo de
 * vehículos
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public class Cliente {

    private String cedula;
    private String nombre;
    private boolean vigente;

    /**
     * Constructor por defecto
     */
    public Cliente() {
        this.cedula = "";
        this.nombre = "";
        this.vigente = true;
    }

    /**
     * Constructor con parámetros
     *
     * @param cedula Cédula del cliente
     * @param nombre Nombre del cliente
     * @param vigente Estado de vigencia del cliente
     */
    public Cliente(String cedula, String nombre, boolean vigente) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.vigente = vigente;
    }

    // Métodos getter y setter
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        if (cedula != null && !cedula.trim().isEmpty()) {
            this.cedula = cedula;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        }
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    /**
     * Método para mostrar información del cliente
     *
     * @param mensaje Mensaje a mostrar
     * @param tipo Tipo de mensaje
     * @return String con la información formateada
     */
    public String mostrarInfo(String mensaje, String tipo) {
        return tipo + ": " + mensaje + " - Cliente: " + nombre + " (Cédula: " + cedula + ")";
    }

    @Override
    public String toString() {
        return nombre + " - " + cedula + " (Vigente: " + (vigente ? "Sí" : "No") + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cliente cliente = (Cliente) obj;
        return cedula.equals(cliente.cedula);
    }

    @Override
    public int hashCode() {
        return cedula.hashCode();
    }
}
