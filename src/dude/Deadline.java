package dude;

/**
 * The `Deadline` class represents a task with a deadline, which has a description and a due date (by).
 * It extends the `Task` class and provides additional fields and methods specific to deadline tasks.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructs a new `Deadline` task with the specified description and due date (by).
     *
     * @param description The description of the deadline task.
     * @param by          The due date of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "[D]";
    }

    /**
     * Converts the deadline task to a formatted string for saving to a file.
     *
     * @return A string representation of the deadline task in file format.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + by;
    }

    /**
     * Creates a `Deadline` object from a string in file format.
     *
     * @param fileString A string containing the deadline task details in file format.
     * @return A `Deadline` object parsed from the file string.
     */
    public static Deadline fromFileFormat(String fileString) {
        String[] parts = fileString.split("\\s\\|\\s");
        Deadline deadline = new Deadline(parts[2], parts[3]);
        if (parts[1].equals("1")) {
            deadline.setDone(deadline.isDone);
        }
        return deadline;
    }

    /**
     * Generates a string representation of the deadline task, including its description and due date.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
