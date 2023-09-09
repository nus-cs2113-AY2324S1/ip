public class Event extends Task {
    protected String timePeriod;

    public Event(String description, String timePeriod) {
        super(description);
        this.timePeriod = timePeriod;
    }

    public static String[] handleInputForEvent(String userInput) {
        String usefulInput = userInput.replace("event", "").trim();
        String[] taskAndTimePeriod = usefulInput.split("/");
        String description = taskAndTimePeriod[0];
        String timePeriod = taskAndTimePeriod[1] + taskAndTimePeriod[2];
        if (timePeriod.startsWith("from")) {
            timePeriod = "(" + timePeriod.replace("from", "from:").replace("to", "to:") + ")";
        } else {
            timePeriod = "(from: " + timePeriod.replace("to", "to:") + ")";
        }
        return new String[] {description, timePeriod};
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + timePeriod;
    }

    public static void main(String[] args) {
        handleInputForEvent("event project meeting /Mon 2pm /to 4pm");
    }
}
