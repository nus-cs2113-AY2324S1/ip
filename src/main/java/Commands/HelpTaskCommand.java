package Commands;

import taskmanagement.Task;
import taskmanagement.Storage;
import userinputs.Ui;

import java.util.ArrayList;


public class HelpTaskCommand extends Commands {
    static String outputFormat = "    ____________________________________________________________\n" +
            "    %s\n    ____________________________________________________________ ";
    public HelpTaskCommand(String input) {
        super(input);
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        System.out.printf((outputFormat) + "%n",
                "Help is here! :) \n" +
                        "    Listed below are the valid commands: \n" +
                        "    - mark \n    - unmark \n " +
                        "   - todo \n    - deadline \n    - event \n" +
                        "    For the formats below, replace *field* with your input \n" +
                        "    MARK: mark *existing task index* \n" +
                        "    UNMARK: unmark *existing task index* \n" +
                        "    TODO: todo *task name* \n" +
                        "    DEADLINE: deadline *task name* /by *deadline* \n" +
                        "    EVENT: event *event name* /from *start date* /to *end date*" +
                        "    DELETE: delete *existing task index* \n");
    }

}
