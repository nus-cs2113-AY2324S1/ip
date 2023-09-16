package magpie.input;
import magpie.exceptions.MagpieException;
import magpie.task.Deadline;
import magpie.task.Todo;
import magpie.task.Event;
import magpie.task.taskHandler;
import magpie.task.Task;
public class inputHandler {

    protected static String command;
    protected static String arguments;
    protected static String[] splitInput;

    public inputHandler(String input) {
        splitInput = input.split(" ");
        command = splitInput[0].toLowerCase();
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

        inputValidator.ValidateTodo();
        Task newTask = new Todo(arguments);
        taskHandler.add(newTask);

    }

    public void manageDeadline(taskHandler taskManager) throws MagpieException {


        inputValidator.ValidateDeadline();
        int byIndex = arguments.indexOf("/by");
        String by = arguments.substring(byIndex + 3);
        String description = arguments.substring(0, byIndex);
        Task newTask = new Deadline(description, by.trim());
        taskHandler.add(newTask);

    }

    public void manageEvent(taskHandler taskManager) throws MagpieException {


        inputValidator.ValidateEvent();
        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");
        String from = arguments.substring(fromIndex + 5, toIndex);
        String to = arguments.substring(toIndex + 3);
        String eventName = arguments.substring(0, fromIndex);
        Task newTask = new Event(eventName, from.trim(), to.trim());
        taskHandler.add(newTask);

    }

    public void markOrUnmarkTask(taskHandler taskManager, boolean isMark) throws MagpieException {


        inputValidator.ValidateMarking();

        int index = parseInt(splitInput[1]);
        if (index >= 0) {
            taskHandler.mark(index - 1, isMark);
        }

    }

    public void processCommand(taskHandler taskManager) throws MagpieException {

        switch (command) {
        case "list":
            taskHandler.print();
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
        default:
            System.out.println("Please enter a valid command!");
        }

    }


}
