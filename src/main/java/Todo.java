public class Todo extends Task{
    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + "[T][" + getStatusIcon() + "]" + description
                + System.lineSeparator() + "Now you have " + numberOfTasks + " tasks in the list.";
    }
}
