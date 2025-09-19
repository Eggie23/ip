package megatrongriffin;

/**
 * Base exception class for input-related errors in the application.
 * Extends Exception to handle various types of user input problems.
 */
public class InputException extends Exception {

    /**
     * Constructs an InputException with the specified error message.
     *
     * @param message the detail message explaining the input error
     */
    public InputException(String message) {
        super(message);
    }
}
