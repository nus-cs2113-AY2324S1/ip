package Task;

public class ToDo extends Task {

    public ToDo(String name){
        super(name);
    }
    public ToDo(String name, boolean done){
        super(name);
        this.done = done;
    }

    @Override
    public String toString(){
        String out = "[T]["
                + (done ? "X" : " ")
                + "] " + super.name;
        return out;
    }

    @Override
    public String toFileLine(){
        return super.toFileLine() + " /TODO";
    }
}
