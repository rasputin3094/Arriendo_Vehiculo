package modelo;

/**
 * Clase CuotaArriendo que representa una cuota de un arriendo
 *
 * @author JOSE RIOS SILVA
 * @author JOSÉ PARRA BARONCINI
 * @author ALEJANDRO MORALES VALENZUELA
 */
public class CuotaArriendo {

    private int numCuota;
    private int valorCuota;
    private boolean pagada;

    /**
     * Constructor por defecto
     */
    public CuotaArriendo() {
        this.numCuota = 0;
        this.valorCuota = 0;
        this.pagada = false;
    }

    /**
     * Constructor con parámetros
     *
     * @param numCuota Número de la cuota
     * @param valorCuota Valor de la cuota
     * @param pagada Estado de pago de la cuota
     */
    public CuotaArriendo(int numCuota, int valorCuota, boolean pagada) {
        this.numCuota = numCuota;
        this.valorCuota = valorCuota;
        this.pagada = pagada;
    }

    // Métodos getter y setter
    public int getNumCuota() {
        return numCuota;
    }

    public void setNumCuota(int numCuota) {
        if (numCuota > 0) {
            this.numCuota = numCuota;
        }
    }

    public int getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(int valorCuota) {
        if (valorCuota > 0) {
            this.valorCuota = valorCuota;
        }
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    /**
     * Método para mostrar información de la cuota
     *
     * @param mensaje Mensaje a mostrar
     * @param tipo Tipo de mensaje
     * @return String con la información formateada
     */
    public String mostrarInfo(String mensaje, String tipo) {
        return tipo + ": " + mensaje + " - Cuota #" + numCuota + " - Valor: $" + valorCuota
                + " - Estado: " + (pagada ? "Pagada" : "Pendiente");
    }

    @Override
    public String toString() {
        return "Cuota #" + numCuota + " - $" + valorCuota + " (" + (pagada ? "Pagada" : "Pendiente") + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CuotaArriendo cuota = (CuotaArriendo) obj;
        return numCuota == cuota.numCuota;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numCuota);
    }
}
