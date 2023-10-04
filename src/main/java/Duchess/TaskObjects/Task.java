package Duchess.TaskObjects;

import java.util.Scanner;

public class Task {
    
    /**Done condition and
     * Name to be accessed as task */
    private boolean isDone = false;
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

    public void replaceName(Scanner sc) {
        System.out.println("What would you like to change the name to?");
        String newName = sc.nextLine();
        this.setName(newName);
    }

    /** Marks as done */
    public void markAsDone(){
        this.isDone = true;
    }

    /** Marks as undone */
    public void markAsUndone(){
        this.isDone = false;
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
        return this.isDone;
    }

    /** Gets task as string
     * @return name Name of task
     */
    @Override
    public String toString(){
        if (this.isDone){
            return ("[X] " + this.name);
        } else {
            return ("[ ] " + this.name);
        }
    }

    public String toFileString() {
        if (this.isDone){
            return (" X | " + this.name);
        } else {
            return (" O | " + this.name);
        }
    }

    public Boolean contains(String keyword) {
        return this.name.contains(keyword);
    }

}
