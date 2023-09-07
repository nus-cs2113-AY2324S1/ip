/* todo class
extends Task class
tasks without any date/time attached to it
 */
public class Todo extends Task{
    public Todo(String description){
        super(description);
    }

    public String getTypeIcon(){ return "T"; }
}
