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
        for (int i = 1; i <= this.tasks.size(); i++){
            System.out.print(i + ". ");
            this.tasks.get(i-1).printTask();
        }
        System.out.println(DefaultStrings.splittingLine);
    }

    public void markTask(int taskNumber){
        this.tasks.get(taskNumber-1).markAsDone();
        System.out.println(DefaultStrings.markedString);
        System.out.print(taskNumber + ". ");
        this.tasks.get(taskNumber-1).printTask();
        System.out.println(DefaultStrings.splittingLine);
    }

    public void unmarkTask(int taskNumber){
        this.tasks.get(taskNumber-1).markAsUndone();
        System.out.println(DefaultStrings.unmarkedString);
        System.out.print(taskNumber + ". ");
        this.tasks.get(taskNumber-1).printTask();
        System.out.println(DefaultStrings.splittingLine);
    }

}
