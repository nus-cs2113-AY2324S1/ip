package nupjuk.command;

import nupjuk.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import static nupjuk.Printer.printLine;

/**
 * DeadlineCommand class
 * get commands starts with "deadline" and execute it
 */

public class DeadlineCommand {
    public boolean execute(TaskList tasks, String[] tokens, Storage storage) throws IOException {
        // error handling
        try {
            FormatChecker.checkInputFormat(tokens);
        } catch (InputFormatException e) {
            printLine("☹ OOPS!!! <deadline> should be with task description and deadline");
            System.out.println("    ____________________________________________________________\n");
            return false;
        }

        // input format: description / deadline
        String[] schedules = tokens[1].split("/", 2);

        // error handling
        try {
            FormatChecker.checkDeadlineFormat(schedules);
        } catch (InputFormatException e) {
            printLine("☹ OOPS!!! <deadline> needs argument like (work/by time)");
            System.out.println("    ____________________________________________________________\n");
            return false;
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm", Locale.ENGLISH);
        LocalDateTime date;
        // using datetime format
        try {
            date = LocalDateTime.parse(schedules[1].trim().substring(2).trim(), formatter);
        } catch (DateTimeParseException e) {
            printLine("☹ OOPS!!! time must be this form: dd-MM-yyyy hhmm");
            System.out.println("    ____________________________________________________________\n");
            return false;
        }


        // make and add to list
        printLine("Got it. I've added this task:");

        Deadline deadline = new Deadline(schedules[0].trim(), date);
        tasks.addTask(deadline);

        printLine(String.format("  [%s][%s] %s",
                deadline.getTypeIcon(), deadline.getStatusIcon(), deadline.getDescription()));
        printLine(String.format("Now you have %d tasks in the list.", tasks.getSize()));
        System.out.println("    ____________________________________________________________\n");
        storage.saveTask(tasks);
        return false;
    }
}
