package luke.tasks;

public class Todo extends Task {
    //protected boolean isDone;

    public Todo(String description) {
        super(description); //ensures superclass is properly initialised

        if (description.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String getType() {
        return "todo";
    }

    @Override
    public String toString() {
        //super.toString();
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "\t[T]" + isDoneString + getDescription();
    }

    @Override
    public String memoryString() {
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[T]" + isDoneString + " todo" + getDescription();
    }

}
