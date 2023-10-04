package magpie.input;
import magpie.exceptions.MagpieException;
import magpie.task.TaskList;

/**
 * Parses user input such as extracting out arguments, calling <code>InputValidator</code> methods to validate input
 * and <code>TaskList</code> methods to execute parsed commands.
 */
public class Parser {

    protected static String command;
    protected static String arguments;
    protected static String[] splitInputs;

    /**
     * Constructs <code>splitInputs</code>, <code>command</code> and <code>arguments</code>.
     *
     * @param input Input given by user.
     */
    public Parser(String input) {
        splitInputs = input.split(" ");
        command = splitInputs[0].toLowerCase();
        arguments = input.substring(command.length());
    }

    /**
     * Checks if given String can be parsed into an Integer and parses if validation is successful.
     *
     * @param input <code>String</code> input to be parsed into <code>int</code>.
     * @return Parsed Integer if successful, otherwise <code>-1</code>.
     */
    public int parseInt(String input) {

        boolean canParse = InputValidator.isValidInt(input);

        if (canParse) {
            return Integer.parseInt(input);
        } else {
            return -1;
        }

    }

    /**
     * Checks if user input is valid to add a <code>Todo</code> Task,
     * and adds a new <code>Todo</code> Task if valid.
     *
     * @throws MagpieException if user input is missing description.
     */
    public void manageTodo() throws MagpieException {

        InputValidator.validateTodo();
        TaskList.addTodo(false, arguments);

    }

    /**
     * Checks if user input is valid to add a <code>Deadline</code> Task, and adds a new Deadline task if valid.
     * Retrieves arguments and passes them into <code>addDeadline</code> method.
     *
     * @throws MagpieException if user input is missing <code>description</code> or a <code>/by </code> deadline.
     */
    public void manageDeadline() throws MagpieException {

        InputValidator.validateDeadline();
        int byIndex = arguments.indexOf("/by");
        String by = arguments.substring(byIndex + 3);
        String description = arguments.substring(0, byIndex);
        TaskList.addDeadline(false, description, by);

    }

    /**
     * Checks if user input is valid to add a <code>Event</code> Task, and adds a new Event task if valid.
     * Retrieves arguments and passes them into <code>addEvent</code> method.
     *
     * @throws MagpieException if user input is missing <code>description</code>,<code>/from</code>, or
     * <code>/to</code>.
     */
    public void manageEvent() throws MagpieException {

        InputValidator.validateEvent();
        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");
        String from = arguments.substring(fromIndex + 5, toIndex);
        String to = arguments.substring(toIndex + 3);
        String eventName = arguments.substring(0, fromIndex);
        TaskList.addEvent(false, eventName, from, to);

    }


    /**
     * Checks if user input contains a target index and parses index if valid.
     * Pass parsed index into <code>markTask</code> method to perform mark or unmark operation.
     *
     * @param isMark Boolean value to indicate a mark or unmark operation.
     * @throws MagpieException if index is missing or invalid.
     */
    public void markOrUnmarkTask(boolean isMark) throws MagpieException {

        InputValidator.validateTargetIsPresent();

        int index = parseInt(splitInputs[1]);
        if (index >= 0) {
            TaskList.markTask(index - 1, isMark);
        }

    }

    /**
     * Checks if user input contains a target index and parses index if valid.
     * Pass parsed index into <code>deleteTask</code> method to delete selected task.
     *
     * @throws MagpieException if index is missing or invalid.
     */
    public void deleteTask() throws MagpieException {
        InputValidator.validateTargetIsPresent();
        int index = parseInt(splitInputs[1]);
        if (index >= 0) {
            TaskList.deleteTask(index - 1);
        }
    }

    /**
     * Checks if user input contains a target keyword.
     * Pass target keyword into <code>findTask</code> method to find tasks containing keyword.
     *
     * @throws MagpieException if target keyword is missing.
     */
    public void findTask() throws MagpieException {
        InputValidator.validateTargetIsPresent();
        String keyword = splitInputs[1];
        TaskList.findTask(keyword);

    }

    /**
     * Processes <code>command</code> by matching it to a <code>Switch case</code>
     * and calling its respective methods to execute command.
     *
     * @throws MagpieException if command does not match any of the cases.
     */

    public void processCommand() throws MagpieException {

        switch (command) {
        case "list":
            TaskList.listTasks();
            break;
        case "todo":
            manageTodo();
            break;
        case "deadline":
            manageDeadline();
            break;
        case "event":
            manageEvent();
            break;
        case "mark":
            markOrUnmarkTask(true);
            break;
        case "unmark":
            markOrUnmarkTask(false);
            break;
        case "delete":
            deleteTask();
            break;
        case "find":
            findTask();
            break;
        default:
            throw new MagpieException("Please enter a valid command!");
        }

    }

}
