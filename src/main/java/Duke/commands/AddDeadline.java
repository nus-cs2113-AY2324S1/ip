package duke.commands;

import duke.inputProcess.Parser;
import duke.inputProcess.TaskList;

import java.time.format.DateTimeParseException;

public class AddDeadline {
    Parser parser;
    TaskList tasks;
    public AddDeadline(Parser parser, TaskList tasks){
        this.parser = parser;
        this.tasks = tasks;
    }
    public void AddDeadlineTask(){
        try {
            try {
                Parser deadlineParse = parser.getDeadlineInput();
                tasks.addDeadline(deadlineParse.getTaskName(), parser.getTaskTime1());
                System.out.println("\tGot it. I've added this task:\n\t\t" + tasks.getByIndex(tasks.getSize() - 1) + "\n\tNow you have " + tasks.getSize() + " tasks in the list.");
            } catch (DateTimeParseException e){
                System.out.println("\tThe input format for deadline event need to be \"deadline deadlineEvent /by dd/MM/yyyy HHmm\"");
            }
        } catch(IndexOutOfBoundsException e){
            System.out.println("\tOOPS!!! The deadline need to separated by \"/by\"");
        }
    }
}
