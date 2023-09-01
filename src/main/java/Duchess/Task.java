package Duchess;

public class Task {
    
    /**Name to be accessed as task */
    private String name = "";

    /** Constructor class to be declared. */
    public Task(){
    }

    /** Sets name
     * @param name Name of task to be set
    */
    public void setName(String name){
        this.name = name;
    }

    /** Gets name
     * @return name Name of task
     */
    public String getName(){
        return this.name;
    }
}
