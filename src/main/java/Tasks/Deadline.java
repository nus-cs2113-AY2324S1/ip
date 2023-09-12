package Tasks;

public class Deadline extends Task {

    protected static String time;

    public Deadline(String description, String time) {
        super(description);
        Deadline.time = time;
    }

    public static String getDescription() {
        return description;
    }

    public static String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
