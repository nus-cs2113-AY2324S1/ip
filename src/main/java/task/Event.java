package task;

import commandFormat.TimeParser;
import exception.DukeException;

import java.time.LocalDateTime;

public class Event extends Task {
    protected String start, end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public static Event newEventTask(String userCommand) throws DukeException {
        //command format: event project meeting /from Mon 2pm /to 4pm
        if (!(userCommand.contains("/from")) || !(userCommand.contains("/to"))){
            throw new DukeException("Oh, no! There is no '/from' or '/to' ");
        }
        userCommand = userCommand.substring(6);  //remove "event"
        int indexOfFrom = userCommand.indexOf("/from");
        int indexOfTo = userCommand.indexOf("/to");
        String start = userCommand.substring(indexOfFrom + 6, indexOfTo).trim();
        String end = userCommand.substring(indexOfTo + 4).trim();
        String eventTask = userCommand.substring(0, indexOfFrom).trim();
        return new Event(eventTask, start, end);
    }


    @Override
    public String toString() {
        //print example: [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
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
