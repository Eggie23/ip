package megatrongriffin;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main application class for the MegatronGriffin chatbot.
 * Initializes the chatbot with file storage and provides methods for interaction.
 */
public class MegatronGriffin {
    private static ToDoList list;
    private ChatBot bot;

    /**
     * Constructor that initializes the ChatBot with file storage
     */
    public MegatronGriffin() throws InputException {
        Path filePath = Paths.get("data", "tasks.txt");
        TaskStorage file = new TaskStorage(filePath);
        list = (ToDoList) file.load();
        this.bot = new ChatBot(list, file);
        assert this.bot != null : "ChatBot is not instantiated properly";
    }

    /**
     * Processes user input and returns the chatbot's response.
     *
     * @param input the user's command or message
     * @return the chatbot's response as a string
     */
    public String getResponse(String input) {
        return bot.processCommand(input);
    }

    /**
     * Main method for running the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

    }

}
