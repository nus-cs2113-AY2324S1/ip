public class Todo extends Task{
    public Todo (String description) {
        super(description);
        Task.numberOfTasks ++;

    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + "[T][" + super.getStatusIcon() + "]" + description
                + System.lineSeparator() + "Now you have " + super.getNumberOfTasks() + " tasks in the list.";
    }
}
