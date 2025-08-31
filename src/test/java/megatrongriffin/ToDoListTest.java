package megatrongriffin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoListTest {

    private ToDoList list;

    @BeforeEach
    void setUp() {
        list = new ToDoList();
    }

    @Test
    void testAddAndGetItemTest() {
        ToDoItem item1 = new ToDoItem("Task 1", false);
        ToDoItem item2 = new ToDoItem("Task 2", true);

        list.add(item1);
        list.add(item2);

        assertEquals(item1, list.getItem(1));
        assertEquals(item2, list.getItem(2));
    }

    @Test
    void testDelete() {
        ToDoItem item1 = new ToDoItem("Task 1", false);
        ToDoItem item2 = new ToDoItem("Task 2", false);

        list.add(item1);
        list.add(item2);

        ToDoItem removed = list.delete(1);
        assertEquals(item1, removed);
        assertEquals(item2, list.getItem(1)); // item2 should now be first
    }

    @Test
    void testLength() {
        assertEquals("Great. 0 tasks. Like it even matters.", list.length());

        list.add(new ToDoItem("Task 1", false));
        list.add(new ToDoItem("Task 2", false));

        assertEquals("Great. 2 tasks. Like it even matters.", list.length());
    }

    @Test
    void testToString() {
        list.add(new ToDoItem("Task 1", false));
        list.add(new ToDoItem("Task 2", true));

        String expected = "1. " + list.getItem(1).toString() + "\n" + "2. " + list.getItem(2).toString() + "\n";

        assertEquals(expected, list.toString());
    }

}
