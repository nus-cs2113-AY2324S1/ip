package nupjuk.command;

import nupjuk.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import static nupjuk.Printer.printLine;

/**
 * EventCommand class
 * get commands starts with "Event" and execute it
 */
public class EventCommand {
    public boolean execute(TaskList tasks, String[] tokens, Storage storage) throws IOException {
        // error handling
        try {
            FormatChecker.checkInputFormat(tokens);
        } catch (InputFormatException e) {
            printLine("☹ OOPS!!! <event> should be with task description and start/end time");
            System.out.println("    ____________________________________________________________\n");
            return false;
        }

        // input format: description / deadline
        String[] schedules = tokens[1].split("/", 3);

        // error handling
        try {
            FormatChecker.checkEventFormat(schedules);
        } catch (InputFormatException e) {
            printLine("☹ OOPS!!! <event> needs input like (work /from start /to end)");
            printLine("time must be this form: dd-MM-yyyy HHmm");
            System.out.println("    ____________________________________________________________\n");
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm", Locale.ENGLISH);
        LocalDateTime from;
        LocalDateTime to;
        // using datetime format
        try {
            from = LocalDateTime.parse(schedules[1].trim().substring(4).trim(), formatter);
            to = LocalDateTime.parse(schedules[2].trim().substring(2).trim(), formatter);
        } catch (DateTimeParseException e) {
            printLine("☹ OOPS!!! time must be this form: dd-MM-yyyy HHmm");
            System.out.println("    ____________________________________________________________\n");
            return false;
        }

        // make and add to list
        printLine("Got it. I've added this task:");

        Event event = new Event(schedules[0].trim(), from, to);
        tasks.addTask(event);

        printLine(String.format("  [%s][%s] %s",
                event.getTypeIcon(), event.getStatusIcon(), event.getDescription()));
        printLine(String.format("Now you have %d tasks in the list.", tasks.getSize()));
        System.out.println("    ____________________________________________________________\n");
        storage.saveTask(tasks);
        return false;
    }
}

