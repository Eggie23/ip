import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MegatronGriffin {
    private static final ToDoList list = new ToDoList();

    public static void main(String[] args) {
        String start = "Uh… hi. It’s Meg. What do you want?\n";
        String end = "Uh… see ya. Try not to forget I exist, I guess.";
        System.out.println(start);

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";

            switch(command.toLowerCase()) {
                case "list":
                    System.out.println(list.toString());
                    break;
                case "mark":
                    String str = "Uh… okay… I marked it as done… I guess. \n";
                    ToDoItem item = list.getItem(Integer.parseInt(argument) - 1);
                    item.setDone(true);
                    str += item.toString();
                    System.out.println(str);
                    break;

                case "unmark":
                    String str2 = "Uh… okay… I marked it as not done… I guess.\n";
                    ToDoItem item2 = list.getItem(Integer.parseInt(argument) - 1);
                    item2.setDone(false);
                    str2 += item2.toString();
                    System.out.println(str2);
                    break;

                default:
                    ToDoItem newItem = new ToDoItem(input, false);
                    list.add(newItem);
                    System.out.println("added: " + input);
            }
            input = scanner.nextLine();
        }

        System.out.println(end);

    }

}
