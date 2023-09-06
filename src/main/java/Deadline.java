import java.util.Arrays;

public class Deadline extends Task {
    private String by;

    public static Deadline parseDeadline(String cmdBody) {
        String[] args = cmdBody.split(" +/by +");
        String name = args[0];
        String by = args[1];

        return new Deadline(name, by);
    }

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), getBy());
    }
}
