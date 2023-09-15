package task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String getListText() {
        return "[T] " + super.getListText();
    }

    @Override
    public String getAddMessage() {
        return "Got it. I've added this task:\n" +
                "[T][ ] " + getName() + "\n" +
                "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public String getSaveString() {
        return "T" + super.getSaveString();
    }

}
