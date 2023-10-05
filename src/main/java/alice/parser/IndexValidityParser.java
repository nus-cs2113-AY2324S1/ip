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

    private boolean indexInRange() throws InvalidCommandException{
        if (taskId >= tasks.size() || taskId < 0) {
            return false;
        }
        return true;
    }

    private boolean correctInputFormat() {
        if (lengthOfInputArray > 2) {
            return false;
        }
        return true;
    }

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
