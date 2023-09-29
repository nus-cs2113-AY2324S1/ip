package task;

public class Deadline extends Task {

    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String getListText() {
        return "[D] " + super.getListText() + " (by: " + parseDate(by) + ")";
    }

    @Override
    public String getAddMessage() {
        return "Got it. I've added this task:\n" +
                "[D][" + (getIsComplete() ? "X" : " ") + "] " +
                getName() + " (by: " + parseDate(by) + ")\n" +
                "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public String getDeleteMessage() {
        numberOfTasks--;
        return "Noted: I've removed this task:\n" +
                "[D][" + (getIsComplete() ? "X" : " ") + "] " +
                getName() + " (by: " + parseDate(by) + ")\n" +
                "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public String getSaveString() {
        return "D" + super.getSaveString() + " | " + by;
    }

}
