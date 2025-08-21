public class DeadlineItem extends ToDoItem{
    private String dueBy;

    public DeadlineItem(String task, String dueBy){
        super(task, false);
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "[D][X] " + this.getTask() + "(by:" + this.dueBy +")";
        } else {
            return "[D][ ] " + this.getTask() + "(by:" + this.dueBy +")";
        }
    }
}
