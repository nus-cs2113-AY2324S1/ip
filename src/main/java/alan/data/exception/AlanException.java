package alan.data.exception;

import alan.data.task.Task;

import java.util.ArrayList;

import static alan.common.Messages.EMPTY_DESCRIPTION_MESSAGE;
import static alan.common.Messages.INVALID_DEADLINE_FORMAT_MESSAGE;
import static alan.common.Messages.INVALID_EVENT_FROM_FORMAT_MESSAGE;
import static alan.common.Messages.INVALID_EVENT_TO_FORMAT_MESSAGE;
import static alan.common.Messages.INVALID_INPUT_COMMAND_MESSAGE;
import static alan.common.Messages.INVALID_TASK_NUMBER_MESSAGE;
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
            throw new AlanException(INVALID_TASK_NUMBER_MESSAGE);
        }
    }

    /**
     * Outputs message when user enters an invalid/unknown command.
     *
     * @throws AlanException If selected index is out of range of taskList.
     */
    public static void invalidInputCommand() throws AlanException {
        throw new AlanException(INVALID_INPUT_COMMAND_MESSAGE);
    }

    /**
     * Checks for any description text found after the command.
     *
     * @throws AlanException If there is only 1 String element found in array.
     */
    public static void checkEmptyDescription(String userInput) throws AlanException {
        String[] userInputWords = userInput.split(" ");

        if (userInputWords.length == 1) {
            throw new AlanException(EMPTY_DESCRIPTION_MESSAGE);
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
            throw new AlanException(INVALID_DEADLINE_FORMAT_MESSAGE);
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
            throw new AlanException(INVALID_EVENT_FROM_FORMAT_MESSAGE);
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
            throw new AlanException(INVALID_EVENT_TO_FORMAT_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
