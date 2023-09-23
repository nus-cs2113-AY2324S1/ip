package duke;

/**
 * Represents the main class for the Duke application.
 * Duke is a task management program that allows users to add, delete, and manage tasks.
 * Itay class initializes the application and provides methods to handle user commands.
 * @author Itay Refaely
 * @version 1.0
 * @since 24/09/2023
 */
public class Itay {
    
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static Parser p;

    /**
     * Constructs a new instance of the Itay class.
     *
     * @param filePath The file path to be used for data storage.
     * @throws DukeException If there is an issue initializing the storage or loading tasks.
     */
    public Itay(String filePath) throws DukeException {
        storage = new Storage(filePath);
        // Load tasks from the data file at startup
        tasks = storage.load();
        ui = new Ui(tasks);
        p = new Parser();
    }

    private void run() throws DukeException {
        ui.printIntro();

        while(true) {
            try {
                String input = ui.readCommand();
                if(p.isExit(input)) {
                    break;
                }
                p.parse(input);
            } catch(DukeException dukeEx) {
                ui.printException(dukeEx.toString());
            }
        }
        
        // Save tasks to the data file before exiting
        storage.save(tasks);
        ui.printExit();
    }

    public static void validateNonEmptyCommand(String input) throws DukeException {
        if(input.isEmpty()) {
            throw new DukeException(ErrorMessageType.EMPTY_COMMAND);
        }
    }

    public static void validateNonEmptyDescription(String[] splitInput) throws DukeException {
        if(splitInput.length == 1) {
            throw new DukeException(ErrorMessageType.INVALID_DESCRIPTION);
        }
    }

    public static void printList() {
        ui.printList();
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param splitInput An array containing the user's input.
     * @throws DukeException If there's an error handling the mark operation.
     */
    public static void handleMark(String[] splitInput) throws DukeException {
        int taskIdx = getTaskIndex(splitInput);
        tasks.getTaskAt(taskIdx).setStatus(true);
        storage.save(tasks);
        ui.printMarked(taskIdx);
    }

    /**
     * Unmarks a previously marked task as not done based on user input.
     *
     * @param splitInput An array containing the user's input.
     * @throws DukeException If there's an error handling the unmark operation.
     */
    public static void handleUnmark(String[] splitInput) throws DukeException {
        int taskIdx = getTaskIndex(splitInput);
        tasks.getTaskAt(taskIdx).setStatus(false);
        storage.save(tasks);
        ui.printUnmarked(taskIdx);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param splitInput An array containing the user's input.
     * @throws DukeException If there's an error handling the delete operation.
     */
    public static void handleDelete(String[] splitInput) throws DukeException {
        int taskIdx = getTaskIndex(splitInput);
        Task toDelete = tasks.getTaskAt(taskIdx);
        tasks.removeTaskAt(taskIdx);
        storage.save(tasks);
        ui.printDelete(toDelete);
    }

    /**
     * Handles the addition of a Todo task to the task list.
     *
     * @param input      The user's input.
     * @param splitInput An array containing the user's input.
     * @throws DukeException If there's an error handling the Todo task.
     */
    public static void handleTodo(String input, String[] splitInput) throws DukeException {
        String description = input.substring(input.indexOf(' ') + 1);
        Task task = new Task(description, 'T');
        addTask(task);
    }

    /**
     * Handles the addition of a Deadline task to the task list.
     *
     * @param input The user's input.
     * @throws DukeException If there's an error handling the Deadline task.
     */
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
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new DukeException(ErrorMessageType.INVALID_DEADLINE);
        }
    }

    /**
     * Handles the addition of an Event task to the task list.
     *
     * @param input The user's input.
     * @throws DukeException If there's an error handling the Event task.
     */
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
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new DukeException(ErrorMessageType.INVALID_EVENT);
        } 
    }

    public static void handleFind(String input, String[] splitInput) throws DukeException {
        validateActionCommand(splitInput);
        String keyword = splitInput[1];
        TaskList foundTasks = new TaskList();
        String[] splitDescription;
        Task task;
        for(int i = 0; i < tasks.getSize(); i++) {
            task = tasks.getTaskAt(i);
            splitDescription = task.getDescription().split(" ");
            for(String word: splitDescription) {
                if(word.equalsIgnoreCase(keyword)) {
                    foundTasks.addTask(task);
                }
            }
        }
        ui.printFound(foundTasks);
    }

    public static void handleBadIndicator() throws DukeException {
        throw new DukeException(ErrorMessageType.INVALID_INDICATOR);
    }
    
    private static void addTask(Task task) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks);
        ui.printAddTask(task);
    }

    private static int getTaskIndex(String[] splitInput) throws DukeException {
        validateActionCommand(splitInput);
        int taskIdx;
        try {
            taskIdx = Integer.parseInt(splitInput[1]) - 1;
            if (taskIdx < 0 || taskIdx >= tasks.getSize()) {
                throw new DukeException(ErrorMessageType.INVALID_TASK_NUMBER);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException(e.getMessage(), e);
        }
        return taskIdx;
    }

    private static void validateActionCommand(String[] splitInput) throws DukeException {
        if(splitInput.length > 2) {
            throw new DukeException(ErrorMessageType.INVALID_ACTION_COMMAND);
        }
    }

    public static void main(String[] args) throws DukeException {
        new Itay("./data/duke.txt").run();
    }
}