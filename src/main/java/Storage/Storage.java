package Storage;

import Data.*;
import Exceptions.CSGPTFileCorruptedError;
import Exceptions.CSGPTParsingException;
import Exceptions.CSGPTReadFileException;
import Exceptions.CSGPTWriteFileException;
import Parser.DateParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Handles the reading and writing of data to the file
 */
public class Storage {
    private static final String FILE_PATH = "data/CSGPT.txt";
    private static final String TODO_INDICATOR = "T";
    private static final String DEADLINE_INDICATOR = "D";
    private static final String EVENT_INDICATOR = "E";

    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_DONE_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 2;
    private static final int TASK_DATE_INDEX = 3;

    private static final String DONE_INDICATOR = "X";
    private static final String NOT_DONE_INDICATOR = " ";
    private static final String DEFAULT_SEPARATOR = " | ";
    private static final String DATE_SEPARATOR = " - ";

    private String filePath;

    /**
     * Constructor for Storage
     */
    public Storage() {
        this.filePath = FILE_PATH;
    }

    /**
     * Reads the data from the file into the program's task list, creating the file and directory if it does not exist
     * @param taskList Task list to load the data into
     * @throws CSGPTReadFileException If there is an error reading from the file
     * @throws CSGPTFileCorruptedError If the file is corrupted
     */
    public void readFromFile(TaskList taskList) throws CSGPTReadFileException, CSGPTFileCorruptedError, CSGPTParsingException {
        File file = new File(filePath);
        // Create file and directories if file does not exist
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new CSGPTReadFileException();
            }
        } else {
            loadFileData(file, taskList);
        }
    }

    /**
     * Loads the data from the file into the program's task list
     * @param file File to read from
     * @param taskList Task list to load the data into
     * @throws CSGPTReadFileException If there is an error reading from the file
     * @throws CSGPTFileCorruptedError If the file is corrupted
     */
    public void loadFileData(File file, TaskList taskList) throws CSGPTReadFileException, CSGPTFileCorruptedError, CSGPTParsingException {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] taskArray = line.split(Pattern.quote(DEFAULT_SEPARATOR)); // Pipe operator is a special character in regex
                if (taskArray.length == 0) {
                    throw new CSGPTReadFileException();
                }
                String taskType = taskArray[TASK_TYPE_INDEX];
                if (taskArray.length < TASK_DESCRIPTION_INDEX+1) {
                    throw new CSGPTFileCorruptedError("Task description missing.");
                }
                String description = taskArray[TASK_DESCRIPTION_INDEX];
                boolean isDone = parseBoolean(taskArray[TASK_DONE_INDEX]);
                String dateString;
                switch (taskType) {
                case TODO_INDICATOR:
                    addTodo(
                        description,
                        isDone,
                        taskList
                    );
                    break;
                case DEADLINE_INDICATOR:
                    if (taskArray.length != TASK_DATE_INDEX + 1) {
                        throw new CSGPTFileCorruptedError("Deadline date missing.");
                    }
                    dateString = taskArray[TASK_DATE_INDEX];

                    LocalDate date = DateParser.parse(dateString);
                    addDeadline(
                            description,
                            isDone,
                            date,
                            taskList
                    );
                    break;
                case EVENT_INDICATOR:
                    if (taskArray.length != TASK_DATE_INDEX + 1) {
                        throw new CSGPTFileCorruptedError("Event date missing.");
                    }
                    dateString = taskArray[TASK_DATE_INDEX];
                    String[] dateArray = dateString.split(DATE_SEPARATOR);
                    if (dateArray.length != 2) {
                        throw new CSGPTFileCorruptedError("Event date format incorrect.");
                    }
                    String fromString = dateArray[0];
                    String toString = dateArray[1];
                    LocalDate from = DateParser.parse(fromString);
                    LocalDate to = DateParser.parse(toString);
                    addEvent(
                            description,
                            isDone,
                            from,
                            to,
                            taskList
                    );
                    break;
                default:
                    throw new CSGPTFileCorruptedError("Task type not recognised.");
                }
            }
        } catch (IOException e) {
            throw new CSGPTReadFileException();
        }
    }

    private void addTodo(String description, boolean isDone, TaskList taskList) {
        Task todo = new Todo(description);
        todo.setDone(isDone);
        taskList.add(todo);
    }
    public void addDeadline(String description, boolean isDone, LocalDate by, TaskList taskList) {
        Task deadline = new Deadline(description, by);
        deadline.setDone(isDone);
        taskList.add(deadline);
    }
    public void addEvent(String description, boolean isDone, LocalDate from, LocalDate to, TaskList taskList) {
        Task event = new Event(description, from, to);
        event.setDone(isDone);
        taskList.add(event);
    }
    private boolean parseBoolean(String input) throws CSGPTFileCorruptedError {
        if (input.equals(DONE_INDICATOR)) {
            return true;
        } else if (input.equals(NOT_DONE_INDICATOR)) {
            return false;
        } else {
            throw new CSGPTFileCorruptedError("Task done status not recognised.");
        }
    }

    /**
     * Writes the data from the task list to the file
     * @param taskList Task list to write to the file
     * @throws CSGPTWriteFileException If there is an error writing to the file
     */
    public void writeToFile(TaskList taskList) throws CSGPTWriteFileException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 1; i < taskList.size() + 1; i++) {
                Task task = taskList.getTask(i);
                boolean isTodo = task instanceof Todo;
                boolean isDeadline = task instanceof Deadline;
                boolean isEvent = task instanceof Event;
                if (isTodo) {
                    writeTodo(fw, (Todo) task);
                } else if (isDeadline) {
                    writeDeadline(fw, (Deadline) task);
                } else if (isEvent) {
                    writeEvent(fw, (Event) task);
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new CSGPTWriteFileException();
        }
    }

    private void writeTodo(FileWriter fw, Todo todo) throws IOException {
        fw.write(TODO_INDICATOR + DEFAULT_SEPARATOR + todo.getStatusIcon() + DEFAULT_SEPARATOR + todo.getDescription() + System.lineSeparator());
    }


    public void writeDeadline(FileWriter fw, Deadline deadline) throws IOException {
        fw.write(DEADLINE_INDICATOR + DEFAULT_SEPARATOR + deadline.getStatusIcon() + DEFAULT_SEPARATOR + deadline.getDescription() + DEFAULT_SEPARATOR + deadline.getByStringToSave() + System.lineSeparator());
    }

    public void writeEvent(FileWriter fw, Event event) throws IOException {
        fw.write(EVENT_INDICATOR + DEFAULT_SEPARATOR + event.getStatusIcon() + DEFAULT_SEPARATOR + event.getDescription() + DEFAULT_SEPARATOR + event.getFromStringToSave() + DATE_SEPARATOR + event.getToStringToSave() + System.lineSeparator());
    }

}
