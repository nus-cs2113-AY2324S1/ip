public class Event extends Deadline {
    protected String startDate;
    protected String endDate;
//event project meeting /from Mon 2pm /to 4pm
    public Event(String description, String dates) {
        super(description, dates);

        int spaceCut = dates.indexOf(" ");
        dates = dates.substring(spaceCut + 1);

        int slashCut = dates.indexOf("/");
        startDate = dates.substring(0, slashCut);
        dates = dates.substring(slashCut + 1);

        spaceCut = dates.indexOf(" ");
        endDate = dates.substring(spaceCut + 1);
/*
        int spaceCut = taskDeadline.indexOf(" ");
        taskDeadline = taskDeadline.substring(spaceCut + 1);
        //this.dates = dates;

 */
    }

    public String getBy() {
        return startDate;
    }

    public void setBy(String by) {
        this.startDate = by;
    }

    @Override
    public String toString() {
        super.toString();
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[E]" + isDoneString + description + "(from: " + startDate + "to: " + endDate + ")";
    }
}
