package commands;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.isDone = false;
        this.type = 'T';
    }

}
