public class ToDo extends Task{
    public ToDo(String description, boolean isDone){
        super(description);
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}

