public class Event extends Deadline {
    protected String startDate;
    protected String endDate;

    public Event(String echo) {
        super(echo);
        //setDates(dates);
        String taskDescription = echo.substring(5);
        int slashCut = taskDescription.indexOf("/");
        String taskDuration = taskDescription.substring(slashCut);
        setDates(taskDuration);
        description = taskDescription.substring(0, slashCut);

        if (description.length() <= 0) {
            throw new IndexOutOfBoundsException();
        }
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

        return "\t[E]" + isDoneString + getDescription() + "(from: " + getStartDate() + "to: " + getEndDate() + ")";
    }
}
