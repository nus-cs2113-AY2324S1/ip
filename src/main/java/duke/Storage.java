package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Storage} class manages the loading and saving of tasks to and from a data file.
 * <p>
 * This class handles interactions with a specified file, allowing the user to load tasks from it and save tasks to it.
 * The {@code load} method reads tasks from the file, and the {@code save} method writes tasks to the file, both using
 * specific string formats to represent different task types.
 * </p>
 * Tasks are represented differently based on their types:
 * <ul>
 *     <li>"T" for ToDo.</li>
 *     <li>"D" for Deadline along with end time.</li>
 *     <li>"E" for Event along with start and end times.</li>
 * </ul>
 * <p>
 * The data file is located at the path provided during the instantiation of the {@code Storage} object. If no file exists
 * at the given path when loading tasks, a new file will be created at the same location.
 * </p>
 *
 * Key Methods Include:
 * <ul>
 *     <li>{@link #load()} - Loads tasks from the data file into a list and returns it.</li>
 *     <li>{@link #save(TaskList)} - Saves tasks from the given TaskList to the data file.</li>
 * </ul>
 *
 * @author  Ashok Balaji
 * @version 1.0
 * @since   2023-09-25
 */
public class Storage {
    public static int FIRST_INDEX=0;
    public static int SECOND_INDEX=1;
    public static int THIRD_INDEX=2;
    public static int FOURTH_INDEX=3;
    public static int FIFTH_INDEX=4;
    protected File dataFile;
    protected String filePath;
    protected FileWriter fw;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.dataFile = new File(filePath);
    }

    /**
     * Loads tasks from the data file and returns a list of tasks.
     * <p>
     * If the data file does not exist, it creates a new one and prints a message indicating this.
     * If the data file does exist, it reads the file line by line, parsing each line to create the corresponding task object
     * and adds it to the task list. If a task is marked as done, it updates the task object accordingly.
     * If the line represents a deadline, it also parses the deadline end time.
     * </p>
     * <p>
     * The tasks are represented with different indicators:
     * "T" for ToDo,
     * "D" for Deadline,
     * "E" for Event.
     * </p>
     * <p>
     * It prints a message indicating the successful loading of historical data if any data is present in the file.
     * </p>
     *
     * @return {@code ArrayList<Task>} A list of tasks loaded from the data file.
     * @throws IOException If an I/O error occurs when creating a new file or reading from the existing file.
     * @throws DukeException If there is a DateTimeParseException while parsing the deadline end time, or if the data file contains corrupt data.
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> TASKS = new ArrayList<Task>();
        if (this.dataFile.createNewFile()){
            System.out.println("Data file not found @ " + this.filePath +
                    "\nCreating new data file @ " + this.filePath);
        }else{
            Scanner dataScanner = new Scanner(dataFile);
            while(dataScanner.hasNext()){
                String dataLine =  dataScanner.nextLine();
                String[] splitLineArguments = dataLine.split(" \\| ");
                switch (splitLineArguments[FIRST_INDEX]) {
                    case "T":
                        ToDo newTodo = new ToDo(splitLineArguments[THIRD_INDEX]);
                        TASKS.add(newTodo);
                        if (splitLineArguments[SECOND_INDEX].equals("1")){
                            newTodo.markAsDone();
                        }
                        break;
                    case "D":
                        try {
                            LocalDateTime deadlineEndParsed = LocalDateTime.parse(splitLineArguments[FOURTH_INDEX]);
                            Deadline newDeadline = new Deadline(splitLineArguments[THIRD_INDEX],
                                    deadlineEndParsed);
                            TASKS.add(newDeadline);
                            if (splitLineArguments[SECOND_INDEX].equals("1")){
                                newDeadline.markAsDone();
                            }
                        } catch (DateTimeParseException dtEx){
                            throw new DukeException("Invalid date format in your load file bro... ");
                        }
                        break;
                    case "E":
                        LocalDateTime eventStartParsed = LocalDateTime.parse(splitLineArguments[FOURTH_INDEX]);
                        LocalDateTime eventEndParsed = LocalDateTime.parse(splitLineArguments[FIFTH_INDEX]);
                        Event newEvent = new Event(splitLineArguments[THIRD_INDEX], eventStartParsed,
                                eventEndParsed);
                        TASKS.add(newEvent);
                        if (splitLineArguments[SECOND_INDEX].equals("1")){
                            newEvent.markAsDone();
                        }
                        break;
                    default:
                        throw new DukeException("Corrupt data bro..");
                }
            }
            System.out.println("Loaded historical data successfully.");
        }
        return TASKS;
    }

    /**
     * Saves the tasks from the given TaskList to the data file represented by the filePath.
     * This method iterates over each task in the TaskList and converts it to a formatted string representation,
     * which is then written to the data file. The tasks are saved in different formats depending on their type.
     * <ul>
     *     <li>ToDo tasks are saved in the format: "T | completionStatus | description"</li>
     *     <li>Deadline tasks are saved in the format: "D | completionStatus | description | by"</li>
     *     <li>Event tasks are saved in the format: "E | completionStatus | description | start | end"</li>
     * </ul>
     * After writing all tasks to the file, it closes the FileWriter.
     *
     * @param TASKS The TaskList containing tasks to be saved.
     * @throws IOException If an I/O error occurs writing to the file.
     * @throws DukeException If there is an error in the task saving process due to corrupt data or unexpected input.
     */
    public void save(TaskList TASKS) throws IOException, DukeException{
        FileWriter FW = new FileWriter(this.filePath);
        ArrayList<Task> AL = TASKS.getArrayList();
        for (Task taskToSave : AL){
            String taskSaveFormat = String.format("%s | %d | " , taskToSave.getTaskType(),
                    taskToSave.getCompletionStatus() ? 1:0);
            if (taskToSave.getTaskType().equals("T")){
                ToDo todoToSave = (ToDo) taskToSave;
                taskSaveFormat =  String.format("%s%s\n",taskSaveFormat,todoToSave.description);
            } else if (taskToSave.getTaskType().equals("D")) {
                Deadline deadlineToSave = (Deadline) taskToSave;
                taskSaveFormat = String.format("%s%s | %s\n",taskSaveFormat,deadlineToSave.description,
                        deadlineToSave.getBy());
            } else {
                Event eventToSave = (Event) taskToSave;
                taskSaveFormat = String.format("%s%s | %s | %s\n", taskSaveFormat, eventToSave.description,
                        eventToSave.getStart(), eventToSave.getEnd());
            }
            FW.write(taskSaveFormat);
        }
        FW.close();
    }
}
