package tasks;

/**
 * Represents an 'Event' task in the Duke application.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /*
     * Initializes a new Event with the given description, start time, and end time.
     * 
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /*
     * Returns the start time of the event.
     * 
     * @return The start time of the event.
     */
    public String getFrom() {
        return this.from;
    }

    /*
     * Returns the end time of the event.
     * 
     * @return The end time of the event.
     */
    public String getTo() {
        return this.to;
    }

    /*
     * Converts the Event task object to a data representation for saving to a file.
     * 
     * @return The data representation of the Event task.
     */
    @Override
    public String toData() {
        String done = String.valueOf(this.isDone ? 1 : 0);
        return "E | " + done + " | " + this.description + " | " + this.from + " | " + this.to;
    }

    /*
     * Returns the string representation of the Event task.
     * 
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }

    /*
     * Returns a new Event task based on the given task data.
     * 
     * @param taskData The task data to be converted.
     * @return The new Event task.
     */
    public static Task dataToTask(String taskData) {
        int firstSplitIndex = taskData.indexOf("|");
        int secondSplitIndex = taskData.indexOf("|", firstSplitIndex + 1);
        int thirdSplitIndex = taskData.indexOf("|", secondSplitIndex + 1);
        boolean isDone = taskData.substring(0, firstSplitIndex - 1).equals("1");
        String desc = taskData.substring(firstSplitIndex + 2, secondSplitIndex - 1);
        String from = taskData.substring(secondSplitIndex + 2, thirdSplitIndex - 1);
        String to = taskData.substring(thirdSplitIndex + 2);
        Event newEvent =  new Event(desc, from, to);
        newEvent.isDone = isDone;
        return newEvent;
    }
}
