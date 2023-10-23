package Duchess.FunctionObjects;
import Duchess.TextObjects.DefaultStrings;
import Duchess.TaskObjects.TaskList;
import Duchess.ErrorObjects.DuchessError;
import Duchess.TaskObjects.Task;

import java.util.Scanner;

/** Class to handle UI and display requirements, outputs, and everything. */
public class UI {

    Scanner sc;

    /** Constructor class to be declared. */
    public UI(){
        this.sc = new Scanner(System.in);
    }

    /** Prints out task information.
     * @param task Task to be printed.
     */
    public void printTask(Task task){
        System.out.println(DefaultStrings.addedString + task.toString());
        System.out.println(DefaultStrings.splittingLine);
    }

    /** Prints out welcome message. */
    public void printWelcome(){
        System.out.println(DefaultStrings.logo);
        System.out.println(DefaultStrings.splittingLine);
    }

    /** Prints out list of tasks.
     * @param taskList TaskList to be printed.
     */
    public void printList(TaskList taskList){
        System.out.println(DefaultStrings.listString);
        taskList.listTasks();
        System.out.println(DefaultStrings.splittingLine);
    }

    /** Prints out error message.
     * @param error Error to be printed.
     */
    public void printError(DuchessError error){
        System.out.println(error.getMessage());
        System.out.println(DefaultStrings.splittingLine);
    }

    /** Prints out goodbye message. */
    public void printGoodbye(){
        System.out.println(DefaultStrings.endString);
        sc.close();
    }

    /** Gets next command. */
    public String getCommand(){
        return sc.nextLine();
    }

    /** Prints out line. */
    public void printLine(){
        System.out.println(DefaultStrings.splittingLine);
    }
    
}
