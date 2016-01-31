package Excepciones;

/**
 *
 * @author Francisco
 */
public class TrabajadorYaRegistradoException extends Exception {

    /**
     * Creates a new instance of <code>TrabajadorYaRegistradoException</code>
     * without detail message.
     */
    public TrabajadorYaRegistradoException() {
    }

    /**
     * Constructs an instance of <code>TrabajadorYaRegistradoException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public TrabajadorYaRegistradoException(String msg) {
        super(msg);
    }
}
