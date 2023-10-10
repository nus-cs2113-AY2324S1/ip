import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage object that handles loading and saving of tasks into specified file
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
         this.filePath = filePath;
    }

    /**
     * This method attempts to load pre-existing tasks from the file specified by
     * the filepath. It will read the text file line by line and extract the information
     * based on a format specified by the program.
     *
     * @return A TaskList object that contains the tasks loaded from the file.
     * @throws DukeException If the file cannot be found or is corrupted.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> allTasks = new ArrayList<Task>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] lineTokens = line.split("\\|", 3);
                String taskType = lineTokens[0].trim();
                String completionStatus = lineTokens[1].trim();
                String description = lineTokens[2].trim();
                switch (taskType) {
                case "T":
                    ToDo todo = new ToDo(description);
                    allTasks.add(todo);
                    if (completionStatus.equals("1")) {
                        todo.setDone(true);
                    }
                    break;
                case "D":
                    String[] deadlineTokens = description.split("\\|");
                    description = deadlineTokens[0].trim();
                    String by = deadlineTokens[1].trim();
                    Deadline deadline = new Deadline(description, by);
                    allTasks.add(deadline);
                    if (completionStatus.equals("1")) {
                        deadline.setDone(true);
                    }
                    break;
                case "E":
                    String[] eventTokens = description.split("\\|");
                    description = eventTokens[0].trim();
                    String from = eventTokens[1].trim();
                    String to = eventTokens[2].trim();
                    Event event = new Event(description, from, to);
                    allTasks.add(event);
                    if (completionStatus.equals("1")) {
                        event.setDone(true);
                    }
                    break;
                default:
                    System.out.println("\tUnknown task encountered. Skipping.");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Failed to load file: file not found.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Failed to load file: file is corrupted.");
        }
        return allTasks;
    }

    /**
     *
     * This method save the tasks in TaskList into the file specified by filepath.
     * If the file specified by the filepath does not exist, it will create a new file as
     * specified by the filepath.
     *
     * @param taskList TaskList object that contains the tasks.
     * @throws DukeException If there is an error during the saving process due to corrupted
     *                       data or invalid input.
     */
    public void save(TaskList taskList) throws DukeException {
        ArrayList<Task> tasks = taskList.getTasks();
        try {
            createFile();
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.toSave() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("An error has occurred while saving");
        }
    }

    /**
     * This method create the file specified by the filepath.
     *
     * @throws DukeException If it fails to create the specified file.
     */
    public void createFile() throws DukeException {
        try {
            File f = new File(filePath);
            if (f.exists()) {
                return;
            }
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Cannot create file; reason: " + e.getMessage());
        }
    }
}