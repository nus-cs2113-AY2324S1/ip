package task;

public class Event extends Task {

    protected String startDate;
    protected String endDate;

    /**
     * Constructor for event
     * @param description Task description
     * @param startDate start date, in 11PM/DD/MM/YY
     * @param endDate end date, in 11PM/DD/MM/YY
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
