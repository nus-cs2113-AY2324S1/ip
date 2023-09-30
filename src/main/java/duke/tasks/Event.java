package duke.tasks;

import duke.exceptions.NullValidInputException;

public class Event extends Task {
    protected String timePeriod;

    public Event(String description, String timePeriod) {
        super(description);
        this.timePeriod = timePeriod;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + timePeriod;
    }
}
