public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        if(!by.contains("by ")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline must contain \"by\" time.");
        }else if(by.replace("by ", "").isBlank()) {
            throw new DukeException("☹ OOPS!!! The time of a deadline cannot be empty.");
        }
        this.by = by.replace("by ", "");
    }

    /**
     * Converts the Deadline object into a string.
     *
     * @return String format of the object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts the object into a string to be stored in file.
     *
     * @return String format of the object to be stored in file.
     */
    @Override
    public String toFile() {
        return "D | " + super.toFile() + " | " + this.by;
    }
}
