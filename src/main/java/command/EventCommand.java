package command;

import duke.Duke;
import exception.DukeException;
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
            Duke.list[Duke.taskCount] = Event.newEventTask(this.command);
            Text.createTaskSuccessMsg();
        } catch (DukeException e) {
            e.incorrectFormatException("event");
        } catch (IOException io){
            System.out.println("OMG! Something went wrong! Please check if the source files are available.");
        } catch (InvalidTimeException d){
            System.out.println("Please check your period again.");
        }
    }


}
