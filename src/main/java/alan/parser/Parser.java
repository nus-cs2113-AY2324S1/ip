package alan.parser;

import alan.data.TaskList;
import alan.data.exception.AlanException;
import alan.data.task.Task;
import alan.ui.Ui;

import static alan.data.exception.AlanException.checkEmptyDescription;
import static alan.data.exception.AlanException.checkEventInputFromFormat;
import static alan.data.exception.AlanException.checkEventInputToFormat;
import static alan.data.exception.AlanException.checkOutOfTaskListIndex;
import static alan.data.exception.AlanException.invalidInputCommand;

public class Parser {
    private TaskList tasks;
    private Ui ui;

    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }
    public void processCommandHandler(String userInput) throws AlanException {
        String[] userInputWords = userInput.split(" ");
        String command = userInputWords[0];

        switch (command) {
        case "bye":
            ui.showExitMessage();
            break;
        case "list":
            //print the tasks in the lists
            listCommandHandler();
            break;
        case "mark":
            //mark tasks as done
            markingCommandHandler(userInput, true);
            break;
        case "unmark":
            //unmark tasks as undone
            markingCommandHandler(userInput, false);
            break;
        case "todo":
            //add to-do task to the list
            checkEmptyDescription(userInput);
            todoCommandHandler(userInput);
            break;
        case "deadline":
            //add deadline task to the list
            checkEmptyDescription(userInput);
            deadlineCommandHandler(userInput);
            break;
        case "event":
            //add event task to the list
            checkEmptyDescription(userInput);
            eventCommandHandler(userInput);
            break;
        case "delete":
            //delete task from the list
            deleteCommandHandler(userInput);
            break;
        default:
            invalidInputCommand();
        }
    }

    public void listCommandHandler() {
        ui.showListMessage(tasks.getTaskList());
    }

    public void markingCommandHandler(String userInput, boolean isMark) throws AlanException {
        String[] words = userInput.split(" ");
        int selectedTaskIndex = Integer.parseInt(words[1]) - 1;

        checkOutOfTaskListIndex(selectedTaskIndex, tasks.getTaskList());
        Task targetTask = tasks.getSelectedTask(selectedTaskIndex);

        if (isMark) {
            tasks.markTask(selectedTaskIndex, true);
            ui.showMarkTaskMessage(targetTask);
        } else {
            tasks.markTask(selectedTaskIndex, false);
            ui.showUnmarkTaskMessage(targetTask);
        }
    }

    public void todoCommandHandler(String userInput) {
        String description = userInput.replace("todo ", "");
        tasks.addToDo(description);

        ui.showTaskAddedMessage(tasks.getTaskList());
    }

    public void deadlineCommandHandler(String userInput) throws AlanException {
        String filteredUserInput = userInput.replace("deadline ", "");
        String[] data = filteredUserInput.split(" /by ");

        String description = data[0];
        String by = data[1];

        tasks.addDeadline(description, by);

        ui.showTaskAddedMessage(tasks.getTaskList());
    }

    public void eventCommandHandler(String userInput) throws AlanException {
        String filteredUserInput = userInput.replace("event ", "");
        String[] splitDescriptionAndDate = filteredUserInput.split(" /from ");

        checkEventInputFromFormat(splitDescriptionAndDate);

        String[] splitFromAndTo = splitDescriptionAndDate[1].split(" /to ");

        checkEventInputToFormat(splitFromAndTo);

        String description = splitDescriptionAndDate[0];
        String from = splitFromAndTo[0];
        String to = splitFromAndTo[1];

        tasks.addEvent(description, from, to);

        ui.showTaskAddedMessage(tasks.getTaskList());
    }

    public void deleteCommandHandler(String userInput) throws AlanException {
        String[] words = userInput.split(" ");
        int selectedTaskIndex = Integer.parseInt(words[1]) - 1;
        checkOutOfTaskListIndex(selectedTaskIndex, tasks.getTaskList());

        Task targetTask = tasks.getSelectedTask(selectedTaskIndex);
        ui.showDeleteTaskMessage(targetTask);
        tasks.removeTask(selectedTaskIndex);

        int numberOfTasks = tasks.getTaskListSize();
        ui.showNumberOfTasksMessage(numberOfTasks);
    }
}
