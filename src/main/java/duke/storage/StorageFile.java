package duke.storage;

import duke.command.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles Duke's storage capabilities. A <code>StorageFile</code> object handles the
 * loading and saving aspects of new tasks.
 */
public class StorageFile {
    String filePath;
    public StorageFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads all tasks from the .txt file
     * when the program is first ran.
     *
     * @return List of Task objects representing saved tasks.
     * @throws DukeException If there is an issue loading tasks.
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        File f = new File(filePath);

        try {
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parsed = line.split("\\|");
                String taskType = parsed[0];
                String description = parsed[2];

                switch (taskType) {
                case "T":
                    tasks.add(new Todo(description));
                    break;
                case "D":
                    String by = parsed[3];
                    tasks.add(new Deadline(description, by));
                    break;
                case "E":
                    String from = parsed[3];
                    String to = parsed[4];
                    tasks.add(new Event(description, from, to));
                    break;
                default:
                    throw new DukeException("Unknown task type detected");
                }

                if (parsed[1].equals("true")) {
                    tasks.get(tasks.size() - 1).setStatus(true);
                }
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves all tasks to the .txt file after
     * a command that modifies the TaskList
     *
     * @param tasks to be written to the file
     * @throws DukeException If there is an error writing.
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        File f = new File(filePath);

        try {
            FileWriter fw = new FileWriter(f);
            fw.write(tasks.getSerializedTasks());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
