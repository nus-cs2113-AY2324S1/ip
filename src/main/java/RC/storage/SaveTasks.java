package RC.storage;

import RC.RCException;
import RC.TaskList;
import RC.task.Task;

import java.io.FileWriter;
import java.io.IOException;

public abstract class SaveTasks {
    private static final String FILE_PATH = "data/tasks.txt";
    private static final String MESSAGE_WRITE_ERROR = "\tOOPS!!! Error writing to file.";

    public static void save(TaskList taskList) throws RCException {
        try {
            FileWriter fr = new FileWriter(FILE_PATH);
            for (Task task : taskList.tasks) {
                String text = task.formatString() + "\n";
                fr.write(text);
            }
            fr.close();
        } catch (IOException e) {
            throw new RCException(MESSAGE_WRITE_ERROR);
        }
    }
}
