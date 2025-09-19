package megatrongriffin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for TaskStorage functionality.
 * Tests the saving and loading of tasks to and from persistent storage.
 */

public class TaskStorageTest {

    private Path tempFile;
    private TaskStorage storage;

    /**
     * Sets up test environment before each test method.
     * Creates a temporary file and TaskStorage instance for testing.
     *
     * @throws Exception if there is an error creating the temporary file
     */
    @BeforeEach
    void setUp() throws Exception {
        tempFile = Files.createTempFile("tasks", ".txt");
        tempFile.toFile().deleteOnExit();

        this.storage = new TaskStorage(tempFile);
    }

    /**
     * Tests the save and load functionality of TaskStorage.
     * Verifies that tasks can be saved to and loaded from persistent storage correctly.
     *
     * @throws Exception if there is an error during file operations
     */
    @Test
    void testSaveAndLoadTest() throws Exception {
        ToDoList list = new ToDoList();
        ToDoItem item1 = new ToDoItem("Read book", false);
        ToDoItem item2 = new ToDoItem("Write report", true);
        list.add(item1);
        list.add(item2);

        storage.save(list);

        ToDoList load = storage.load();

        assertEquals(2, load.toSave().size());
        assertEquals("Read book", load.getItem(1).getTask());
        assertEquals(false, load.getItem(1).isDone());
        assertEquals("Write report", load.getItem(2).getTask());
        assertEquals(true, load.getItem(2).isDone());
    }
}
