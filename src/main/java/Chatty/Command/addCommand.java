/**
 * The addCommmand class represents a command to add and its execution.
 */
package Chatty.Command;

import Chatty.Storage;
import Chatty.TaskList;
import Chatty.Ui;
import Chatty.tasks.*;

public class addCommand extends Command{

    char type;

    public addCommand (String input, char type){
        super(input);
        this.type = type;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        switch (type) {
        case 'T':
            String todoDescription = input.substring(5);
            tasks.addTask(new Todo(todoDescription));
            ui.printMessage("Got it. I've added this task:");
            ui.printMessage("[T][ ] " + todoDescription);
            break;
        case 'D':
            int byIndex = input.indexOf("/by");
            String deadlineDescription = input.substring(9, byIndex).trim();
            String by = input.substring(byIndex + 3).trim();
            tasks.addTask(new Deadline(deadlineDescription, by));
            ui.printMessage("Got it. I've added this task:");
            ui.printMessage("[D][ ] " + deadlineDescription + " (by: " + by + ")");
        case 'E':
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            String eventDescription = input.substring(6, fromIndex).trim();
            String from = input.substring(fromIndex + 5, toIndex).trim();
            String to = input.substring(toIndex + 3).trim();
            tasks.addTask(new Event(eventDescription, from, to));
            ui.printMessage("Got it. I've added this task:");
            ui.printMessage("[E][ ] " + eventDescription + " (from: " + from + " to: " + to + ")");
        }
    }

}
