package luke.tasks;
import luke.errors.LukeTimeError;

public class Event extends Task {
    protected String startDate;
    protected String endDate;

    public Event(String echo) throws LukeTimeError {
        super(echo);
        //setDates(dates);
        String taskDescription = echo.substring(5);

        int slashCut = taskDescription.indexOf("/");
        if (slashCut <= 0) {
            throw new LukeTimeError();
        }

        String taskDuration = taskDescription.substring(slashCut + 1);
        setDates(taskDuration);

        description = taskDescription.substring(0, slashCut);

        if (description.length() <= 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setDates(String dates) throws LukeTimeError {
        String[] words = dates.split(" ");
        if (!words[0].equals("from")) {
            throw new LukeTimeError();
        }

        dates = dates.substring(5);

        int slashCut = dates.indexOf("/");
        if (slashCut <= 0) {
            throw new LukeTimeError();
        }

        startDate = dates.substring(0, slashCut);
        dates = dates.substring(slashCut + 1);

        words = dates.split(" ");
        if (!words[0].equals("to")) {
            throw new LukeTimeError();
        }

        endDate = dates.substring(3);
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

    @Override
    public String memoryString() {
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[E]" + isDoneString + " event" + getDescription() + "/from " + getStartDate() + "/to " + getEndDate();
    }
}
