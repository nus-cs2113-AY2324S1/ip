public class Todo extends Task{

    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[T]" + super.toString();
        } else {
            return "[T]" + super.toString();
        }
    }
}
