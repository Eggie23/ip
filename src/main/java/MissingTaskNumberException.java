public class MissingTaskNumberException extends InputException {

    public MissingTaskNumberException(String command) {
        super("Seriously? You typed '" + command + "' but didn’t even say which task. Classic.");
    }
}
