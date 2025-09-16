package megatrongriffin;

public class ToDoItem {
    private boolean isDone;
    private String task;

    /**
     * Constructor for ToDoItem
     * @param task the description of ToDoItem
     * @param isDone if the task is completed or not
     */
    public ToDoItem(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    /**
     * Returns boolean of the task
     * @return
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks task as done
     * @param isDone
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns String, description of task of item
     * @return
     */
    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[T][X] " + this.task;
        } else {
            return "[T][ ] " + this.task;
        }
    }
}
