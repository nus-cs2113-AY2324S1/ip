package Task;

public class ToDo extends Task {

    public ToDo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the object into a string format to be stored in the file.
     * 
     * @return Formatted string to be stored in the file.
     */
    public String toFile() {
        return "T," + (isCompleted() ? "1" : "0") + "," + getName();
    }
}
