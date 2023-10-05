package alice.parser;

import alice.enumeration.TaskStatus;
import alice.exceptions.InvalidCommandException;
import alice.exceptions.InvalidFormatException;
import alice.tasks.Task;
import alice.tasks.TaskList;

public class StatusParser {
    TaskList tasks;
    String userInput;
    String actionOfInput;

    StatusParser(String userInput, TaskList tasks) {
        this.userInput = userInput;
        this.tasks = tasks;
        String[] userInputArray = userInput.split(" ");
        this.actionOfInput = userInputArray[0];
    }

    public TaskStatus getStatus() {
        return TaskStatus.valueOf(actionOfInput);
    }

    public Task getTask() throws InvalidCommandException, InvalidFormatException {
        IndexValidityParser validityParser = new IndexValidityParser(userInput, tasks);
        int validTaskId = validityParser.getValidTaskId();

        return tasks.get(validTaskId);
    }
}
