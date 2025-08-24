package modelo;

/**
 * Clase Vehiculo que representa un vehículo en el sistema de arriendo
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public class Vehiculo {

    private String patente;
    private String marca;
    private char condicion;

    /**
     * Constructor por defecto
     */
    public Vehiculo() {
        this.patente = "";
        this.marca = "";
        this.condicion = 'D'; // D = Disponible por defecto
    }

    /**
     * Constructor con parámetros
     *
     * @param patente Patente del vehículo
     * @param marca Marca del vehículo
     * @param condicion Condición del vehículo (D=Disponible, A=Arrendado,
     * M=Mantenimiento)
     */
    public Vehiculo(String patente, String marca, char condicion) {
        this.patente = patente;
        this.marca = marca;
        this.condicion = condicion;
    }

    // Métodos getter y setter
    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        if (patente != null && !patente.trim().isEmpty()) {
            this.patente = patente;
        }
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (marca != null && !marca.trim().isEmpty()) {
            this.marca = marca;
        }
    }

    public char getCondicion() {
        return condicion;
    }

    public void setCondicion(char condicion) {
        if (condicion == 'D' || condicion == 'A' || condicion == 'M') {
            this.condicion = condicion;
        }
    }

    /**
     * Método para mostrar información del vehículo
     *
     * @param mensaje Mensaje a mostrar
     * @param tipo Tipo de mensaje
     * @return String con la información formateada
     */
    public String mostrarInfo(String mensaje, String tipo) {
        String estado = "";
        switch (condicion) {
            case 'D':
                estado = "Disponible";
                break;
            case 'A':
                estado = "Arrendado";
                break;
            case 'M':
                estado = "Mantenimiento";
                break;
        }
        return tipo + ": " + mensaje + " - Vehículo: " + marca + " (" + patente + ") - Estado: " + estado;
    }

    /**
     * Verifica si el vehículo está disponible para arriendo
     *
     * @return true si está disponible, false en caso contrario
     */
    public boolean estaDisponible() {
        return condicion == 'D';
    }

    @Override
    public String toString() {
        String estado = "";
        switch (condicion) {
            case 'D':
                estado = "Disponible";
                break;
            case 'A':
                estado = "Arrendado";
                break;
            case 'M':
                estado = "Mantenimiento";
                break;
        }
        return marca + " - " + patente + " (" + estado + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vehiculo vehiculo = (Vehiculo) obj;
        return patente.equals(vehiculo.patente);
    }

    @Override
    public int hashCode() {
        return patente.hashCode();
    }
}
