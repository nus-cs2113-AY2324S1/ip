public class Todo extends Task {
    public static Todo parseTodo(String arg) {
        return new Todo(arg);
    }

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
