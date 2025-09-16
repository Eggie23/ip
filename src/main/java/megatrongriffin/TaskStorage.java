package megatrongriffin;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class TaskStorage {
    private final Path filePath;

    private void addToDoTask(ToDoList tasks, String description, String isDone) {
        if (isDone.equals("[ ]")) {
            tasks.add(new ToDoItem(description, false));
        } else {
            tasks.add(new ToDoItem(description, true));
        }
    }

    private void addDeadlineTask(ToDoList tasks, String description, String isDone) {
        String deadlineParts[] = description.split("\\(by:");
        String date = deadlineParts[1].replace(")", "").trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        if (isDone.equals("[ ]")) {
            tasks.add(new DeadlineItem(deadlineParts[0], dateTime, false));
        } else {
            tasks.add(new DeadlineItem(deadlineParts[0], dateTime, true));
        }
    }

    private void addEventTask(ToDoList tasks, String description, String isDone) {
        String eventParts[] = description.split("\\(from:|to:|\\)");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma");
        LocalDateTime from = LocalDateTime.parse(eventParts[1].trim(), formatter2);
        LocalDateTime to = LocalDateTime.parse(eventParts[2].trim(), formatter2);
        if (isDone.equals("[ ]")) {
            tasks.add(new EventItem(eventParts[0], from, to, false));
        } else {
            tasks.add(new EventItem(eventParts[0], from, to, true));
        }
    }
    /**
     * Constructor of TaskStorage
     * @param filePath is the filePath of tasks that is stored in hard disk's memory;
     */
    public TaskStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns ToDoList that contains saved tasks from hard disk
     * Reads from text file in hard disk with pre-saved tasks
     * If the file does not exist, creates file and returns empty ToDoList
     * Else, reads text in saved file and recreates tasks, appends to ToDoList
      * @return
     */
    public ToDoList load() {
        ToDoList tasks = new ToDoList();

        try {
            Files.createDirectories(filePath.getParent());

            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
                return tasks;
            }

            //Read file and insert into tasks
            List<String> lines = Files.readAllLines(filePath);
            for (String line: lines) {
                String[] parts = line.split("(?<=\\])(?=\\[)", 2);
                String taskType = parts[0];
                String rest = parts[1];
                String isDone = rest.substring(0, 3);
                String description = rest.substring(3).trim();
            switch(taskType) {
                case "[T]":
                    addToDoTask(tasks, description, isDone);
                    break;
                case "[D]":
                    addDeadlineTask(tasks, description, isDone);
                    break;
                case "[E]":
                    addEventTask(tasks, description, isDone);
                    break;
                }
            }
            return tasks;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Reads from ToDoList and writes into saved text file to store
     * current tasks
     * @param save
     */
    public void save(ToDoList save) {
        List<String> lines = save.toSave();
        try {
            Files.write(filePath, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
