package chat0pt.commands;

public class Todo extends Task {
    public Todo(String addtask) {
        super(addtask);
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public String toFile(){
        return "T," + super.toFile();
    }
}
