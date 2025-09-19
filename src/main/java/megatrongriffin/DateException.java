package megatrongriffin;

public class DateException extends InputException {

    public DateException(String message) {
        super(message.equals("deadline")
                ? "Um... deadlines are kinda important, you know? Try: deadline return book /by 19/9/2025 2359 ...otherwise I'll just sit here staring at it."
                : "Uh... you kinda forgot when this event is supposed to happen. Try typing it like this: event project meeting /from 19/9/2025 1400 /to 19/9/2025 1600 ...so I don't have to sit here guessing.");
    }
}
