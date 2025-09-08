package megatrongriffin;

public class DescriptionException extends InputException {
    public DescriptionException(String message) {
        super("Wow, genius. A " + message + " with no description. That’s super useful.");
    }

    public DescriptionException() {
        super("Uh... yeah, I have no idea what that's supposed to mean.");
    }

}
