public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String addMessage() {
        return "Got it. I've added this task:\n" +
                "[T][ ] borrow book\n" +
                "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public String listText() {
        return "[T] " + super.listText();
    }
}
