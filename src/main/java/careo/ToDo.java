package careo;


public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns toString of the superclass and adds that it is of type ToDo.
     *
     * @return A string representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}