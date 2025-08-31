package MegatronGriffin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventItem extends ToDoItem {
    private LocalDateTime start;
    private LocalDateTime end;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma");

    /**
     * Constructor of EventItem
     * @param task is a string, descrption of task
     * @param start is a LocalDateTime object, dictates start time of event
     * @param end is a LocalDateTime object, dictates end time of event
     * @param isDone is a boolean, dictates whether event is done
     */

    public EventItem(String task, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(task, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "[E][X] " + this.getTask() + " (from: " + this.start.format(formatter) + " to:" + this.end.format(formatter) + ")";
        } else {
            return "[E][ ] " + this.getTask() + " (from: " + this.start.format(formatter) + " to: " + this.end.format(formatter) + ")";
        }
    }
}
