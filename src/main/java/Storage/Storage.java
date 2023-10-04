package Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Exceptions.DukeException;
import Task.Deadline;
import Task.Event;
import Task.TaskList;
import static Task.TaskList.list;


public class Storage {

    //method to save list to file
    public static void saveListToFile() throws IOException {
        try (FileWriter fileWriter = new FileWriter("data/botbot.txt")){
            for (int i = 0; i<TaskList.size(); i++) {
                fileWriter.write(list.get(i).toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    //method to extract todo task
    public static void extractTodo(String savedTask){
        TaskList.createTodoTasks(savedTask.substring(8));
    }

    //method to extract deadline task
    public static void extractDeadline(String savedTask) throws DukeException {
        String[] parts = savedTask.split(" \\(by: " );
        String task = parts[0].substring(8);
        String deadline = parts[1].substring(0, parts[1].length() - 1);
        if (task.isEmpty() || deadline.isEmpty()) {
            throw new DukeException("Loaded task or deadline is empty... Please check your saved list again~");
        } else {
            //instantiate new deadline object
            Deadline deadlineTask = new Deadline(task, deadline);
            //add to array
            list.add(deadlineTask);
        }
    }

    //method to extract event task
    public static void extractEvent(String savedTask) throws DukeException {
        String[] parts = savedTask.split(" \\(");
        String task = parts[0].substring(8);
        String[] timeSplit = parts[1].split(" to: ");
        String from = timeSplit[0].substring(6);
        String to = timeSplit[1].substring(0, timeSplit[1].length() - 1);
        if (task.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DukeException("Loaded task or time period is empty... Please check your saved list again~");
        } else {
            //instantiate new event object
            Event eventTask = new Event(task, from, to);
            //add to array
            list.add(eventTask);
        }
    }

    // method to load list from file
    public static void loadListFromFile() throws IOException, DukeException {
        File file = new File("data/botbot.txt");
        if (!file.exists()){
            try {
                boolean createdDirectory = file.mkdirs();
                boolean createdFile = file.createNewFile();
            } catch (IOException e){
                System.out.println("Something went wrong: " + e.getMessage());
            }
        } else {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String savedTask = fileScanner.nextLine();
                if (savedTask.contains("T")) {
                    extractTodo(savedTask);
                } else if (savedTask.contains("D")) {
                    extractDeadline(savedTask);
                } else if (savedTask.contains("E")) {
                    extractEvent(savedTask);
                }
            }
        }
    }
}
