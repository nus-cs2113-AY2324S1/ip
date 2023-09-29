package magpie.input;
import magpie.exceptions.MagpieException;
import magpie.task.TaskList;

public class Parser {

    protected static String command;
    protected static String arguments;
    protected static String[] splitInputs;

    public Parser(String input) {
        splitInputs = input.split(" ");
        command = splitInputs[0].toLowerCase();
        arguments = input.substring(command.length());
    }

    public int parseInt(String input) {

        boolean canParse = InputValidator.isValidInt(input);

        if (canParse) {
            return Integer.parseInt(input);
        } else {
            return -1;
        }

    }

    public void manageTodo(TaskList taskManager) throws MagpieException {

        InputValidator.validateTodo();
        TaskList.addTodo(false, arguments);

    }

    public void manageDeadline(TaskList taskManager) throws MagpieException {


        InputValidator.validateDeadline();
        int byIndex = arguments.indexOf("/by");
        String by = arguments.substring(byIndex + 3);
        String description = arguments.substring(0, byIndex);
        TaskList.addDeadline(false, description, by);

    }

    public void manageEvent(TaskList taskManager) throws MagpieException {


        InputValidator.validateEvent();
        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");
        String from = arguments.substring(fromIndex + 5, toIndex);
        String to = arguments.substring(toIndex + 3);
        String eventName = arguments.substring(0, fromIndex);
        TaskList.addEvent(false, eventName, from, to);

    }

    public void markOrUnmarkTask(TaskList taskManager, boolean isMark) throws MagpieException {


        InputValidator.validateTargetIsPresent();

        int index = parseInt(splitInputs[1]);
        if (index >= 0) {
            TaskList.markTask(index - 1, isMark);
        }

    }

    public void deleteTask(TaskList taskManager) throws MagpieException {
        InputValidator.validateTargetIsPresent();
        int index = parseInt(splitInputs[1]);
        if (index >= 0) {
            TaskList.deleteTask(index - 1);
        }
    }

    public void findTask(TaskList taskManager) throws MagpieException {
        InputValidator.validateTargetIsPresent();
        String keyword = splitInputs[1];
        TaskList.findTask(keyword);

    }

    public void processCommand(TaskList taskManager) throws MagpieException {

        switch (command) {
        case "list":
            TaskList.listTasks();
            break;
        case "todo":
            manageTodo(taskManager);
            break;
        case "deadline":
            manageDeadline(taskManager);
            break;
        case "event":
            manageEvent(taskManager);
            break;
        case "mark":
            markOrUnmarkTask(taskManager, true);
            break;
        case "unmark":
            markOrUnmarkTask(taskManager, false);
            break;
        case "delete":
            deleteTask(taskManager);
            break;
        case "find":
            findTask(taskManager);
            break;
        default:
            System.out.println("Please enter a valid command!");
        }

    }


}
