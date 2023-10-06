package command;

import duke.Duke;
import exception.DukeException;
import message.Text;
import task.Deadline;

import java.io.IOException;

public class DeadlineCommand extends AddCommand {

    public DeadlineCommand(String ddlCmd){
        super(ddlCmd);
    }

    @Override
    public void executeCommand(){
        try {
            Duke.list[Duke.taskCount] = Deadline.newDdl(this.command);
            Text.createTaskSuccessMsg();
        } catch (DukeException e) {
            e.incorrectFormatException("deadline");
        } catch (IOException io){
            System.out.println("OMG! Something went wrong! Please check if the source files are available.");
        }

    }
}
