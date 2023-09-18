package Duchess.TaskObjects;

import java.util.ArrayList;

import Duchess.TextObjects.DefaultStrings;


public class TaskList {

    private ArrayList<Task> tasks;
    
    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task){
        this.tasks.add(task);
        System.out.println(DefaultStrings.splittingLine
                + "\n" + DefaultStrings.addedString
                + "\n" + task.toString()
                + "\n" + DefaultStrings.splittingLine);
        this.displayNumTasks();
    }

    public void deleteTask(int taskNum){
        try{
            Task task = this.tasks.get(taskNum-1);
            this.tasks.remove(taskNum-1);
            System.out.println(DefaultStrings.splittingLine
                    + "\n" + DefaultStrings.deletedString
                    + "\n\t " + task.toString()
                    + "\n" + DefaultStrings.splittingLine);
            this.displayNumTasks();
        } catch (IndexOutOfBoundsException e){
            System.out.println(DefaultStrings.splittingLine
                    + "\n" + DefaultStrings.invalidTaskString
                    + "\n" + DefaultStrings.splittingLine);
        }
    }

    public void displayNumTasks(){
        System.out.println(DefaultStrings.splittingLine
                + "\n" + DefaultStrings.numTasksStringStart + this.tasks.size()
                + DefaultStrings.numTasksStringEnd
                + "\n" + DefaultStrings.splittingLine);
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
