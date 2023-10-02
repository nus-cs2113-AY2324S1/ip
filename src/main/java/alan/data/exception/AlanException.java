package alan.data.exception;

import alan.data.task.Task;

import java.util.ArrayList;

import static alan.common.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static alan.common.Messages.MESSAGE_INVALID_DEADLINE_FORMAT;
import static alan.common.Messages.MESSAGE_INVALID_EVENT_FROM_FORMAT;
import static alan.common.Messages.MESSAGE_INVALID_EVENT_TO_FORMAT;
import static alan.common.Messages.MESSAGE_INVALID_INPUT_COMMAND;
import static alan.common.Messages.MESSAGE_INVALID_TASK_NUMBER;

public class AlanException extends Exception {
        public AlanException(String errorMessage) {
        super(errorMessage);
    }

    public static void checkOutOfTaskListIndex(int selectedIndex, ArrayList<Task> taskList) throws AlanException {
        int lastTaskIndex = taskList.size() - 1;

        if (selectedIndex > lastTaskIndex) {
            throw new AlanException(MESSAGE_INVALID_TASK_NUMBER);
        }
    }

    public static void invalidInputCommand() throws AlanException {
        throw new AlanException(MESSAGE_INVALID_INPUT_COMMAND);
    }

    public static void checkEmptyDescription(String userInput) throws AlanException {
        String[] userInputWords = userInput.split(" ");

        if (userInputWords.length == 1) {
            throw new AlanException(MESSAGE_EMPTY_DESCRIPTION);
        }
    }

    public static void checkDeadlineInputFormat(String[] words) throws AlanException {
        if (words.length == 1) {
            throw new AlanException(MESSAGE_INVALID_DEADLINE_FORMAT);
        }
    }

    public static void checkEventInputFromFormat(String[] splitDescriptionAndDate) throws AlanException {
        if (splitDescriptionAndDate.length == 1) {
            throw new AlanException(MESSAGE_INVALID_EVENT_FROM_FORMAT);
        }
    }

    public static void checkEventInputToFormat(String[] splitFromAndTo) throws AlanException {
        if (splitFromAndTo.length == 1) {
            throw new AlanException(MESSAGE_INVALID_EVENT_TO_FORMAT);
        }
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
