package alice.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String typeOfTask = "[T]";
        return typeOfTask + super.toString();
    }

    /**
     * Encodes the todo task into a string to be stored in the text file
     *
     * @return string in the format to be stored in text file
     */
    @Override
    public String encode() {
        return String.format("Todo | %s", super.encode());
    }
}