package task;

import commandFormat.TimeParser;
import exception.OrientoException;
import exception.InvalidTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String start, end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * extreact start and end from userCommand first
     * then test if the input is valid
     * if failed to parse input, assume the user is true
     * @InvalidTimeException caught in EventCommand class
     */
    public static Event newEventTask(String userCommand) throws OrientoException, InvalidTimeException {
        //command format: event project meeting /from Mon 2pm /to 4pm
        if (!(userCommand.contains("/from")) || !(userCommand.contains("/to"))){
            throw new OrientoException("Oh, no! There is no '/from' or '/to' ");
        }
        userCommand = userCommand.substring(6);  //remove "event"

        if(userCommand.startsWith("/from")){
            throw new OrientoException("Cannot find event description. Please try again.");
        }

        int indexOfFrom = userCommand.indexOf("/from");
        int indexOfTo = userCommand.indexOf("/to");
        String start = userCommand.substring(indexOfFrom + 6, indexOfTo).trim();
        String end = userCommand.substring(indexOfTo + 4).trim();
        String eventTask = userCommand.substring(0, indexOfFrom).trim();
        testTimeInput(start, end);

        return new Event(eventTask, start, end);
    }

    /**
     * test if the user input period for event is valid
     * @param startTime the starting time of event
     * @param endTime the end time of event
     * @throws InvalidTimeException if the input time is invalid, which could happen in the following cases:
     * 1. end time occurs before start time
     * 2. end time is over already
     */
    private static void testTimeInput(String startTime, String endTime) throws InvalidTimeException {
        LocalDateTime now = LocalDateTime.now();
        try{
            LocalDateTime start = TimeParser.parseDateTime(startTime);
            LocalDateTime end = TimeParser.parseDateTime(endTime);
            if(end.isBefore(now)){
                throw new InvalidTimeException("The event is ended. Please check the end time again.");
            }
            if(end.isBefore(start)){
                throw new InvalidTimeException("End time is early than start time.");
            }
        } catch(DateTimeParseException d) {
            /* do nothing */
        }
    }

    /**
     * print example: [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
     * @return string containing event task information
     */
    @Override
    public String toString() {
        try {
            LocalDateTime  tmp = TimeParser.parseDateTime(this.start);
            String startTime = TimeParser.convertDateTimetoString(tmp);
            tmp = TimeParser.parseDateTime(this.end);
            String endTime = TimeParser.convertDateTimetoString(tmp);
            return "[E]" + super.toString() + " (from: " + startTime +  " to: " + endTime + ")";
        } catch (Exception e) {
            return "[E]" + super.toString() + " (from: " + this.start +  " to: " + this.end + ")";
        }
    }

}
