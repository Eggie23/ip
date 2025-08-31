package megatrongriffin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskStorageTest {

    private Path tempFile;
    private TaskStorage storage;

    @BeforeEach
    void setUp() throws Exception {
        tempFile = Files.createTempFile("tasks", ".txt");
        tempFile.toFile().deleteOnExit();

        this.storage = new TaskStorage(tempFile);
    }

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
