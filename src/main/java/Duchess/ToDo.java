package Duchess;

public class ToDo extends Task {
    
    public ToDo() {
        super();
    }

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}    