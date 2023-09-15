public class Event extends Task {
    protected String start, end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public static Event newEventTask(String userCommand) {
        //command format: event project meeting /from Mon 2pm /to 4pm
        String[] eventSplit = userCommand.split("/");
        int spaceIndex = eventSplit[0].indexOf(" ");
        String eventTask = eventSplit[0].substring(spaceIndex + 1).trim();
        String start = eventSplit[1].trim().substring(5); // Remove "/from " prefix
        String end = eventSplit[2].trim().substring(3); // Remove "/to " prefix
        return new Event(eventTask, start, end);
    }


    @Override
    public String toString() {
        //print example: [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
        return "[E]" + super.toString() + " (from: " + this.start +  " to: " + this.end + ")";
    }

}
