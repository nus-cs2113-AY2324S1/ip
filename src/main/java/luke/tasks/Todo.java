package luke.tasks;

public class Todo extends Task {
    //protected boolean isDone;

    public Todo(String echo) {
        super(echo); //ensures superclass is properly initialised

        description = echo.substring(4);
        if (description.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
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

        return isDoneString + " todo " + getDescription();
    }

}
