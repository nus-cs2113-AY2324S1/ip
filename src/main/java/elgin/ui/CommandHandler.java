package elgin.ui;

import elgin.exception.DukeException;
import elgin.task.TaskList;

import static elgin.ui.Ui.getUnknownCmdErrorMsg;
import static elgin.ui.Ui.sayBye;
import static elgin.parser.Parser.parseCommand;

public class CommandHandler {
    public static boolean handleCommand(TaskList tasks, String command) throws DukeException {
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
