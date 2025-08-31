import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import megatrongriffin.*;


public class MegatronGriffin {
    private static ToDoList list;

    public static void main(String[] args) throws InputException {


        String start = "Uh… hi. It’s Meg. What do you want?\n";
        String end = "Uh… see ya. Try not to forget I exist, I guess.";
        System.out.println(start);

        Path filePath = Paths.get("data", "tasks.txt");

        TaskStorage file = new TaskStorage(filePath);
        list = (ToDoList) file.load();

        Scanner scanner = new Scanner(System.in);

        ChatBot bot = new ChatBot(list, file);
        bot.run();
        System.out.println(end);

    }

}
