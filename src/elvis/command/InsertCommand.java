package elvis.command;

import elvis.exception.UnknownInputException;
import elvis.operation.TaskList;
import elvis.operation.Ui;
import elvis.task.Deadline;
import elvis.task.Event;
import elvis.task.ToDo;

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
        String date = inputBuffer.substring(inputBuffer.indexOf("/by") + 3).trim();
        String description = inputBuffer.replace("deadline ", "");  //Get rid of "deadline "
        description = description.substring(0, description.indexOf("/by"));          //Get rid of "/by..."
        if (isFromFile) {
            int isDone = getStatusFromFile(description);
            description = description.replaceAll("^\\d+\\s*", "");
            TaskList.addDeadline(description, isDone, date);
        } else {
            TaskList.addDeadline(description, 0, date);
            Ui.taskAddedMessagePrinter();
        }
    }

    public static void insertEvent(String inputBuffer, boolean isFromFile) throws UnknownInputException {
        String startTime = inputBuffer.substring(inputBuffer.indexOf("/from") + 5, inputBuffer.indexOf("/to")).trim();
        String endTime = inputBuffer.substring(inputBuffer.indexOf("/to") + 3).trim();
        String description = inputBuffer.replace("event ", "");         //Get rid of "event "
        description = description.substring(0, description.indexOf("/from")).trim();    //Get rid of "/from..."

        if (isFromFile) {
            int isDone = getStatusFromFile(description);
            description = description.replaceAll("^\\d+\\s*", "");
            TaskList.addEvent(description, isDone, startTime, endTime);
        } else {
            TaskList.addEvent(description, 0, startTime, endTime);
            Ui.taskAddedMessagePrinter();
        }
    }

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
