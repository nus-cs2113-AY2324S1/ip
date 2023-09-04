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
            System.out.println(i + ". " + this.tasks.get(i-1).toString());
        }
        System.out.println(DefaultStrings.splittingLine);
    }

    public void markTask(int taskNumber){
        this.tasks.get(taskNumber-1).markAsDone();
        printTaskSkeleton(taskNumber, DefaultStrings.markedString);
    }

    public void unmarkTask(int taskNumber){
        this.tasks.get(taskNumber-1).markAsUndone();
        printTaskSkeleton(taskNumber, DefaultStrings.unmarkedString);
    }

    private void printTaskSkeleton(int taskNumber, String isMarkedString){
        System.out.println(isMarkedString + taskNumber + ". "
                + "\n" + this.tasks.get(taskNumber-1).toString()
                + "\n" + DefaultStrings.splittingLine);
    }

}
