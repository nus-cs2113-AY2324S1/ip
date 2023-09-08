
public class Deadline extends Task {
    private String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    // appends "[D]" to the beginning of the string.
    // Then, it calls super.toString(), ie. it calls the toString() method of the superclass
    // then adds the due date
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.due + ")";
    }
}
