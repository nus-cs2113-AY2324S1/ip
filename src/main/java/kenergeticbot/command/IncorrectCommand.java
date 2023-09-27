package kenergeticbot.command;

import kenergeticbot.TaskList;

public class IncorrectCommand extends Command{
    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }


    public void execute(TaskList taskList) {
        System.out.println(feedbackToUser);
    }
}
