package elvis.command;

import elvis.exception.UnknownInputException;
import elvis.operation.TaskList;
import elvis.operation.Ui;
import java.util.Scanner;

public class InsertCommand extends Command {
    public static void insertToDo(String inputBuffer, boolean isFromFile) throws UnknownInputException {
        String description = inputBuffer.replace("todo ", "");
        if (isFromFile) {
            int isDone = getStatusFromFile(description);
            description = description.replaceAll("^\\d+\\s*", "");
            TaskList.addToDo(description, isDone);
        } else {
            TaskList.addToDo(description, 0);
            Ui.taskAddedMessagePrinter();
        }
    }

    public static void insertDeadline(String inputBuffer, boolean isFromFile) throws UnknownInputException {
        String byWhen = inputBuffer.substring(inputBuffer.indexOf("/by") +3).trim();
        String description = inputBuffer.replace("deadline ", "").trim();  //Get rid of "deadline "
        description = description.substring(0, description.indexOf("/by")).trim();          //Get rid of "/by..."
        if (isFromFile) {
            int isDone = getStatusFromFile(description);
            description = description.replaceAll("^\\d+\\s*", "");  //Delete 0/1 from file string
            TaskList.addDeadline(description, isDone, byWhen);
        } else {
            TaskList.addDeadline(description, 0, byWhen);
            Ui.taskAddedMessagePrinter();
        }
    }

    public static void insertEvent(String inputBuffer, boolean isFromFile) throws UnknownInputException {
        String fromWhen = inputBuffer.substring(inputBuffer.indexOf("/from") + 5, inputBuffer.indexOf("/to")).trim();
        String toWhen = inputBuffer.substring(inputBuffer.indexOf("/to") + 3).trim();
        String description = inputBuffer.replace("event ", "");         //Get rid of "event "
        description = description.substring(0, description.indexOf("/from")).trim();     //Get rid of "/from../to.."

        if (isFromFile) {
            int isDone = getStatusFromFile(description);
            description = description.replaceAll("^\\d+\\s*", "");
            TaskList.addEvent(description, isDone, fromWhen, toWhen);
        } else {
            TaskList.addEvent(description, 0, fromWhen, toWhen);
            Ui.taskAddedMessagePrinter();
        }
    }

    /**
     * Returns the isDone state of the task
     * isDone is extracted from int
     * 1 is done, 0 is not done
     *
     * @param description
     * @return isDone
     * @throws UnknownInputException
     */
    public static int getStatusFromFile(String description) throws UnknownInputException {
        Scanner lineReader = new Scanner(description);  //Used to read mark/unmark from file
        if (!lineReader.hasNextInt()) {
            throw new UnknownInputException();
        }
        int isDone = lineReader.nextInt();
        if (isDone < 0 || isDone > 1) {
            throw new UnknownInputException();
        }
        return isDone;
    }
}
