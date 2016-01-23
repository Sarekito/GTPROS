package Excepciones;

/**
 *
 * @author Francisco
 */
public class ProyectoConEtapasAbiertasException extends Exception {

    /**
     * Creates a new instance of <code>ProyectoConEtapasAbiertasException</code>
     * without detail message.
     */
    public ProyectoConEtapasAbiertasException() {
    }

    /**
     * Constructs an instance of <code>ProyectoConEtapasAbiertasException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ProyectoConEtapasAbiertasException(String msg) {
        super(msg);
    }
}
