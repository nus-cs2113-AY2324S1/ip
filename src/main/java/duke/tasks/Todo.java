package duke.tasks;

//Create a todo class that inherits from the task class
public class Todo extends Task {
    public Todo(String taskName){
        super(taskName);
    }
    //toString method to print the status of the task followed by the task name
    public String toString(){
        if(this.isDone()){
            return "[T][X] " + this.taskName;
        }else{
            return "[T][ ] " + this.taskName;
        }
    }
}