package megatrongriffin;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;



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
    }

    public String getResponse(String input) {
        return bot.processCommand(input);
    }

    public String getEndMessage() {
        return "Uh... see ya. Try not to forget I exist, I guess.";
    }

    public static void main(String[] args) {

    }

}
