package command;

import Oriento.Oriento;
import exception.OrientoException;
import exception.InvalidTimeException;
import message.Text;
import task.Deadline;

import java.io.IOException;

public class DeadlineCommand extends AddCommand {

    /**
     * reusing the constructor of AddCommand
     * @param ddlCmd represents raw user command starts from "deadline"
     */
    public DeadlineCommand(String ddlCmd){
        super(ddlCmd);
    }

    /**
     * Exception could occur in the following cases:
     * 1. Input deadline command is in wrong format
     * 2. Failed to write into output file
     * 3. Inappropriate deadline is detected, e.g. deadline is pass already
     */
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
            System.out.println("Deadline is over already!");
        }

    }
}
