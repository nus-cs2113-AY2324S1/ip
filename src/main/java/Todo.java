public class Todo extends Task{
    public Todo (String description) {
        super(description);
        Task.numberOfTasks ++;

    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + "[T][" + getStatusIcon() + "]" + description
                + System.lineSeparator() + "Now you have " + getNumberOfTasks() + " tasks in the list.";
    }
}
