package duke.commands;

import duke.inputProcess.Parser;
import duke.inputProcess.TaskList;

import java.time.format.DateTimeParseException;

public class AddEvent {
    Parser parser;
    TaskList tasks;
    public AddEvent(Parser parser, TaskList tasks){
        this.parser = parser;
        this.tasks = tasks;
    }

    public void AddEventTask(){
        Parser EventParse;
        try {
            try {
                EventParse = parser.getEventInput();
                if (EventParse.getTaskTime2().isBefore(EventParse.getTaskTime1())){
                    System.out.println("\tStart time cannot be later than end time");
                    return;
                }
            } catch (DateTimeParseException e) {
                System.out.println("\tThe input format for event need to be \"event eventName /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm\"");
                return;
            }
            tasks.addEvent(parser.getTaskName(), parser.getTaskTime1(), parser.getTaskTime2());
            System.out.println("\tGot it. I've added this task:\n\t\t" + tasks.getByIndex(tasks.getSize() - 1) + "\n\tNow you have "+ tasks.getSize() + " tasks in the list.");
        } catch(IndexOutOfBoundsException e){
            System.out.println("\tOOPS!!! The event timing need to separated by \"/from\" and \"/to\"" );
        }
    }
}
