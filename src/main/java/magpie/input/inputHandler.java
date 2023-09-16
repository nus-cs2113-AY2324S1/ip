package magpie.input;
import magpie.exceptions.MagpieException;
import magpie.task.taskHandler;

public class inputHandler {

    protected static String command;
    protected static String arguments;
    protected static String[] splitInputs;

    public inputHandler(String input) {
        splitInputs = input.split(" ");
        command = splitInputs[0].toLowerCase();
        arguments = input.substring(command.length());
    }

    public int parseInt(String input) {

        boolean canParse = inputValidator.isValidInt(input);

        if (canParse) {
            return Integer.parseInt(input);
        } else {
            return -1;
        }

    }

    public void manageTodo(taskHandler taskManager) throws MagpieException {

        inputValidator.validateTodo();
        taskHandler.addTodo(false, arguments);

    }

    public void manageDeadline(taskHandler taskManager) throws MagpieException {


        inputValidator.validateDeadline();
        int byIndex = arguments.indexOf("/by");
        String by = arguments.substring(byIndex + 3);
        String description = arguments.substring(0, byIndex);
        taskHandler.addDeadline(false, description, by);

    }

    public void manageEvent(taskHandler taskManager) throws MagpieException {


        inputValidator.validateEvent();
        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");
        String from = arguments.substring(fromIndex + 5, toIndex);
        String to = arguments.substring(toIndex + 3);
        String eventName = arguments.substring(0, fromIndex);
        taskHandler.addEvent(false, eventName, from, to);

    }

    public void markOrUnmarkTask(taskHandler taskManager, boolean isMark) throws MagpieException {


        inputValidator.validateIndexIsPresent();

        int index = parseInt(splitInputs[1]);
        if (index >= 0) {
            taskHandler.markTask(index - 1, isMark);
        }

    }

    public void deleteTask(taskHandler taskManager) throws MagpieException {
        inputValidator.validateIndexIsPresent();
        int index = parseInt(splitInputs[1]);
        if (index >= 0) {
            taskHandler.deleteTask(index - 1);
        }
    }

    public void processCommand(taskHandler taskManager) throws MagpieException {

        switch (command) {
        case "list":
            taskHandler.listTasks();
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
        default:
            System.out.println("Please enter a valid command!");
        }

    }


}
