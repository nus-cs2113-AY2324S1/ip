package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Itay {
    static ArrayList<Task> tasks = new ArrayList<>(100);
    static int numTasks = 0;
    static String DIVIDER = "------------------------------------------------------------";
    
    public static void respond(String input) throws DukeException {
        System.out.println(DIVIDER);
        String splitInput[] = input.split(" ");
        String indicator = splitInput[0];

        switch(indicator) {
            case("list"):
                printList(false);
                break;
            case("mark"):
                handleMark(splitInput);
                break;
            case("unmark"):
                handleUnmark(splitInput);
                break;
            case("delete"):
                handleDelete(splitInput);
                break;
            case("todo"):
                handleTodo(input, splitInput);
                break;
            case("deadline"):
                handleDeadline(input);
                break;
            case("event"):
                handleEvent(input);
                break;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void printList(boolean isIntroduction) {
        if(numTasks == 0) {
           System.out.println("You have no tasks in your list."); 
        }
        else {
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < numTasks ; i++) {
                System.out.println(tasks.get(i).toString());
            }
        }
        if(!isIntroduction) System.out.println(DIVIDER);
    }

    public static void handleMark(String[] splitInput) throws DukeException {
        int taskIdx = getTaskIndex(splitInput);
        tasks.get(taskIdx).setStatus(true);
        saveTasksToFile(tasks);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIdx).toString());
        System.out.println(DIVIDER);
    }

    public static void handleUnmark(String[] splitInput) throws DukeException {
        int taskIdx = getTaskIndex(splitInput);
        tasks.get(taskIdx).setStatus(false);
        saveTasksToFile(tasks);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIdx).toString());
        System.out.println(DIVIDER);
    }

    public static int getTaskIndex(String[] splitInput) throws DukeException {
        int taskIdx;
        String errorMessage = "OOPS!!! Please enter a valid task number.";

        try {
            taskIdx = Integer.parseInt(splitInput[1]) - 1;
            if (taskIdx < 0 || taskIdx >= numTasks) {
                throw new DukeException(errorMessage);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException inputEx) {
            throw new DukeException(errorMessage + inputEx.getMessage());
        }
        return taskIdx;
    }

    public static void handleDelete(String[] splitInput) throws DukeException {
        int taskIdx = getTaskIndex(splitInput);
        Task toDelete = tasks.get(taskIdx);
        tasks.remove(taskIdx);
        numTasks--;
        saveTasksToFile(tasks);

        System.out.println("Got it. I've removed this task:");
        System.out.println(toDelete.toString());
        System.out.println("Now you have " + numTasks + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void handleTodo(String input, String[] splitInput) throws DukeException {
        if(splitInput.length == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(input.indexOf(' ') + 1);
        Task task = new Task(description, 'T');
        addTask(task);
    }

    public static void handleDeadline(String input) throws DukeException {
        try {
            int firstSlashIdx = input.indexOf('/');

            String description = input.substring(input.indexOf(' ') + 1, firstSlashIdx - 1);
            Task task = new Task(description, 'D');

            String temp = input.substring(firstSlashIdx + 1);
            String deadline = temp.substring(temp.indexOf(' '));
            deadline = deadline.trim();
            task.setDeadlineTime(deadline);
            addTask(task);
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException inputEx) {
            throw new DukeException("OOPS!!! Description of deadline command must be of form: deadline ___ /by ___");
        }
    }

    public static void handleEvent(String input) throws DukeException {
        try {
            int firstSlashIdx = input.indexOf('/');
            int secondSlashIdx = input.indexOf('/', firstSlashIdx + 1);

            String description = input.substring(input.indexOf(' ') + 1, firstSlashIdx - 1);
            Task task = new Task(description, 'E');

            String temp = input.substring(firstSlashIdx);
            firstSlashIdx = temp.indexOf('/');
            secondSlashIdx = temp.indexOf('/', firstSlashIdx + 1);
            String startTime = temp.substring(temp.indexOf(' ') + 1, secondSlashIdx - 1);
            temp = temp.substring(secondSlashIdx);
            String endTime = temp.substring(temp.indexOf(' '));
            endTime = endTime.trim();
            
            task.setEventTime(startTime, endTime);
            addTask(task);
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException inputEx) {
            throw new DukeException("OOPS!!! Description of event command must be of form: event ___ /from ___ /to ___");
        } 
    }
    
    public static void addTask(Task task) throws DukeException {
        tasks.add(task);
        numTasks++;
        saveTasksToFile(tasks);

        System.out.println("Got it. I've added this task:");   
        System.out.println(task.toString());   
        System.out.println("Now you have " + numTasks + " tasks in the list.");
        System.out.println(DIVIDER);   
    }

    public static void saveTasksToFile(ArrayList<Task> tasks) throws DukeException {
        try {
            FileOutputStream fos = new FileOutputStream("./data/duke.txt");
            PrintWriter writer = new PrintWriter(fos);

            for (Task task : tasks) {
                String taskString = task.serializeToString(); 
                writer.println(taskString);
            }

            writer.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Error saving tasks to file: " + e.getMessage(), e);
        }
    }

    public static ArrayList<Task> loadTasksFromFile() throws DukeException {
        try {
            File folder = new File("./data");
            if (!folder.exists()) {
                folder.mkdirs(); // Create the "data" folder if it doesn't exist
            }

            File file = new File("./data/duke.txt");
            if (!file.exists()) {
                file.createNewFile(); // Create the file "duke.txt" if it doesn't exist
            }

            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.deserializeFromString(line); // Implement deserialization
                if (task != null) {
                    tasks.add(task);
                }
            }

            reader.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Error loading tasks from file: " + e.getMessage(), e);
        }

        // Update static variable numTasks 
        numTasks = tasks.size();
        return tasks;
    }

    public static void main(String[] args) throws DukeException {
        // Load tasks from the data file at startup
        loadTasksFromFile();
        
        System.out.println("Hello! I'm Itay!");
        printList(true);
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);

        try (Scanner in = new Scanner(System.in)) {
            String input = in.nextLine();
            input = input.trim();

            while(! input.equals("bye")) {
                try {
                    respond(input);
                } catch(DukeException dukeEx) {
                    System.out.println(dukeEx.toString());
                }
                // Get the next input
                input = in.nextLine();
                input = input.trim();
            }
        }

        // Save tasks to the data file before exiting
        saveTasksToFile(tasks);

        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
}