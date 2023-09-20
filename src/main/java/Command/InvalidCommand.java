package Command;

import Storage.TaskList;

public class InvalidCommand extends Command {

    public final String errorFeedback;

    public InvalidCommand(String errorFeedback) {
        this.errorFeedback = errorFeedback;
    }

    @Override
    public void execute(TaskList tasks) {
        System.out.println(this.errorFeedback);
    }
}
