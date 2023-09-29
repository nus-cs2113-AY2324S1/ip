package task;

public class Deadline extends Task {

    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String getListText() {
        return "[D] " + super.getListText() + " (by: " + by + ")";
    }

    @Override
    public String getAddMessage() {
        return "Got it. I've added this task:\n" +
                "[D][ ] " + getName() + " (by: " + by + ")\n" +
                "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public String getDeleteMessage() {
        numberOfTasks--;
        return "Noted: I've removed this task:\n" +
                "[D][ ] " + getName() + " (by: " + by + ")\n" +
                "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public String getSaveString() {
        return "D" + super.getSaveString() + " | " + by;
    }

}
