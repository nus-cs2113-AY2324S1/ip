public class Deadline extends Task {

    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String addMessage() {
        return "Got it. I've added this task:\n" +
                "[D][ ] borrow book (by: " + by + ")\n" +
                "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public String listText() {
        return "[D] " + super.listText() + "(by: " + by + ")";
    }
}
