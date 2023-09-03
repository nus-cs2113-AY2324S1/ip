public class Todo extends Task{
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (super.getDone()) {
            return "[T][X] " + super.getName();
        } else {
            return "[T][ ] " + super.getName();
        }
    }
}
