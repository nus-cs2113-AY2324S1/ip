package doli.files;

import doli.exceptions.DoliExceptions;
import doli.tasks.TaskList;
import doli.tasks.Event;
import doli.tasks.Deadline;
import doli.tasks.ToDo;
import doli.tasks.Task;

import java.util.ArrayList;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <h3>Storage class</h3>
 * The Storage class handles the initialisation, loading
 * and modification/updating of the .txt file containing
 * the agenda which is stored using a relative path, and thus
 * accessible using any OS.
 *
 * @author pappalardodaniel
 * @version 1.0
 * @since 2023-11-03
 */
public class Storage {
    protected final String FILE_PATH;
    private static final String TODO_SYMBOL = "T";
    private static final String DEADLINE_SYMBOL = "D";
    private static final String EVENT_SYMBOL = "E";
    private static final String INFO_SEPARATOR = "|";

    /**
     * Constructs an object of type Storage to manage the storage of a file
     * and its contents
     * @param filePath of type String specifying the relative file path
     */
    public Storage(String filePath){
        FILE_PATH = filePath;
    }

    /**
     * Loads the previously created file and returns its contents as an agenda if such a file exists.
     * Otherwise, it creates a new file from scratch and returns an empty ArrayList.
     * @return an ArrayList containing the agenda entries
     * @throws IOException in case there were problems handling the file
     * @throws DoliExceptions in case there are problems loading the existing file
     */
    public ArrayList<Task> initializeFile() throws IOException, DoliExceptions {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
                return new ArrayList<>();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                return loadExistingFile(file);
            } catch (DoliExceptions e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Loads an existing agenda file by scanning each line contained in the .txt file
     * and adding the specific tasks within the file to a new ArrayList.
     * @param file of type File to analyse and extract content from
     * @return an ArrayList of Tasks that Doli will use to create a new object of type TaskList
     * @throws IOException in case the file could not be handled properly
     * @throws DoliExceptions in case the task in the file could not be recognized or deciphered
     */
    public static ArrayList<Task> loadExistingFile(File file)
            throws IOException, DoliExceptions {
        Scanner fs = new Scanner(file);
        ArrayList<Task> agenda = new ArrayList<>();
        while (fs.hasNext()) {
            String line = fs.nextLine();
            String[] task = line.split("\\|");
            String type = task[0];
            boolean isDone = task[1].equals("X");
            String description = task[2];

            switch (type) {
            case TODO_SYMBOL:
                addTodo(description, isDone, agenda);
                break;
            case DEADLINE_SYMBOL:
                String deadline = task[3];
                addDeadline(description, isDone, deadline, agenda);
                break;
            case EVENT_SYMBOL:
                String startDate = task[3];
                String endDate = task[4];
                addEvent(description, isDone, startDate, endDate, agenda);
                break;
            default:
                throw new DoliExceptions("Task not recognized.");
            }
        }
        return agenda;
    }

    /**
     * Updates the file in order to account for new commands and changes performed by the user.
     * @param agenda of type TaskList refers to the agenda containing the recent changes to transcribe onto the file
     * @throws DoliExceptions in case the agenda entry could not be recognized and transcribed
     */
    public void modifyFile(TaskList agenda) throws DoliExceptions {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : agenda) {
                if (task instanceof ToDo) {
                    writeTodo(fw, (ToDo) task);
                } else if (task instanceof Deadline) {
                    writeDeadline(fw, (Deadline) task);
                } else if (task instanceof Event) {
                    writeEvent(fw, (Event) task);
                } else {
                    throw new DoliExceptions("Task type unrecognized.");
                }
            }
            fw.close();
        } catch (DoliExceptions | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a task of type todo to the agenda.
     * @param description of type String specifying the description of the todo task
     * @param isDone of type boolean specifying whether the task is done or not
     * @param agenda an ArrayList of type Task where the todo task will be added to
     */
    private static void addTodo(String description, boolean isDone, ArrayList<Task> agenda) {
        ToDo todo = new ToDo(description);
        todo.setDone(isDone);
        agenda.add(todo);
    }
    /**
     * Adds a task of type deadline to the agenda.
     * @param description of type String specifying the description of the deadline task
     * @param isDone of type boolean specifying whether the task is done or not
     * @param date of type String referring to the deadline of the task
     * @param agenda an ArrayList of type Task where the deadline task will be added to
     */
    private static void addDeadline(String description, boolean isDone, String date, ArrayList<Task> agenda) {
        Deadline deadline = new Deadline(description, date);
        deadline.setDone(isDone);
        agenda.add(deadline);
    }
    /**
     * Adds a task of type event to the agenda.
     * @param description of type String specifying the description of the event task
     * @param isDone of type boolean specifying whether the task is done or not
     * @param startDate of type String referring to the start date of the event
     * @param endDate of type String referring to the end date of the event
     * @param agenda an ArrayList of type Task where the event task will be added to
     */
    private static void addEvent(String description, boolean isDone,
                                 String startDate, String endDate, ArrayList<Task> agenda) {
        Event event = new Event(description, startDate, endDate);
        event.setDone(isDone);
        agenda.add(event);
    }

    /**
     * Writes out a todo task onto a file using FileWriter so that the new entries of the agenda can be
     * transcribed and copied onto the .txt file where the agenda is stored.
     * @param fw of type FileWriter
     * @param todo of Type ToDo referring to the task to be transcribed
     * @throws IOException in case the file cannot be correctly transcribed
     */
    public static void writeTodo(FileWriter fw, ToDo todo) throws IOException {
        fw.write(TODO_SYMBOL + INFO_SEPARATOR + todo.getStatusIcon()
                + INFO_SEPARATOR + todo.getDescription() + System.lineSeparator());
    }
    /**
     * Writes out a deadline task onto a file using FileWriter so that the new entries of the agenda can be
     * transcribed and copied onto the .txt file where the agenda is stored.
     * @param fw of type FileWriter
     * @param deadline of Type Deadline referring to the task to be transcribed
     * @throws IOException in case the file cannot be correctly transcribed
     */
    public static void writeDeadline(FileWriter fw, Deadline deadline) throws IOException {
        fw.write(DEADLINE_SYMBOL + INFO_SEPARATOR + deadline.getStatusIcon()
                + INFO_SEPARATOR + deadline.getDescription() +
                INFO_SEPARATOR + deadline.getDeadline() + System.lineSeparator());
    }
    /**
     * Writes out an event task onto a file using FileWriter so that the new entries of the agenda can be
     * transcribed and copied onto the .txt file where the agenda is stored.
     * @param fw of type FileWriter
     * @param event of Type Event referring to the task to be transcribed
     * @throws IOException in case the file cannot be correctly transcribed
     */
    public static void writeEvent(FileWriter fw, Event event) throws IOException {
        fw.write(EVENT_SYMBOL + INFO_SEPARATOR + event.getStatusIcon()
                + INFO_SEPARATOR + event.getDescription() +
                INFO_SEPARATOR + event.getStartTime() +
                INFO_SEPARATOR + event.getEndTime() + System.lineSeparator());
    }
}
