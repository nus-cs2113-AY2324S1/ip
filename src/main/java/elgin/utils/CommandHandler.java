package elgin.utils;

import elgin.exception.DukeException;
import elgin.task.TaskManager;

import static elgin.utils.ExceptionMessage.getUnknownCmdErrorMsg;
import static elgin.utils.FormatPrinter.sayBye;
import static elgin.utils.Parser.parseCommand;

public class CommandHandler {
    public static boolean handleCommand(TaskManager tasks, String command) throws DukeException {
        String[] parsedCommand = parseCommand(command);
        String userCommand = parsedCommand[0];
        String arguments = "";
        if (parsedCommand.length > 1) {
            arguments = parsedCommand[1];
        }
        switch (userCommand) {
        case "bye":
            sayBye();
            tasks.saveToFile();
            return false;
        case "list":
            tasks.listTasks();
            break;
        case "mark":
            tasks.setTaskIsDone(arguments, true);
            break;
        case "unmark":
            tasks.setTaskIsDone(arguments, false);
            break;
        case "todo":
            tasks.addTodo(userCommand, arguments);
            break;
        case "deadline":
            tasks.addDeadline(userCommand, arguments);
            break;
        case "event":
            tasks.addEvent(userCommand, arguments);
            break;
        case "delete":
            tasks.deleteTask(arguments);
            break;
        default:
            throw new DukeException(getUnknownCmdErrorMsg());
        }
        return true;
    }
}
