package kenergeticbot.command;

import kenergeticbot.TaskList;
import kenergeticbot.ui.TextUi;
import static kenergeticbot.common.Messages.SEPARATING_LINE;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command{
    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public void execute(TaskList taskList, TextUi ui) {
        ui.showToUser(SEPARATING_LINE, feedbackToUser, SEPARATING_LINE);
    }
}
