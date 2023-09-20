package Command;

import Soccat.Event;
import Storage.TaskList;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    private final Event eventTask;

    public EventCommand(String taskName, String from, String to) {
        this.eventTask = new Event(taskName, from, to);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(eventTask);
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t" + eventTask);
        System.out.println("Now you have " + tasks.getTaskListLength() + " tasks in the list.");
    }
}