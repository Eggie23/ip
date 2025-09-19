package megatrongriffin;

/**
 * Exception thrown when a command that requires a task number is missing the task number.
 * Provides a sarcastic error message indicating that the task number was not specified.
 */
public class MissingTaskNumberException extends InputException {

    /**
     * Constructs a MissingTaskNumberException with a command-specific error message.
     *
     * @param command the command that was missing a task number
     */

    public MissingTaskNumberException(String command) {
        super("Seriously? You typed '" + command + "' but didn’t even say which task. Classic.");
    }
}
