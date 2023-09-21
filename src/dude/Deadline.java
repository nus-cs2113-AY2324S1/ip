package dude;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "[D]";
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + by;
    }

    public static Deadline fromFileFormat(String fileString) {
        String[] parts = fileString.split("\\s\\|\\s");
        Deadline deadline = new Deadline(parts[2], parts[3]);
        if (parts[1].equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
