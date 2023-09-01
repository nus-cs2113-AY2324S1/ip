package Duchess;

import java.util.ArrayList;


public class TaskList {

    private ArrayList<Task> tasks;
    
    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public void listTasks(){
        System.out.print(DefaultStrings.splittingLine);
        for (int i = 0; i < this.tasks.size(); i++){
            System.out.println("\t" + i + ". " + this.tasks.get(i).getName());
        }
        System.out.println(DefaultStrings.splittingLine);
    }

}
