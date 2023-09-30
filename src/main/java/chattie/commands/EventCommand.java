package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.error.ErrorType;
import chattie.tasks.Deadline;
import chattie.tasks.Event;
import chattie.Storage;

public class EventCommand extends Command {

    private static final int EVENT_LENGTH = 6;
    private static final int FROM_LENGTH = 5;
    private static final int TO_LENGTH = 3;
    private static String command;

    public EventCommand(String command) {
        this.command = command;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) throws ChattieException {
        if (command.trim().length() < EVENT_LENGTH) {
            throw new ChattieException(ErrorType.EMPTY_EVENT); //check if event is empty
        }

        int firstSlash = command.indexOf("/from");
        int secondSlash = command.indexOf("/to");
        if (firstSlash < 0 || secondSlash < 0) {
            throw new ChattieException(ErrorType.INVALID_EVENT); //check if '/from' or '/to' present
        }

        String task = command.substring(EVENT_LENGTH, firstSlash);
        String from = command.substring(firstSlash + FROM_LENGTH, secondSlash);
        String to = command.substring(secondSlash + TO_LENGTH);
        if (task.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new ChattieException(ErrorType.INVALID_EVENT); //check if [task], [from], or [to] is empty
        }

        Event event = new Event(task, from, to);
        tasks.add(event);
        ui.printAddMessage(event, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
