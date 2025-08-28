import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


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

        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";

            switch(command.toLowerCase()) {
                case "list":
                    System.out.println("I… I made a list of your tasks… don’t laugh or anything");
                    System.out.println(list);
                    break;
                case "mark":
                    if (argument.isEmpty() || !argument.matches("\\d+")) {
                        throw new MissingTaskNumberException(command);
                    }
                    String str = "Uh… okay… I marked it as done… I guess. \n";
                    ToDoItem item = list.getItem(Integer.parseInt(argument));
                    item.setDone(true);
                    str += item.toString();
                    System.out.println(str);
                    file.save(list);
                    break;

                case "unmark":
                    if (argument.isEmpty() || !argument.matches("\\d+")) {
                        throw new MissingTaskNumberException(command);
                    }
                    String str2 = "Uh… okay… I marked it as not done… I guess.\n";
                    ToDoItem item2 = list.getItem(Integer.parseInt(argument));
                    item2.setDone(false);
                    str2 += item2.toString();
                    System.out.println(str2);
                    file.save(list);
                    break;

                case "deadline":
                    if (argument.isEmpty()) {
                        throw new DescriptionException(command);
                    }
                    String str3 = "Okay… I put it on the list. Happy now?\n";
                    String[] split = argument.split("/by", 2);
                    String taskName = split[0];
                    String deadline = split[1];
                    if (deadline.isEmpty()) {
                        throw new DateException(command);
                    }
                    list.add(new DeadlineItem(taskName, deadline));
                    file.save(list);
                    System.out.println(str3);
                    System.out.println(list);
                    System.out.println(list.length());
                    file.save(list);
                    break;

                case "event":
                    if (argument.isEmpty()) {
                        throw new DescriptionException(command);
                    }
                    String str4 = "Ugh, fine… I added the task, okay?";
                    String[] spl = argument.split("/from", 2);
                    String task = spl[0];
                    String[] spl2 = spl[1].split("/to", 2);
                    String from = spl2[0];
                    String to = spl2[1];
                    if (from.isEmpty() || to.isEmpty()) {
                        throw new DateException(command);
                    }
                    list.add(new EventItem(task, from, to));
                    System.out.println(str4);
                    System.out.println(list);
                    System.out.println(list.length());
                    file.save(list);
                    break;

                case "todo":
                    if (argument.isEmpty()) {
                        throw new DescriptionException(command);
                    }
                    ToDoItem newItem = new ToDoItem(argument, false);
                    list.add(newItem);
                    System.out.println("Yeah, whatever… it’s on the list now.");
                    System.out.println(list);
                    System.out.println(list.length());
                    file.save(list);
                    break;

                case "delete":
                    if (argument.isEmpty() || !argument.matches("\\d+")) {
                        throw new MissingTaskNumberException(command);
                    }
                    System.out.println("Fine. I’ve removed this task completely:");
                    System.out.println(list.delete(Integer.parseInt(argument)));
                    System.out.println(list.length());
                    file.save(list);
                    break;

                default:
                    throw new DescriptionException();
            }
            input = scanner.nextLine();
        }

        System.out.println(end);

    }

}
