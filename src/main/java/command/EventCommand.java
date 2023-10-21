package command;

import Oriento.Oriento;
import exception.OrientoException;
import exception.InvalidTimeException;
import message.Text;
import task.Event;

import java.io.IOException;

public class EventCommand extends AddCommand {

    public EventCommand(String eventTask){
        super(eventTask);
    }

    /**
     *  eventTask represent the whole raw user command starts from "event"
     *  Exception could occur in the following cases:
     *  1. Input event command is with wrong format
     *  2. Failed to write into the output file
     *  3. Inappropriate period is detected, e.g. event period pass already
     */
    @Override
    public void executeCommand(){
        try {
            Oriento.list[Oriento.taskCount] = Event.newEventTask(this.command);
            Text.createTaskSuccessMsg();
        } catch (OrientoException e) {
            e.incorrectFormatException("event");
        } catch (IOException io){
            System.out.println("OMG! Something went wrong! Please check if the source files are available.");
        } catch (InvalidTimeException d){
            System.out.println("Please check your period again.");
        }
    }


}
