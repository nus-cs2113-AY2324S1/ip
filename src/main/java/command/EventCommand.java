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
     *  eventTask represent the whole original user command starts from "event"
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
