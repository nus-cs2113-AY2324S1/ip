public class Deadline extends Task {

    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        if (super.getDone()) {
            return "[T][X] " + super.getName();
        } else {
            return "[T][ ] " + super.getName();
        }
    }
}