package command;

import Oriento.Oriento;
import exception.OrientoException;
import exception.InvalidTimeException;
import message.Text;
import task.Deadline;

import java.io.IOException;

public class DeadlineCommand extends AddCommand {
    /**
     *
     * @param ddlCmd deadline command represents the original command starts from "deadline"
     */
    public DeadlineCommand(String ddlCmd){
        super(ddlCmd);
    }

    @Override
    public void executeCommand(){
        try {
            Oriento.list[Oriento.taskCount] = Deadline.newDdl(this.command);
            Text.createTaskSuccessMsg();
        } catch (OrientoException e) {
            e.incorrectFormatException("deadline");
        } catch (IOException io){
            System.out.println("OMG! Something went wrong! Please check if the source files are available.");
        } catch (InvalidTimeException ite){
            System.out.println("Please check your schedule.");
        }

    }
}
