public class Todo extends Task {
    //protected boolean isDone;

    public Todo(String echo) {
        super(echo);
        //isDone = false;
        description = echo.substring(4);
        if (description.length() <= 0) {
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
}
