public class ToDo extends Task {

    public ToDo(String name){
        super(name);
    }

    @Override
    public String toString(){
        String out = "[T]["
                + (done ? "X" : " ")
                + "] " + super.name;
        return out;
    }
}
