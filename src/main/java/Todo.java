public class Todo extends Task {
    //protected boolean isDone;

    public Todo(String description) {
        super(description);
        //isDone = false;
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

        return "[T]" + isDoneString + description;
    }
}
