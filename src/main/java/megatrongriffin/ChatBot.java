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

    private String processListCommand(ToDoList list) {
        StringBuilder response = new StringBuilder();
        response.append("I... I made a list of your tasks... don't laugh or anything\n");
        response.append(list.toString());
        return response.toString();
    }

    private String processMarkCommand(ToDoList list, String argument, TaskStorage file, String command) throws MissingTaskNumberException {
        if (argument.isEmpty() || !argument.matches("\\d+")) {
            throw new MissingTaskNumberException(command);
        }
        ToDoItem item = list.getItem(Integer.parseInt(argument));
        item.setDone(true);
        StringBuilder response = new StringBuilder();
        response.append("Uh... okay... I marked it as done... I guess.\n").append(item);
        file.save(list);
        return response.toString();
    }

    private String processUnmarkCommand(ToDoList list, String argument, TaskStorage file, String command) throws MissingTaskNumberException {
        StringBuilder response = new StringBuilder();
        if (argument.isEmpty() || !argument.matches("\\d+")) {
            throw new MissingTaskNumberException(command);
        }
        ToDoItem item2 = list.getItem(Integer.parseInt(argument));
        item2.setDone(false);
        response.append("Uh... okay... I marked it as not done... I guess.\n").append(item2);
        file.save(list);
        return response.toString();
    }

    private String processDeadlineCommand(ToDoList list, String argument, TaskStorage file, String command) throws DescriptionException, DateException {
        StringBuilder response = new StringBuilder();
        if (argument.isEmpty()) {
            throw new DescriptionException(command);
        }
        String[] split = argument.split("/by", 2);
        if (split.length < 2) {
            throw new DateException(command);
        }

        String taskName = split[0];
        String time = split[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime deadline = LocalDateTime.parse(time, formatter);

        list.add(new DeadlineItem(taskName, deadline, false));
        file.save(list);
        response.append("Okay... I put it on the list. Happy now?\n\n");
        response.append(list.toString());
        return response.toString();
    }

    private String processEventCommand(ToDoList list, String argument, TaskStorage file, String command) throws DescriptionException {
        StringBuilder response = new StringBuilder();
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
        response.append("Ugh, fine... I added the task, okay?\n");
        response.append(list.toString());
        return response.toString();
    }

    private String processToDoCommand(ToDoList list, String argument, TaskStorage file, String command) throws DescriptionException {
        StringBuilder response = new StringBuilder();
        if (argument.isEmpty()) {
            throw new DescriptionException(command);
        }
        ToDoItem newItem = new ToDoItem(argument, false);
        list.add(newItem);
        file.save(list);
        response.append("Yeah, whatever... it's on the list now.\n");
        response.append(list.toString());
        return response.toString();
    }

    private String processDeleteCommand(ToDoList list, String argument, TaskStorage file, String command) throws MissingTaskNumberException {
        StringBuilder response = new StringBuilder();
        if (argument.isEmpty() || !argument.matches("\\d+")) {
            throw new MissingTaskNumberException(command);
        }
        response.append("Fine. I've removed this task completely:\n");
        response.append(list.delete(Integer.parseInt(argument)));
        file.save(list);
        return response.toString();
    }

    private String processFindCommand(ToDoList list, String argument, TaskStorage file, String command) throws DescriptionException {
        StringBuilder response = new StringBuilder();
        if (argument.isEmpty()) {
            throw new DescriptionException(command);
        }
        response.append("Uh... here are the, uh... matching tasks... in your list...\n");
        ToDoList searchResults = list.search(argument);
        response.append(searchResults.toString());
        return response.toString();
    }

    /**
     * Processes a single command and returns the response as a String
     * @param input The command string to process
     * @return String response that would normally be printed to console
     * @throws InputException
     */
    public String processCommand(String input) {

        try {
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";

            switch (command.toLowerCase()) {
            case "list":
                return processListCommand(list);

            case "mark":
                return processMarkCommand(list, argument, file, command);

            case "unmark":
                return processUnmarkCommand(list, argument, file, command);

            case "deadline":
                return processDeadlineCommand(list, argument, file, command);

            case "event":
                return processEventCommand(list, argument, file, command);

            case "todo":
                return processToDoCommand(list, argument, file, command);

            case "delete":
                return processDeleteCommand(list, argument, file, command);

            case "find":
                return processFindCommand(list, argument, file, command);

            default:
                throw new DescriptionException();
            }
        } catch (InputException e) {
            return e.getMessage();
        }
    }

    private void handleCommand(String input) throws InputException {
        String response = processCommand(input);
        System.out.println(response);
    }
}