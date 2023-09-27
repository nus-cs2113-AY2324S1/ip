package kenergeticbot.command;

import kenergeticbot.TaskList;
import kenergeticbot.exceptionhandler.KenergeticBotException;
import java.text.ParseException;

import static kenergeticbot.exceptionhandler.KenergeticBotException.*;

public class Parser{
    public static Command parseCommand(TaskList taskList, String item) {
        String command[] = item.split(" ");
        switch (command[0]) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return prepareMark(taskList,item);
        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(taskList,item);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(taskList,item);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            try{
                return new AddCommand(item);
            } catch (KenergeticBotException e) {
                return new IncorrectCommand(e.getMessage());
            }
        }
    }

    private static Command prepareMark(TaskList taskList, String item) {
        try {
            final int listIndex = parseArgsAsDisplayedIndex(taskList ,item, "mark");
            return new MarkCommand(listIndex);
        } catch (KenergeticBotException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        return null;
    }

    private static Command prepareUnmark(TaskList taskList, String item) {
        try {
            final int listIndex = parseArgsAsDisplayedIndex(taskList, item, "unmark");
            return new UnmarkCommand(listIndex);
        } catch (KenergeticBotException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        return null;
    }

    private static Command prepareDelete(TaskList taskList, String item) {
        try {
            final int listIndex = parseArgsAsDisplayedIndex(taskList ,item, "delete");
            return new DeleteCommand(listIndex);
        } catch (KenergeticBotException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        return null;
    }
    private static int parseArgsAsDisplayedIndex(TaskList taskList, String item, String command) throws ParseException, NumberFormatException, KenergeticBotException {
        String formattedString = item.replace(command, "").trim();
        int listIndex = Integer.parseInt(formattedString);
        if (formattedString.isEmpty()) {
            throw new KenergeticBotException(COMMAND_TYPO_NO_NUMBER);
        } else if (listIndex > taskList.getSize()) {
            throw new KenergeticBotException(OUT_OF_RANGE);
        }
        return listIndex;
    }
}
