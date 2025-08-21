import java.util.Scanner;

public class MegatronGriffin {
    private static final ToDoList list = new ToDoList();
    private static int length = 0;

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
                    System.out.println("I… I made a list of your tasks… don’t laugh or anything");
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

                case "deadline":
                    String str3 = "Okay… I put it on the list. Happy now?\n";
                    String[] split = argument.split("/by", 2);
                    String taskName = split[0];
                    String deadline = split[1];
                    list.add(new DeadlineItem(taskName, deadline));
                    System.out.println(str3);
                    System.out.println(list.toString());
                    list.increment();
                    System.out.println(list.length());
                    break;

                case "event":
                    String str4 = "Ugh, fine… I added the task, okay?";
                    String[] spl = argument.split("/from", 2);
                    String task = spl[0];
                    String[] spl2 = spl[1].split("/to", 2);
                    String from = spl2[0];
                    String to = spl2[1];
                    list.add(new EventItem(task, from, to));
                    System.out.println(str4);
                    System.out.println(list.toString());
                    list.increment();
                    System.out.println(list.length());
                    break;

                case "todo":
                    ToDoItem newItem = new ToDoItem(argument, false);
                    list.add(newItem);
                    System.out.println("Yeah, whatever… it’s on the list now.");
                    System.out.println(list.toString());
                    list.increment();
                    System.out.println(list.length());
                    break;

            }
            input = scanner.nextLine();
        }

        System.out.println(end);

    }

}
