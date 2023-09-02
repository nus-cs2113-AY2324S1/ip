package commands;

import dawson.Dawson;
import dawson.TaskList;
import tasks.Deadline;

public class DeadlineCommand extends Command {

    private static final String BY_DELIMITER = "/by";

    private String payload;
    private TaskList list;

    public DeadlineCommand(String payload, TaskList list) {
        this.payload = payload;
        this.list = list;
    }

    @Override
    public void execute() {
        int by_position = payload.indexOf(BY_DELIMITER);

        if (by_position == -1) {
            Dawson.printText("Invalid format for Deadline task!");
            return;
        }

        String taskString = payload.substring(0, by_position).trim();
        String byString = payload.substring(by_position + BY_DELIMITER.length()).trim();

        Deadline newTask = new Deadline(taskString, byString);
        list.add(newTask);
    }

}