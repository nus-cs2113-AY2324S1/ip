package parser;

import enumeration.TaskStatus;
import exceptions.InvalidCommandException;
import exceptions.InvalidFormatException;
import tasks.Task;
import tasks.TaskList;

public class StatusParser {
    TaskList tasks;
    String userInput;
    String actionOfInput;
    int taskId;
    int lengthOfInputArray;
    StatusParser(String userInput, TaskList tasks) {
        this.userInput = userInput;
        this.tasks = tasks;
        handleInput();
    }

    public void handleInput() {
        String[] userInputArray = userInput.split(" ");
        this.lengthOfInputArray = userInputArray.length;
        this.actionOfInput = userInputArray[0];
        this.taskId = Integer.parseInt(userInputArray[1]) - 1;
    }

    public TaskStatus getStatus() {
        return TaskStatus.valueOf(actionOfInput);
    }

    public Task getTask() throws InvalidCommandException, InvalidFormatException {
        ValidityParser parser = new ValidityParser(tasks);
        if (!parser.taskIdInRange(taskId)) {
            throw new InvalidCommandException();
        }
        if (lengthOfInputArray > 2) {
            throw new InvalidFormatException();
        }

        return tasks.get(taskId);
    }
}
