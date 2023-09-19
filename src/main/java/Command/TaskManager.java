package Command;

import Tasks.Task;
import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class TaskManager {
    private static final String FILE_PATH = "./src/main/java/Data/taskList.txt";
    private static final int TODO_COMMAND_LENGTH = 5;
    private static final int EVENT_COMMAND_LENGTH = 6;

    private static final int DEADLINE_COMMAND_LENGTH = 9;
    private static final int BY_KEYWORD_LENGTH = 4;
    private final ArrayList<Task> taskList = new ArrayList<>();

    public void listTasks() {
        System.out.println("Here's your tasks!");
        for (int i = 0; i < taskList.size(); i++) {
            int indexNum = i + 1;
            System.out.println(indexNum + "." + taskList.get(i));
        }
    }

    public void markTaskAsDone(int index) throws JarvisException{
        try {
            if (isValidIndex(index)) {
                System.out.println("Nice! I've marked this task as done:");
                taskList.get(index).markAsDone();
                saveTasksToFile(); // Save tasks to file after a new task is added
                System.out.println("    " + taskList.get(index));
            }
            else {
                throw JarvisException.invalidTaskNumber(index);
            }
        } catch (JarvisException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void unmarkTaskAsDone(int index) throws JarvisException{
        try {
            if (isValidIndex(index)) {
                System.out.println("I've unmarked this task:");
                taskList.get(index).markAsUndone();
                saveTasksToFile(); // Save tasks to file after a new task is added
                System.out.println("    " + taskList.get(index));
            }
            else {
                throw JarvisException.invalidTaskNumber(index);
            }
        } catch (JarvisException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int extractIndex(String userInput, int startIndex) {
        if (startIndex < userInput.length()) {
            return Integer.parseInt(userInput.substring(startIndex).trim()) - 1; // Convert to 0-indexed
        }
        else {
            return -1;
        }
    }

    public void addTodo(String description) throws IOException {
        Todo todo = new Todo(description);
        taskList.add(todo);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + todo);
        displayTaskCount();
        saveTasksToFile(); // Save tasks to file after a new task is added
    }

    public void loadTodo(String description) throws IOException {
        Todo todo = new Todo(description);
        taskList.add(todo);
    }

    static String parseToDoDescription(String userInput) throws JarvisException {
        if (userInput.length() <= TODO_COMMAND_LENGTH) {
            throw JarvisException.invalidTodoFormat();
        }
        String description = userInput.substring(TODO_COMMAND_LENGTH).trim();
        if(description.isEmpty()) {
            throw JarvisException.invalidTodoFormat();
        }
        return description;
    }

    public void addDeadline(String description, String time) throws IOException {
        Deadline deadline = new Deadline(description, time);
        taskList.add(deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + deadline);
        displayTaskCount();
        saveTasksToFile(); // Save tasks to file after a new task is added
    }
    public void loadDeadline(String description, String time) throws IOException {
        Deadline deadline = new Deadline(description, time);
        taskList.add(deadline);
    }

    static List<String> parseDeadlineDescription(String userInput) throws JarvisException {
        int lastIndex = userInput.lastIndexOf("/by");
        if (lastIndex == -1) {
            throw JarvisException.invalidDeadlineFormat();
        }

        if (userInput.length() <= DEADLINE_COMMAND_LENGTH || lastIndex + BY_KEYWORD_LENGTH >= userInput.length()) {
            throw JarvisException.invalidDeadlineFormat();
        }
        String description = userInput.substring(DEADLINE_COMMAND_LENGTH, lastIndex).trim();
        String time = userInput.substring(lastIndex + BY_KEYWORD_LENGTH).trim();

        if(description.isEmpty() || time.isEmpty()) {
            throw JarvisException.invalidDeadlineFormat();
        }

        List<String> descriptionAndTime = new ArrayList<>();
        descriptionAndTime.add(description);
        descriptionAndTime.add(time);

        return descriptionAndTime;
    }

    public void addEvent(String description, String startTime, String endTime) throws IOException {
        String timeRange = startTime + " to " + endTime;
        Event event = new Event(description, timeRange);
        taskList.add(event);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + event);
        displayTaskCount();
        saveTasksToFile(); // Save tasks to file after a new task is added
    }

    public void loadEvent(String description, String startTime, String endTime) throws IOException {
        String timeRange = startTime + " to " + endTime;
        Event event = new Event(description, timeRange);
        taskList.add(event);
    }

    static List<String> parseEventDescription(String userInput) throws JarvisException {
        int lastIndexTo = userInput.lastIndexOf("/to");
        int lastIndexFrom = userInput.lastIndexOf("/from");
        if (lastIndexFrom == -1 && lastIndexTo == -1) {
            throw JarvisException.invalidEventFormat();
        }

        String[] parts = userInput.substring(EVENT_COMMAND_LENGTH).trim().split("/from|/to", 3);
        String description = parts[0].trim();
        String startTime = parts[1].trim();
        String endTime = parts[2].trim();

        List<String> descriptionAndTime = new ArrayList<>();
        descriptionAndTime.add(description);
        descriptionAndTime.add(startTime);
        descriptionAndTime.add(endTime);
        System.out.println((descriptionAndTime));

        if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw JarvisException.invalidEventFormat();
        }
        return descriptionAndTime;
    }

    public void deleteTask(int index) throws JarvisException {
        try {
            if (isValidIndex(index)) {
                Task removedTask = taskList.remove(index);
                System.out.println("Noted. I've removed this task:");
                System.out.println("    " + removedTask);
                displayTaskCount();
            } else {
                throw JarvisException.invalidTaskNumber(index);
            }
        } catch (JarvisException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void saveTasksToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        for (Task task : taskList) {
            writer.write(toFileFormat(task));
            writer.newLine();
        }
        writer.close();
    }

    public void loadTasksFromFile() throws IOException, JarvisException {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            System.out.println("Loading File");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                switch (parts[0]) {
                case "T":
                    loadTodo(parts[2]);
                    break;
                case "D":
                    loadDeadline(parts[2], parts[3]);
                    break;
                case "E":
                    String[] timeParts = parts[3].split(" to ");
                    loadEvent(parts[2], timeParts[0], timeParts[1]);
                    break;
                }
                // Loads the progress
                if (parts[1].equals("1")) {
                    taskList.get(taskList.size() - 1).markAsDone();
                }
            }
            System.out.println("File loaded");
            listTasks();
            reader.close();
        }
        else{
            // If file not found, create the necessary directories and then the file
            if (file.getParentFile().mkdirs()) {
                System.out.println("Directories created successfully.");
            }
            else {
                System.out.println("Failed to create directories.");
            }

            if (file.createNewFile()) {
                System.out.println("New file created at: " + FILE_PATH);
            }
            else {
                System.out.println("Failed to create new file.");
            }
        }
    }

    public String toFileFormat(Task task) {
        String formattedString;
        switch(task.getTaskType()) {
        case "T": // Todo task
            formattedString = String.format("T|%s|%s",
                    task.getStatusIcon().equals("X") ? "1" : "0",
                    task.getDescription());
            break;
        case "D": // Deadline task
            Deadline deadline = (Deadline) task;
            formattedString = String.format("D|%s|%s|%s",
                    task.getStatusIcon().equals("X") ? "1" : "0",
                    task.getDescription(),
                    deadline.getTime());
            break;
        case "E": // Event task
            Event event = (Event) task;
            formattedString = String.format("E|%s|%s|%s",
                    task.getStatusIcon().equals("X") ? "1" : "0",
                    task.getDescription(),
                    event.getTime());
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + task.getTaskType());
        }
        return formattedString;
    }

>>>>>>> branch-Level-7
    private void displayTaskCount() {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < taskList.size();
    }
}
