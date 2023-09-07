public class Event extends Deadline {
    protected String startDate;
    protected String endDate;
//event project meeting /from Mon 2pm /to 4pm
    public Event(String description, String dates) {
        super(description, dates);
        setDates(dates);
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setDates(String dates) {
        int spaceCut = dates.indexOf(" ");
        dates = dates.substring(spaceCut + 1);

        int slashCut = dates.indexOf("/");
        startDate = dates.substring(0, slashCut);
        dates = dates.substring(slashCut + 1);

        spaceCut = dates.indexOf(" ");
        endDate = dates.substring(spaceCut + 1);
    }

    @Override
    public String toString() {
        //super.toString();
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[E]" + isDoneString + description + "(from: " + getStartDate() + "to: " + getEndDate() + ")";
    }
}
