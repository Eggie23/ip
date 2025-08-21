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
    }

    public ToDoItem getItem(int i) {
        return this.list.get(i);
    }

    public void increment() {
        this.length++;
    }

    public String length() {
        return "Great. " + this.length + " tasks. Like it even matters.";
    }

    @Override
    public String toString() {
       String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += String.format("%s. %s", i+1, list.get(i).toString());
        }
        return str;
    }
}
