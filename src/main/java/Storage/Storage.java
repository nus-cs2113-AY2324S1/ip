package Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Exceptions.DukeException;
import Task.Deadline;
import Task.Event;
import Task.TaskList;
import Task.Todo;

import static Task.TaskList.list;

/**
 * class containing all relevant methods to saving and loading information from file
 */
public class Storage {

    /**
     * Method to save all list items to file recursively
     */
    public static void saveListToFile()  {
        try (FileWriter fileWriter = new FileWriter("data/botbot.txt")){
            for (int i = 0; i<TaskList.size(); i++) {
                fileWriter.write(list.get(i).toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * extracts todo tasks out of the saved string and adds it to tasklist as task type
     * @param savedTask String of task saved in file
     */
    public static void extractTodo(String savedTask){
        Todo todoTask = new Todo(savedTask.substring(8));
        list.add(todoTask);
    }

    /**
     * extracts deadline tasks and its deadline out of the saved string and adds it to tasklist as task type
     * @param savedTask String of task saved in file
     * @throws DukeException when format of saved task is wrong
     */
        public static void extractDeadline(String savedTask) throws DukeException {
        String[] parts = savedTask.split(" \\(by: " );
        String task = parts[0].substring(8);
        String deadline = parts[1].substring(0, parts[1].length() -1);
        if (task.isEmpty() || deadline.isEmpty()) {
            throw new DukeException("Loaded task or deadline is empty... Please check your saved list again~");
        } else {
            Deadline deadlineTask = new Deadline(task, deadline);
            list.add(deadlineTask);
        }
    }

    /**
     * extracts event tasks and its time period out of the saved string and adds it to tasklist as task type
     * @param savedTask String of task saved in file
     * @throws DukeException when format of saved task is wrong
     */    public static void extractEvent(String savedTask) throws DukeException {
        String[] parts = savedTask.split(" \\(");
        String task = parts[0].substring(8);
        String[] timeSplit = parts[1].split(" to: ");
        String from = timeSplit[0].substring(6);
        String to = timeSplit[1].substring(0, timeSplit[1].length() - 1);
        if (task.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DukeException("Loaded task or time period is empty... Please check your saved list again~");
        } else {
            Event eventTask = new Event(task, from, to);
            list.add(eventTask);
        }
    }

    /**
     * extracts tasks out of the saved file and adds it to tasklist
     * @throws IOException when file does not exist and new file cannot be created
     * @throws DukeException when format of saved file is wrong and hence cannot be loaded
     */    public static void loadListFromFile() throws IOException, DukeException {
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
                if (savedTask.charAt(1) == ('T')) {
                    extractTodo(savedTask);
                } else if (savedTask.charAt(1) == ('D')) {
                    extractDeadline(savedTask);
                } else if (savedTask.charAt(1) == ('E')) {
                    extractEvent(savedTask);
                }
            }
        }
    }
}
