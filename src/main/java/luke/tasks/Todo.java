package luke.tasks;

public class Todo extends Task {
    //protected boolean isDone;
    protected String todoGuide = "\ttodo <description>";

    public Todo(String description) {
        super(description); //ensures superclass is properly initialised

        if (description.isEmpty()) {
            System.out.println("\tThere is a missing task description. Please follow this format:");
            printGuide();
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void printGuide() {
        System.out.println(todoGuide);
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

        return "\t[T]" + isDoneString + " " + getDescription();
    }

    @Override
    public String memoryString() {
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[T]" + isDoneString + " " + getDescription();
    }

}
