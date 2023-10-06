package BotBuddy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage file that stores application data.
 */
public class Storage {
    String filePath;
    String directoryName;

    /**
     * Checks if the storage file exists, and creates one if it does not.
     *
     * @param filePath the relative file path of the storage file.
     * @param directoryName the name of the directory in which the storage file is located.
     * @param ui Ui object to print response to user.
     * @throws IOException If IO errors occur.
     */
    public Storage(String filePath, String directoryName, Ui ui) throws IOException {
        this.filePath = filePath;
        this.directoryName = directoryName;
        File directory = new File(directoryName);
        File taskFile = new File(filePath);

        if (!taskFile.exists()) {
            ui.printCreateTaskFileMsg();
            if (!directory.exists()) {
                directory.mkdir();
            }
            taskFile.createNewFile();
        }
    }

    /**
     * Loads tasks from the storage file into the task list.
     *
     * @return Task list.
     * @throws FileNotFoundException If storage file does not exist.
     * @throws BotBuddyException If storage file is corrupted.
     */
    public ArrayList<Task> load() throws FileNotFoundException, BotBuddyException {
        ArrayList<Task> taskArrayList = new ArrayList<>();

        File taskFile = new File(filePath);
        Scanner taskScanner = new Scanner(taskFile);

        while (taskScanner.hasNext()) {
            String currentTask = taskScanner.nextLine();
            if (currentTask.startsWith("[T]")) {
                currentTask = currentTask.substring(3);
                boolean isDone = false;
                if (currentTask.startsWith("[X]")) {
                    isDone = true;
                }
                currentTask = currentTask.substring(3);
                addTodoFromFile(currentTask, taskArrayList);
                if (isDone) {
                    int noOfTasks = Task.getNoOfTasks();
                    markTaskFromFile(Integer.toString(noOfTasks), taskArrayList);
                }
            } else if (currentTask.startsWith("[E]")) {
                currentTask = currentTask.substring(3);
                boolean isDone = false;
                if (currentTask.startsWith("[X]")) {
                    isDone = true;
                }
                currentTask = currentTask.substring(3);
                addEventFromFile(currentTask, taskArrayList);
                if (isDone) {
                    int noOfTasks = Task.getNoOfTasks();
                    markTaskFromFile(Integer.toString(noOfTasks), taskArrayList);
                }
            } else if (currentTask.startsWith("[D]")) {
                currentTask = currentTask.substring(3);
                boolean isDone = false;
                if (currentTask.startsWith("[X]")) {
                    isDone = true;
                }
                currentTask = currentTask.substring(3);
                addDeadlineFromFile(currentTask, taskArrayList);
                if (isDone) {
                    int noOfTasks = Task.getNoOfTasks();
                    markTaskFromFile(Integer.toString(noOfTasks), taskArrayList);
                }
            } else {
                // this should not run
                throw new BotBuddyException("File corrupted!");
            }
        }

        return taskArrayList;
    }

    /**
     * Adds a todo from the storage file into the task list.
     *
     * @param parameters Details of the todo.
     * @param tasks Task list.
     */
    public static void addTodoFromFile(String parameters, ArrayList<Task> tasks) {
        tasks.add(new Todo(parameters));
    }

    /**
     * Adds an event from the storage file into the task list.
     *
     * @param parameters Details of the event.
     * @param tasks Task list.
     */
    public static void addEventFromFile(String parameters, ArrayList<Task> tasks) {
        String[] eventDetails = parameters.split("/from");
        String eventName = eventDetails[0].trim();
        eventDetails = eventDetails[1].split("/to");
        String eventFrom = eventDetails[0].trim();
        String eventTo = eventDetails[1].trim();
        tasks.add(new Event(eventName, eventFrom, eventTo));
    }

    /**
     * Adds a deadline from the storage file into the task list.
     *
     * @param parameters Details of the deadline.
     * @param tasks Task list.
     */
    public static void addDeadlineFromFile(String parameters, ArrayList<Task> tasks) {
        String[] deadlineDetails = parameters.split("/by");
        String deadlineName = deadlineDetails[0].trim();
        String deadlineBy = deadlineDetails[1].trim();
        tasks.add(new Deadline(deadlineName, deadlineBy));
    }

    /**
     * Marks a task in the task list as done if it is marked as done in the storage file.
     *
     * @param taskToMark Task number to mark as done.
     * @param tasks Task list.
     */
    public static void markTaskFromFile(String taskToMark, ArrayList<Task> tasks) {
        int taskIndex = Integer.parseInt(taskToMark) - 1;
        tasks.get(taskIndex).markAsDone();
    }

    /**
     * Stores data from the task list into the storage file.
     *
     * @param taskArrayList Task list.
     * @throws IOException If there are errors writing to the storage file.
     */
    public void store(ArrayList<Task> taskArrayList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        StringBuilder taskData = new StringBuilder();
        int noOfTasks = Task.getNoOfTasks();

        for (int i = 0; i < noOfTasks; i++) {
            if (taskArrayList.get(i).getClass() == Todo.class) {
                Todo currentTodo = (Todo) taskArrayList.get(i);
                taskData.append("[T]").append(currentTodo.getStatusIcon());
                taskData.append(currentTodo.getDescription());
                taskData.append(System.lineSeparator());
            } else if (taskArrayList.get(i).getClass() == Event.class) {
                Event currentEvent = (Event) taskArrayList.get(i);
                taskData.append("[E]").append(currentEvent.getStatusIcon());
                taskData.append(currentEvent.getDescription());
                taskData.append(" /from ").append(currentEvent.getFrom());
                taskData.append(" /to ").append(currentEvent.getTo());
                taskData.append(System.lineSeparator());
            } else if (taskArrayList.get(i).getClass() == Deadline.class) {
                Deadline currentDeadline = (Deadline) taskArrayList.get(i);
                taskData.append("[D]").append(currentDeadline.getStatusIcon());
                taskData.append(currentDeadline.getDescription());
                taskData.append(" /by ").append(currentDeadline.getBy());
                taskData.append(System.lineSeparator());
            } else {
                // This should not run
                System.out.println("Fatal error!");
            }
        }

        fileWriter.write(taskData.toString());
        fileWriter.close();
    }

}
