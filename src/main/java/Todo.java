public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    // It appends "[T]" to the beginning of the string.
    //  Then, it calls super.toString(), which means it calls the toString() method of the superclass
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
