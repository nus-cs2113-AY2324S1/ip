package linguobot.command;

import linguobot.Ui;
import linguobot.task.Event;
import linguobot.task.TaskList;

import static linguobot.file.TaskFile.saveTaskListToFile;

public class AddEvent extends Command{
    private final Event taskToAdd;
    private final TaskList taskList;

    public AddEvent(Event taskToAdd, TaskList taskList) {
        this.taskToAdd = taskToAdd;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        taskList.addTask(taskToAdd);
        saveTaskListToFile(taskList);
        Ui.printMultipleText(new String[] {"Got it. I've added this task:", taskToAdd.toString(),
                "Now you have " + taskList.getNumberOfTasks() + " task(s) in the list."});
    }
}
