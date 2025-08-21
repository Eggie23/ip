import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private List<ToDoItem> list;

    public ToDoList() {
        this.list = new ArrayList<ToDoItem>();
    }

    public void add(ToDoItem item) {
        list.add(item);
    }

    public ToDoItem getItem(int i) {
        return this.list.get(i);
    }

    @Override
    public String toString() {
        String str = "I… I made a list of your tasks… don’t laugh or anything.\n";
        for (int i = 0; i < list.size(); i++) {
            str += String.format("%s. %s\n", i+1, list.get(i).toString());
        }
        return str;
    }
}
