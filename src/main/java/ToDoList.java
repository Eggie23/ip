import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToDoList {
    private List<ToDoItem> list;
    private int length;

    public ToDoList() {
        this.list = new ArrayList<ToDoItem>();
        this.length = 0;
    }

    public void add(ToDoItem item) {
        list.add(item);
        this.length++;
    }

    public ToDoItem getItem(int i) {
        return this.list.get(i - 1);
    }

    public String length() {
        return "Great. " + this.length + " tasks. Like it even matters.";
    }

    public ToDoItem delete(int i) {
        this.length--;
        return this.list.remove(i - 1);
    }

    @Override
    public String toString() {
       String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += String.format("%s. %s\n", i+1, list.get(i).toString());
        }
        return str;
    }

    public List<String> toSave() {
        List<String> tasks = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            tasks.add(list.get(i).toString());
        }

        return tasks;
    }
}