package Chatty.Command;
import Chatty.tasks.*;
import Chatty.TaskList;
import Chatty.Storage;
import Chatty.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class printDate extends Command{

    public printDate(String input) {
        super(input);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage){
        String dateToPrint = input.substring(10).trim();
        LocalDate specifiedDate = LocalDate.parse(dateToPrint, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Tasks on " + specifiedDate + ":");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline || tasks.get(i) instanceof Event) {
                LocalDate taskDate = (tasks.get(i) instanceof Deadline) ? ((Deadline) tasks.get(i)).getBy() : ((Event) tasks.get(i)).getFrom();
                if (taskDate.equals(specifiedDate)) {
                    System.out.println(tasks.get(i).getDescription());
                }
            }
        }
    }

}
