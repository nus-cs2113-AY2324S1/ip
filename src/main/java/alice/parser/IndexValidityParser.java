package alice.parser;

import alice.exceptions.InvalidCommandException;
import alice.exceptions.InvalidFormatException;
import alice.tasks.TaskList;

public class IndexValidityParser {
    public String userInput;
    private TaskList tasks;
    private int taskId;
    private int lengthOfInputArray;

    public IndexValidityParser(String userInput, TaskList tasks) {
        this.userInput = userInput;
        this.tasks = tasks;
        String[] userInputArray = userInput.split(" ");
        this.lengthOfInputArray = userInputArray.length;
        this.taskId = Integer.parseInt(userInputArray[1]) - 1;
    }

    /**
     * To check if the index given by user is within the range of the number of tasks so far
     *
     * @return true if index is within range, false otherwise
     */
    private boolean indexInRange() {
        if (taskId >= tasks.size() || taskId < 0) {
            return false;
        }
        return true;
    }

    /**
     * To check if the user input is in the correct format
     *
     * @return true if input is in the correct format, false otherwise
     */
    private boolean correctInputFormat() {
        if (lengthOfInputArray > 2) {
            return false;
        }
        return true;
    }

    /**
     * To check if the taskId in the user input is valid.
     * @return a valid taskId
     * @throws InvalidCommandException the command in the input is wrong
     * @throws InvalidFormatException the format of the input is wrong
     */
    public int getValidTaskId() throws InvalidCommandException, InvalidFormatException {
        if (!correctInputFormat()) {
            throw new InvalidFormatException();
        }
        if (!indexInRange()) {
            throw new InvalidCommandException();
        }

        return taskId;
    }
}
