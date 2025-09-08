package megatrongriffin;

public class ToDoItem {
    private boolean done;
    private String task;

    /**
     * Constructor for ToDoItem
     * @param task the description of ToDoItem
     * @param done if the task is completed or not
     */
    public ToDoItem(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    /**
     * Returns boolean of the task
     * @return
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Marks task as done
     * @param done
     */
    public void setDone(boolean done) {
        this.done = done;
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
        if (done) {
            return "[T][X] " + this.task;
        } else {
            return "[T][ ] " + this.task;
        }
    }
}
