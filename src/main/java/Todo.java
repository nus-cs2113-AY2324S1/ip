public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void printAddedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
    }
}
