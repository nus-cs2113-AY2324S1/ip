package Duchess.TaskObjects;

//Java collections class used here
import java.util.ArrayList;
import java.io.File;

import Duchess.FunctionObjects.FileHandler;

import Duchess.TextObjects.DefaultStrings;

import Duchess.ErrorObjects.FileNotFoundError;


public class TaskList {

    private ArrayList<Task> tasks;
    private FileHandler fileHandler;

    
    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    public void importTasks(String filepath) throws FileNotFoundError{
        try{
            fileHandler = new FileHandler(filepath);
            this.tasks = fileHandler.load();
        } catch (Exception e){
            throw new FileNotFoundError(DefaultStrings.fileNotFoundError);
        }
    }

    public void saveTasks(String filepath) throws FileNotFoundError{
        try{
            fileHandler = new FileHandler(filepath);
            fileHandler.save(this.tasks);
        } catch (Exception e){
            File file = new File(filepath);
            file.getParentFile().mkdirs();
            try {
                fileHandler = new FileHandler(filepath);
                fileHandler.save(this.tasks);
            } catch (Exception f){
                throw new FileNotFoundError(DefaultStrings.fileNotFoundError);
            }
        }
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
