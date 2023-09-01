package Duchess;

public class Task {
    
    /**Done condition and
     * Name to be accessed as task */
    private boolean done = false;
    private String name = "";

    /** Constructor class to be declared */
    public Task(){
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

    /** Prints task with status */
    public void printTask(){
        if (this.done){
            System.out.println("[X] " + this.name);
        } else {
            System.out.println("[ ] " + this.name);
        }
    }
}
