package Duchess.FunctionObjects;
import Duchess.TextObjects.DefaultStrings;
import Duchess.TaskObjects.TaskList;
import Duchess.ErrorObjects.DuchessError;
import Duchess.TaskObjects.Task;

import java.util.Scanner;


public class UI {

    Scanner sc;

    public UI(){
        this.sc = new Scanner(System.in);
    }

    public void printTask(Task task){
        System.out.println(DefaultStrings.addedString + task.toString());
        System.out.println(DefaultStrings.splittingLine);
    }

    public void printWelcome(){
        System.out.println(DefaultStrings.logo);
        System.out.println(DefaultStrings.splittingLine);
    }

    public void printList(TaskList taskList){
        System.out.println(DefaultStrings.listString);
        taskList.listTasks();
        System.out.println(DefaultStrings.splittingLine);
    }

    public void printError(DuchessError error){
        System.out.println(error.getMessage());
        System.out.println(DefaultStrings.splittingLine);
    }

    public void printGoodbye(){
        System.out.println(DefaultStrings.endString);
        sc.close();
    }

    public String getCommand(){
        return sc.nextLine();
    }

    public void printLine(){
        System.out.println(DefaultStrings.splittingLine);
    }
    
}
