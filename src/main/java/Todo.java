public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType(){
        return "[T]";
    }
}