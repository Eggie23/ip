public class EventItem extends ToDoItem{
    private String start;
    private String end;

    public EventItem(String task, String start, String end) {
        super(task, false);
        this.start = start;
        this.end = end;
    }

    public EventItem(String task, String start, String end, boolean isDone) {
        super(task, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "[E][X] " + this.getTask() + " (from:" + this.start + "to:" + this.end + ")";
        } else {
            return "[E][ ] " + this.getTask() + " (from:" + this.start + "to:" + this.end + ")";
        }
    }
}
