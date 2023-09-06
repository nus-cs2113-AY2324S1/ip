public class Todo extends Task {
    public static Todo parseTodo(String cmdBody) {
        return new Todo(cmdBody);
    }

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
