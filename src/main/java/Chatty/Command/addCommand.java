package Chatty.Command;

import Chatty.Storage;
import Chatty.TaskList;
import Chatty.Ui;
import Chatty.tasks.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
            return;
        case 'D':
            int byIndex = input.indexOf("/by");
            String deadlineDescription = input.substring(9, byIndex).trim();
            String by = input.substring(byIndex + 3).trim();
            Deadline deadline = new Deadline(deadlineDescription, by);
            tasks.addTask(deadline);
            ui.printMessage("Got it. I've added this task:");
            ui.printMessage(deadline.getDescription());
            return;
        case 'E':
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            String eventDescription = input.substring(6, fromIndex).trim();
            String from = input.substring(fromIndex + 5, toIndex).trim();
            String to = input.substring(toIndex + 3).trim();
            Event event = new Event(eventDescription, from, to);
            tasks.addTask(event);
            ui.printMessage("Got it. I've added this task:");
            ui.printMessage(event.getDescription());
            return;
        }
    }

}
