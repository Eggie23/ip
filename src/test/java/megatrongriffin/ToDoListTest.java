package megatrongriffin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for ToDoList functionality.
 * Tests the basic operations of adding, retrieving, deleting, and displaying tasks.
 */
public class ToDoListTest {

    private ToDoList list;

    /**
     * Sets up test environment before each test method.
     * Creates a new ToDoList instance for testing.
     */
    @BeforeEach
    void setUp() {
        list = new ToDoList();
    }

    /**
     * Tests the add and getItem functionality of ToDoList.
     * Verifies that items can be added and retrieved correctly by index.
     */
    @Test
    void testAddAndGetItemTest() {
        ToDoItem item1 = new ToDoItem("Task 1", false);
        ToDoItem item2 = new ToDoItem("Task 2", true);

        list.add(item1);
        list.add(item2);

        assertEquals(item1, list.getItem(1));
        assertEquals(item2, list.getItem(2));
    }

    /**
     * Tests the delete functionality of ToDoList.
     * Verifies that items can be removed and that remaining items shift positions correctly.
     */
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

    /**
     * Tests the length functionality of ToDoList.
     * Verifies that the correct count message is returned for different list sizes.
     */
    @Test
    void testLength() {
        assertEquals("Great. 0 tasks. Like it even matters.", list.length());

        list.add(new ToDoItem("Task 1", false));
        list.add(new ToDoItem("Task 2", false));

        assertEquals("Great. 2 tasks. Like it even matters.", list.length());
    }

    /**
     * Tests the toString functionality of ToDoList.
     * Verifies that the list displays correctly with numbered items.
     */
    @Test
    void testToString() {
        list.add(new ToDoItem("Task 1", false));
        list.add(new ToDoItem("Task 2", true));

        String expected = "1. " + list.getItem(1).toString() + "\n" + "2. " + list.getItem(2).toString() + "\n";

        assertEquals(expected, list.toString());
    }

}
