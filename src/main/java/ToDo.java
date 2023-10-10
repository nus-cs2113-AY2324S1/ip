public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the format of the task to be printed out to user
     * @return String representation of the task
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the format of the task to be saved into a file
     * @return String representation of the task
     */
    @Override
    public String toSave() {
        return "T | " + super.toSave();
    }
}
