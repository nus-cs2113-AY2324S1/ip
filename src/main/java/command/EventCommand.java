package command;

import duke.Duke;
import exception.DukeException;
import message.text;
import task.Event;

import java.io.IOException;

public class EventCommand extends AddCommand {

    public EventCommand(String eventTask){
        super(eventTask);
    }

    @Override
    public void executeCommand(){
        try {
            Duke.list[Duke.taskCount] = Event.newEventTask(this.command);
            text.createTaskSuccessMsg();
        } catch (DukeException e) {
            e.incorrectFormatException("event");
        } catch (IOException io){
            System.out.println("OMG! Something went wrong! Please check if the source files are available.");
        }
    }


}
