package alan.data.exception;

import alan.data.task.Task;

import java.util.ArrayList;

import static alan.common.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static alan.common.Messages.MESSAGE_INVALID_DEADLINE_FORMAT;
import static alan.common.Messages.MESSAGE_INVALID_EVENT_FROM_FORMAT;
import static alan.common.Messages.MESSAGE_INVALID_EVENT_TO_FORMAT;
import static alan.common.Messages.MESSAGE_INVALID_INPUT_COMMAND;
import static alan.common.Messages.MESSAGE_INVALID_TASK_NUMBER;

/**
 * Represents the error handling class.
 */
public class AlanException extends Exception {

    public AlanException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Checks lateral location of the specified position.
     *
     * @param selectedIndex index of the selected task.
     * @param taskList contains the list of tasks.
     * @throws AlanException  If selected index is out of range of taskList.
     */
    public static void checkOutOfTaskListIndex(int selectedIndex, ArrayList<Task> taskList) throws AlanException {
        int lastTaskIndex = taskList.size() - 1;

        if (selectedIndex > lastTaskIndex) {
            throw new AlanException(MESSAGE_INVALID_TASK_NUMBER);
        }
    }

    /**
     * Outputs message when user enters an invalid/unknown command.
     *
     * @throws AlanException If selected index is out of range of taskList.
     */
    public static void invalidInputCommand() throws AlanException {
        throw new AlanException(MESSAGE_INVALID_INPUT_COMMAND);
    }

    /**
     * Checks for any description text found after the command.
     *
     * @throws AlanException If there is only 1 String element found in array.
     */
    public static void checkEmptyDescription(String userInput) throws AlanException {
        String[] userInputWords = userInput.split(" ");

        if (userInputWords.length == 1) {
            throw new AlanException(MESSAGE_EMPTY_DESCRIPTION);
        }
    }

    /**
     * Checks for any text found after /by parameter.
     *
     * @param words contains an array of the information text split by parameter /by.
     * @throws AlanException If there is only 1 String element found in array.
     */
    public static void checkDeadlineInputFormat(String[] words) throws AlanException {
        if (words.length == 1) {
            throw new AlanException(MESSAGE_INVALID_DEADLINE_FORMAT);
        }
    }
    /**
     * Checks for any text found after /from parameter.
     *
     * @param splitDescriptionAndDate contains an array of the information text split by parameter /from.
     * @throws AlanException If there is only 1 String element found in array.
     */
    public static void checkEventInputFromFormat(String[] splitDescriptionAndDate) throws AlanException {
        if (splitDescriptionAndDate.length == 1) {
            throw new AlanException(MESSAGE_INVALID_EVENT_FROM_FORMAT);
        }
    }
    /**
     * Checks for any text found after /to parameter.
     *
     * @param splitFromAndTo contains an array of the information text split by parameter /to.
     * @throws AlanException If there is only 1 String element found in array.
     */
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
