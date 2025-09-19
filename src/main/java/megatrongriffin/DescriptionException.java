package megatrongriffin;

/**
 * Exception thrown when a description is missing or invalid.
 * Extends InputException to provide specific error handling for cases
 * where required descriptions are not provided.
 */

public class DescriptionException extends InputException {
    /**
     * Constructs a DescriptionException with a specified message context.
     *
     * @param message the context or type of item that is missing a description
     */
    public DescriptionException(String message) {
        super("Wow, genius. A " + message + " with no description. That’s super useful.");
    }

    /**
     * Constructs a DescriptionException with a default error message.
     */
    public DescriptionException() {
        super("Uh... yeah, I have no idea what that's supposed to mean.");
    }

}
