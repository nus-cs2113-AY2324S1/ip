package Duke.Task;

public class ToDo extends Task {
    public ToDo(String description){
        super(description);
    }
    @Override
    public String toString(){
        return "\t[T]" + super.toString();
    }
}
