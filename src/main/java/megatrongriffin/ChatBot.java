package megatrongriffin;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ChatBot {
    private ToDoList list;
    private TaskStorage file;
    private Scanner scanner;

    /**
     * Constructor for ChatBot class
     * @param list Takes in a object of class ToDoList that will be updated
     * @param file The file that new tasks will be written to
     */
    public ChatBot(ToDoList list, TaskStorage file) {
        this.list = list;
        this.file = file;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Continuously reads for command-line user input
     * Terminates when the input is "bye"
     * Continuously calls function handleCommand
     * @throws InputException
     */
    public void run() throws InputException {
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            handleCommand(input);
            input = scanner.nextLine();
        }
    }

    private void handleCommand(String input) throws InputException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";

        switch(command.toLowerCase()) {
        case "list":
            System.out.println("I... I made a list of your tasks... don't laugh or anything");
            System.out.println(list);
            break;

        case "mark":
            if (argument.isEmpty() || !argument.matches("\\d+")) {
                throw new MissingTaskNumberException(command);
            }
            ToDoItem item = list.getItem(Integer.parseInt(argument));
            item.setDone(true);
            System.out.println("Uh... okay... I marked it as done... I guess.\n" + item);
            file.save(list);
            break;

        case "unmark":
            if (argument.isEmpty() || !argument.matches("\\d+")) {
                throw new MissingTaskNumberException(command);
            }
            ToDoItem item2 = list.getItem(Integer.parseInt(argument));
            item2.setDone(false);
            System.out.println("Uh... okay... I marked it as not done... I guess.\n" + item2);
            file.save(list);
            break;

        case "deadline":
            if (argument.isEmpty()) {
                throw new DescriptionException(command);
            }
            String[] split = argument.split("/by", 2);
            if (split.length < 2) throw new DateException(command);

            String taskName = split[0];
            String time = split[1].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime deadline = LocalDateTime.parse(time, formatter);

            list.add(new DeadlineItem(taskName, deadline, false));
            file.save(list);
            System.out.println("Okay... I put it on the list. Happy now?\n");
            System.out.println(list);
            break;

        case "event":
            if (argument.isEmpty()) {
                throw new DescriptionException(command);
            }
            String[] spl = argument.split("/from", 2);
            String task = spl[0];
            String[] spl2 = spl[1].split("/to", 2);
            LocalDateTime fromTime = LocalDateTime.parse(spl2[0].trim(),
                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            LocalDateTime toTime = LocalDateTime.parse(spl2[1].trim(),
                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

            list.add(new EventItem(task, fromTime, toTime, false));
            file.save(list);
            System.out.println("Ugh, fine... I added the task, okay?");
            System.out.println(list);
            break;

        case "todo":
            if (argument.isEmpty()) {
                throw new DescriptionException(command);
            }
            ToDoItem newItem = new ToDoItem(argument, false);
            list.add(newItem);
            file.save(list);
            System.out.println("Yeah, whatever... it's on the list now.");
            System.out.println(list);
            break;

        case "delete":
            if (argument.isEmpty() || !argument.matches("\\d+")) {
                throw new MissingTaskNumberException(command);
            }
            System.out.println("Fine. I've removed this task completely:");
            System.out.println(list.delete(Integer.parseInt(argument)));
            file.save(list);
            break;

        case "find":
            System.out.println(argument);
            if (argument.isEmpty()) {
                throw new MissingTaskNumberException(command);
            }
            System.out.println("Uh... here are the, uh... matching tasks... in your list...");
            ToDoList searchResults = list.search(argument);
            System.out.println(searchResults);
            break;

        default:
            throw new DescriptionException();
        }
    }
}
