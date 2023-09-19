package duke;

import duke.Task;

public class Todo extends Task {

    // Constructor
    public Todo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString(){
        return ("T | " + super.toFileString());
    }
}
