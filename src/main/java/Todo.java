public class Todo extends Task{
    Todo(String description){
        super(description);
    }
    public String toString() {
        return "[T]" + super.toString();
    }
}
