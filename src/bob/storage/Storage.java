package bob.storage;

import bob.BobException;
import bob.deadline.Deadline;
import bob.event.Event;
import bob.task.Task;
import bob.todo.Todo;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

/**
 * Loads and stores task list from and to a given text file.
 */
public class Storage {
    protected String fileName;

    /**
     * Constructs new Storage from {@param fileName}. Tasks will be loaded and saved into
     * this file.
     *
     * @param fileName path to file used to store tasks.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    protected Task createTaskFromFile(String line) {
        String[] details = line.split("[|]");
        for (int i = 0; i < details.length; i++) {
            details[i] = details[i].trim();
        }

        Task task;
        char todoType = details[0].charAt(0);
        boolean isDone = Boolean.parseBoolean(details[1]);

        switch(todoType) {
            case 'T':
                task = new Todo(details[2]);
                break;
            case 'E':
                task = new Event(details[2], details[3], details[4]);
                break;
            case 'D':
                task = new Deadline(details[2], LocalDate.parse(details[3]));
                break;
            default:
                task = new Task("");
        }

        task.setIsDone(isDone);

        return task;
    }

    /**
     * Loads previously saved tasks from file used for storage.
     *
     * @return ArrayList of tasks from save file.
     * @throws BobException if unable to read from file.
     */
    public ArrayList<Task> load() throws BobException {
        Scanner s;

        File taskFile = new File(fileName);
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            s = new Scanner(taskFile);
        } catch (IOException e) {
            throw new BobException("");
        }

        String line;

        while (s.hasNext()) {
            line = s.nextLine();

            Task task = createTaskFromFile(line);
            taskList.add(task);
        }

        return taskList;
    }

    /**
     * Writes tasks into save file.
     *
     * @param tasks prepared list of tasks in the form of a String to be written into
     *              the save file.
     * @throws IOException thrown if unable to write to file.
     */
    public void writeTasksToFile(String tasks) throws IOException {
        FileWriter fw = new FileWriter(fileName);

        fw.write(tasks);
        fw.close();
    }

}
