package listWhisper.task;

import listWhisper.exceptions.DescriptionFormatException;
import listWhisper.exceptions.InvalidCommandException;
import common.Messages;
import java.io.IOException;

public abstract class TaskClassifier {
    public static String classifyTaskForExecution(ListOfTasks listOfTasks, String input) throws DescriptionFormatException {
        String[] inputStrings = StringSplitter.splitInput(input);
        String command = inputStrings[0];
        String description = inputStrings[1];

        try {
            if (command.equals("bye")) {
                Messages.printByeMessage();
                DataManager.saveList(listOfTasks);
            } else if (command.equals("list")) {
                Messages.printListMessage(listOfTasks);
            } else if (command.equals("mark")) {
                Messages.printMarkMessage(listOfTasks.mark(input));
            } else if (command.equals("unmark")) {
                listOfTasks.unmark(input);
                Messages.printUnmarkMessage(listOfTasks.unmark(input));
            } else if (command.equals("todo")) {
                listOfTasks.addTodo(input.substring("todo".length()));
                Messages.printAddMessage(listOfTasks);
            } else if (command.equals("deadline")) {
                listOfTasks.addDeadline(input.substring("deadline".length()));
                Messages.printAddMessage(listOfTasks);
            } else if (command.equals("event")) {
                listOfTasks.addEvent(input.substring("event".length()));
                Messages.printAddMessage(listOfTasks);
            } else if (command.equals("delete")) {
                Messages.printDeleteMessage(listOfTasks, listOfTasks.delete(input));
            } else {
                throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DescriptionFormatException | InvalidCommandException | IOException e) {
            System.out.println(e);
        }
        return command;
    }

    public static void classifyTaskForLoading(ListOfTasks listOfTasks, String input) {
        String[] inputStrings = StringSplitter.splitInput(input);
        String command = inputStrings[0];
        String description = inputStrings[1];

        try {
            if (command.equals("mark")) {
                listOfTasks.mark(input);
            } else if (command.equals("unmark")) {
                listOfTasks.unmark(input);
            } else if (command.equals("todo")) {
                listOfTasks.addTodo(input.substring("todo".length()));
            } else if (command.equals("deadline")) {
                listOfTasks.addDeadline(input.substring("deadline".length()));
            } else if (command.equals("event")) {
                listOfTasks.addEvent(input.substring("event".length()));
            } else if (command.equals("delete")) {
                listOfTasks.delete(input);
            } else {
                throw new InvalidCommandException("Error data format in file");
            }
        } catch (DescriptionFormatException | InvalidCommandException e) {
            System.out.println(e);
        }
    }
}
