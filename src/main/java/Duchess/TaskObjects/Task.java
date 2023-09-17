package Duchess.TaskObjects;

public class Task {
    
    /**Done condition and
     * Name to be accessed as task */
    private boolean done = false;
    private String name = "";

    /** Constructor class to be declared */
    public Task(){
    }

    /** Constructor class to be declared
     * @param name Name of task to be set
     */

    public Task(String name){
        this.name = name;
    }

    /** Sets name
     * @param name Name of task to be set
    */
    public void setName(String name){
        this.name = name;
    }

    /** Marks as done */
    public void markAsDone(){
        this.done = true;
    }

    /** Marks as undone */
    public void markAsUndone(){
        this.done = false;
    }

    /** Gets name
     * @return name Name of task
     */
    public String getName(){
        return this.name;
    }

    /** Gets done condition
     * @return done Done condition of task
     */
    public boolean getDone(){
        return this.done;
    }

    /** Gets task as string
     * @return name Name of task
     */
    @Override
    public String toString(){
        if (this.done){
            return ("[X] " + this.name);
        } else {
            return ("[ ] " + this.name);
        }
    }
}
