package MegatronGriffin;

public class ToDoItem {
    private boolean done;
    private String task;

    public ToDoItem(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

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
