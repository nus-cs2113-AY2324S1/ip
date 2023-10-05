package luke.tasks;
import luke.user.LukeTimeError;

public class Event extends Task {
    protected String startDate;
    protected String endDate;
    protected String eventGuide = "\tevent <description> /from <start date> /to <end date>";

    public Event(String taskDescription) throws LukeTimeError {
        super(taskDescription);

        int slashCut = taskDescription.indexOf("/");
        if (slashCut <= 0) {
            System.out.println("\tThere is a missing task description. Please follow this format:");
            printGuide();
            throw new LukeTimeError();
        }

        description = taskDescription.substring(0, slashCut);

        if (description.length() <= 1) {
            throw new IndexOutOfBoundsException();
        }

        String taskDuration = taskDescription.substring(slashCut + 1);
        setDates(taskDuration);
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
            System.out.println("\tThere is a syntax problem. Please follow this format:");
            printGuide();
            throw new LukeTimeError();
        }

        dates = dates.substring(5);

        int slashCut = dates.indexOf("/");
        if (slashCut <= 0) {
            System.out.println("\tThere is a syntax problem. Please follow this format:");
            printGuide();
            throw new LukeTimeError();
        }

        startDate = dates.substring(0, slashCut);
        dates = dates.substring(slashCut + 1);

        words = dates.split(" ");
        if (!words[0].equals("to")) {
            System.out.println("\tThere is a syntax problem. Please follow this format:");
            printGuide();
            throw new LukeTimeError();
        }

        endDate = dates.substring(3);
    }

    /*
    @Override
    public String getType() {
        return "event";
    }

     */

    @Override
    public void printGuide() {
        System.out.println(eventGuide);
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

        return "\t[E]" + isDoneString + " " + getDescription() + "(from: " + getStartDate() + "to: " + getEndDate() + ")";
    }

    @Override
    public String memoryString() {
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[E]" + isDoneString + getDescription() + "/from " + getStartDate() + "/to " + getEndDate();
    }
}
