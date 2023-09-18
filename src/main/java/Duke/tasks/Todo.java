package Duke.tasks;

import Duke.Task;

public class Todo extends Task {
    public Todo(String description){
        super(description);
    }
    public String toString() {
        return "[T]" + super.toString();
    }
    public String getEventTime(){
        return "";
    }
}
