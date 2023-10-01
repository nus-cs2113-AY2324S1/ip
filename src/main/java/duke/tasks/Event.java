package duke.tasks;

import duke.exceptions.NullValidInputException;

/**
 * A subclass extends Task that handles the command "event"
 */
public class Event extends Task {
    protected String timePeriod;

    public Event(String description, String timePeriod) {
        super(description);
        this.timePeriod = timePeriod;
    }

    /**
     * Override the toString() method of the superclass,
     * so it can return its own description
     * @return the description of event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + timePeriod;
    }
}
