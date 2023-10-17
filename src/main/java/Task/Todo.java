package Task;

/**
 * class indicating an instance of a todo object, extending task class
 */
public class Todo extends Task{

    /**
     * constructor of "Todo"
     * @param taskName name of task
     */
    public Todo(String taskName){
        super(taskName);
    }

    /**
     * returns string representation of todo task
     * @return a string representation of the task
     */
    @Override
    public String toString(){
        return "[T] " + super.toString();
    }
}
