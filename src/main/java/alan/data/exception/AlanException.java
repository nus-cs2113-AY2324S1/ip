package alan.data.exception;

import alan.data.task.Task;

import java.util.ArrayList;

public class AlanException extends Exception {
    public static final String INVALID_INPUT_COMMAND_MESSAGE = "Oof, I have no idea what are you saying duuude";
    public static final String EMPTY_DESCRIPTION_MESSAGE = "Oof Dude, you can't leave the description empty";
    public static final String INVALID_DEADLINE_FORMAT_MESSAGE = "Oof the deadline command isn't quite right you gotta fix the format, bro...\n[Remember it's: <description> /by <date>]";
    public static final String INVALID_EVENT_FROM_FORMAT_MESSAGE = "Oof duude, your /from formatting is whack\n[Remember it's: <description> /from <date> /to <date>]";
    public static final String INVALID_EVENT_TO_FORMAT_MESSAGE = "Oof my man, you need to work on that /to formatting\n[Remember it's: <description> /from <date> /to <date>]";
    public static final String INVALID_TASK_NUMBER_MESSAGE = "Oof maaaan there's no such task";
    public AlanException(String errorMessage) {
        super(errorMessage);
    }

    public static void checkOutOfTaskListIndex(int selectedIndex, ArrayList<Task> taskList) throws AlanException {
        int lastTaskIndex = taskList.size() - 1;

        if (selectedIndex > lastTaskIndex) {
            throw new AlanException(INVALID_TASK_NUMBER_MESSAGE);
        }
    }

    public static void invalidInputCommand() throws AlanException {
        throw new AlanException(INVALID_INPUT_COMMAND_MESSAGE);
    }

    public static void checkEmptyDescription(String userInput) throws AlanException {
        String[] userInputWords = userInput.split(" ");

        if (userInputWords.length == 1) {
            throw new AlanException(EMPTY_DESCRIPTION_MESSAGE);
        }
    }

    public static void checkDeadlineInputFormat(String[] words) throws AlanException {
        if (words.length == 1) {
            throw new AlanException(INVALID_DEADLINE_FORMAT_MESSAGE);
        }
    }

    public static void checkEventInputFromFormat(String[] splitDescriptionAndDate) throws AlanException {
        if (splitDescriptionAndDate.length == 1) {
            throw new AlanException(INVALID_EVENT_FROM_FORMAT_MESSAGE);
        }
    }

    public static void checkEventInputToFormat(String[] splitFromAndTo) throws AlanException {
        if (splitFromAndTo.length == 1) {
            throw new AlanException(INVALID_EVENT_TO_FORMAT_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
