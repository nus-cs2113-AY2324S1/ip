package duke.tasksStorage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import java.io.FileWriter;
import java.io.IOException;
import duke.inputProcess.TaskList;

/**
 * SaveToFile is a class responsible for saving tasks from the TaskList into a text file.
 * It supports saving tasks of different types (Todo, Deadline, Event) along with their
 * respective statuses to the specified file path.
 * <p>
 * SaveToFile is used to persist tasks to a file when the user added new tasks to the Hilary robot.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-09-30
 */

public class SaveToFile {
    private String path;

    /**
     * Constructs a `SaveToFile` object with the given file path.
     *
     * @param path The file path to which task data will be saved.
     */
    public SaveToFile(String path) {
        this.path = path;
    }

    /**
     * Saves task data from the provided task list to the text file.
     *
     * @param list The task list from which task data will be saved.
     * @throws IOException If an I/O error occurs during the file writing process.
     */
    public void saveToTextFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (int i = 0; i < list.getSize(); ++i) {
            String textToAdd = "";
            String taskNameToSave = list.getByIndex(i).getDescription();
            String taskTimeToSave = list.getByIndex(i).getEventTime();
            String isDoneToSave = list.getByIndex(i).getStatus() ? "1" : "0";
            if (list.getByIndex(i).getClass() == Todo.class) {
                textToAdd = "T" + " | " + isDoneToSave + " | " + taskNameToSave + "\n";
            } else if (list.getByIndex(i).getClass() == Deadline.class) {
                textToAdd = "D" + " | " + isDoneToSave + " | " + taskNameToSave + " | " + taskTimeToSave + "\n";
            } else if (list.getByIndex(i).getClass() == Event.class) {
                textToAdd = "E" + " | " + isDoneToSave + " | " + taskNameToSave + " | " + taskTimeToSave + "\n";
            }
            fw.write(textToAdd);
        }
        fw.close();
    }
}
