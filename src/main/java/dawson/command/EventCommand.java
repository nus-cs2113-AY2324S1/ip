package dawson.command;

import dawson.Dawson;
import dawson.TaskList;
import dawson.task.Event;

public class EventCommand extends Command {

    private static final String FROM_DELIMITER = "/from";
    private static final String TO_DELIMITER = "/to";

    private String payload;
    private TaskList list;

    public EventCommand(String payload, TaskList list) {
        this.payload = payload;
        this.list = list;
    }

    @Override
    public void execute() {
        int from_position = payload.indexOf(FROM_DELIMITER);
        int to_position = payload.indexOf(TO_DELIMITER);

        if (from_position == -1 || to_position == -1) {
            Dawson.printText("Invalid format for Event task!");
            return;
        }

        String taskString = payload.substring(0, from_position).trim();
        String fromString = payload.substring(from_position + FROM_DELIMITER.length(), to_position).trim();
        String toString = payload.substring(to_position + TO_DELIMITER.length()).trim();

        Event newTask = new Event(taskString, fromString, toString);
        list.add(newTask);
    }

}