package Excepciones;

/**
 *
 * @author Francisco
 */
public class DatabaseException extends Exception {

    /**
     * Creates a new instance of <code>DatabaseException</code> without detail
     * message.
     */
    public DatabaseException() {
    }

    /**
     * Constructs an instance of <code>DatabaseException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DatabaseException(String msg) {
        super(msg);
    }
}
