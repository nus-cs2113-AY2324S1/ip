public class ToDos extends Task{

    public ToDos(String toDoName) {
        super(toDoName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
