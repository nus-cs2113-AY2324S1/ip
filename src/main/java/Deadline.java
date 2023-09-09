import java.util.Arrays;

public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) {
        super(name);
        setBy(by);
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
