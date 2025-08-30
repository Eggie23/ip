package MegatronGriffin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineItem extends ToDoItem {
    private LocalDateTime dueBy;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma");

    public DeadlineItem(String task, LocalDateTime dueBy, boolean isDone) {
        super(task, isDone);
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "[D][X] " + this.getTask() + "(by: " + this.dueBy.format(formatter) + ")";
        } else {
            return "[D][ ] " + this.getTask() + "(by:" + this.dueBy.format(formatter) +")";
        }
    }
}
