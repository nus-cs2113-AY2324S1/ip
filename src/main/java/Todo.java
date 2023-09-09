public class Todo extends Task {
    public Todo (String taskName) {
        super(taskName);
    }

    @Override
    public void printTask() {
        System.out.println("\t[T]" + getCompletedString() + getDescription());
    }
}
