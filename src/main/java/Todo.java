public class Todo extends Task{
    public Todo(String tasks) {
        super(tasks);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
