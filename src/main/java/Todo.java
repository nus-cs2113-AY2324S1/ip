public class Todo extends Task{
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (super.getDone()) {
            return "[T][X] " + super.toString();
        } else {
            return "[T][ ] " + super.toString();
        }
    }
}
