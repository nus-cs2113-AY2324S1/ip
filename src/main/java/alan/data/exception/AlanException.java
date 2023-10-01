package alan.data.exception;

import alan.data.task.Task;

import java.util.ArrayList;

import static alan.common.Messages.EMPTY_DESCRIPTION_MESSAGE;
import static alan.common.Messages.INVALID_DEADLINE_FORMAT_MESSAGE;
import static alan.common.Messages.INVALID_EVENT_FROM_FORMAT_MESSAGE;
import static alan.common.Messages.INVALID_EVENT_TO_FORMAT_MESSAGE;
import static alan.common.Messages.INVALID_INPUT_COMMAND_MESSAGE;
import static alan.common.Messages.INVALID_TASK_NUMBER_MESSAGE;

public class AlanException extends Exception {
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
