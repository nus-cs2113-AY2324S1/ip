package notGPT.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import notGPT.task.Deadlines;
import notGPT.task.Event;
import notGPT.task.Task;
import notGPT.task.ToDo;
import notGPT.task.TaskList;

import java.util.ArrayList;

/**
 * The Storage class manages the loading and saving of tasks to a file.
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> buffer;

    /**
     * Constructs a new Storage object with the specified file path and loads tasks from the file.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.buffer = new ArrayList<>();
        this.filePath = filePath;
        loadFromFile();
    }

    /**
     * Loads tasks from the specified file and populates the buffer.
     */
    public void loadFromFile() {
        try {
            File taskListFile = new File(filePath);
            Scanner sc = new Scanner(taskListFile);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] taskDetails = task.split("\\|");
                switch (taskDetails[0]) {
                    case "T":
                        addTodoFromFile(taskDetails);
                        break;
                    case "D":
                        addDeadlineFromFile(taskDetails);
                        break;
                    case "E":
                        addEventFromFile(taskDetails);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            try {
                File dataDirectory = new File("./data");
                if (!dataDirectory.exists()) {
                    dataDirectory.mkdir();
                }

                File taskListFile = new File(dataDirectory, "tasks.txt");
                if (!taskListFile.exists()) {
                    taskListFile.createNewFile();
                }
                System.out.println("Unable to find data file/directory! Created new storage file at ./data/tasks.txt");
            } catch (IOException ex) {
                System.out.println("Error: Unable to create or access data file/directory!");
            }
        }
    }

    private void addTodoFromFile(String[] taskDetails) {
        String taskName = taskDetails[2];
        ToDo currTodo = new ToDo(taskName);
        if (taskDetails[1].equals("1")) {
            currTodo.markAsDone();
        }
        buffer.add(currTodo);
    }

    private void addDeadlineFromFile(String[] taskDetails) {
        String taskName = taskDetails[2];
        String deadline = taskDetails[3];
        Deadlines currDeadline = new Deadlines(taskName, deadline);
        if (taskDetails[1].equals("1")) {
            currDeadline.markAsDone();
        }
        buffer.add(currDeadline);
    }

    private void addEventFromFile(String[] taskDetails) {
        String taskName = taskDetails[2];
        String startTime = taskDetails[3];
        String endTime = taskDetails[4];
        Event currEvent = new Event(taskName, startTime, endTime);
        if (taskDetails[1].equals("1")) {
            currEvent.markAsDone();
        }
        buffer.add(currEvent);
    }

    /**
     * Saves tasks from the taskList to the file specified in the filePath.
     *
     * @param taskList The TaskList containing tasks to be saved.
     */
    public void saveTasks(TaskList taskList) {
        try {
            File taskListFile = new File(filePath);

            FileWriter fw = new FileWriter(taskListFile);
            for (int i = 1; i <= taskList.getTaskCount(); i++) {
                fw.write(taskList.getTaskByNumber(i).toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to create or access data file/directory!\n"
                    + e.getMessage());
        }
    }

    /**
     * Retrieves the buffer containing loaded tasks.
     *
     * @return The ArrayList of tasks loaded from the file.
     */
    public ArrayList<Task> getBuffer() {
        return buffer;
    }
}

