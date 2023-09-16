package duke.commands;

import duke.tasks.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ToDoList {
    private ArrayList<Task> taskList;
    private String[] markedTask;
    public String checker;
    public String marker;
    public String unmarked;
    public String toDo;

    public String delete;

    public ToDoList(){
        taskList = new ArrayList<Task>();
        markedTask = new String[100];
        checker = "list";
        marker = "mark";
        unmarked = "unmark";
        toDo = "todo";
        delete = "delete";
    }

    public void listTask(){
        System.out.println("Here are the tasks in your list");
        for(int j = 0; j < taskList.size(); j += 1) {

            if (taskList.get(j).taskType[0] == "T") {
                System.out.println(j + 1 + "." + Arrays.toString(taskList.get(j).taskType) +
                        Arrays.toString(taskList.get(j).markAsDone) + " " + taskList.get(j).toBeDone);
            }
            else if(taskList.get(j).taskType[0] == "D"){
                System.out.println(j + 1 + "." + Arrays.toString(taskList.get(j).taskType) +
                        Arrays.toString(taskList.get(j).markAsDone)
                        + " " + taskList.get(j).toBeDone
                        + " (by: " + taskList.get(j).dueDate + ")");
            }
            else if (taskList.get(j).taskType[0] == "E"){
                System.out.println(j + 1 + "." + Arrays.toString(taskList.get(j).taskType) +
                        Arrays.toString(taskList.get(j).markAsDone) + " " + taskList.get(j).toBeDone
                        + " (from: " + taskList.get(j).startTime
                        + " to: "+ taskList.get(j).endTime + ")");
            }
        }
    }
    public void mark(String input){
        if(input.startsWith("mark")) {
            int dividerPosition = input.indexOf(" ");
            String taskNumberString = input.substring(dividerPosition + 1);
            int taskNumber = Integer.parseInt(taskNumberString.trim());
            Task taskToBeMarked = taskList.get(taskNumber - 1);
            taskToBeMarked.setDone();
            System.out.println("Task Completed!");
            System.out.println(Arrays.toString(taskToBeMarked.markAsDone) + " " + taskToBeMarked.toBeDone);
        }
    }
    public void unmark(String input){
        if(input.startsWith("unmark")){
            int dividerPosition = input.indexOf(" ");
            String taskNumberString = input.substring(dividerPosition + 1);
            int taskNumber = Integer.parseInt(taskNumberString);
            Task taskToBeUnmarked = taskList.get(taskNumber - 1);
            taskToBeUnmarked.setNotDone();
            System.out.println("Task marked as uncompleted!");
            System.out.println(Arrays.toString(taskToBeUnmarked.markAsDone)+ " " + taskToBeUnmarked.toBeDone);
        }
    }
    public void addToDo(String incomingTask) {
        ToDos newToDo = new ToDos(incomingTask);
        taskList.add(newToDo);
        System.out.println("added: " + newToDo.toBeDone);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void addDeadline(String incomingTask, String deadline) {
        Deadline newDeadline = new Deadline(incomingTask,deadline);
        taskList.add(newDeadline);
        System.out.println("added: " + newDeadline.toBeDone + " (by: " + deadline +")");
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void addEvent(String incomingTask, String starting, String ending){
        Event newEvent = new Event(incomingTask,starting,ending);
        taskList.add(newEvent);
        System.out.println("added: " + newEvent.toBeDone + " (from: " + starting +" to:" + ending + ")");
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

    }

    public void removeTask(String input){
        int dividerPosition = input.indexOf(" ");
        String taskNumberString = input.substring(dividerPosition + 1);
        int taskNumber = Integer.parseInt(taskNumberString);

        System.out.println("Task Removed");

        if (taskList.get(taskNumber-1).taskType[0] == "T") {
            System.out.println(Arrays.toString(taskList.get(taskNumber-1).taskType) +
                    Arrays.toString(taskList.get(taskNumber-1).markAsDone) + " " + taskList.get(taskNumber-1).toBeDone);
        }
        else if(taskList.get(taskNumber-1).taskType[0] == "D"){
            System.out.println(Arrays.toString(taskList.get(taskNumber-1).taskType) +
                    Arrays.toString(taskList.get(taskNumber-1).markAsDone)
                    + " " + taskList.get(taskNumber-1).toBeDone
                    + " (by: " + taskList.get(taskNumber-1).dueDate + ")");
        }
        else{
            System.out.println(Arrays.toString(taskList.get(taskNumber-1).taskType) +
                    Arrays.toString(taskList.get(taskNumber-1).markAsDone) + " " + taskList.get(taskNumber-1).toBeDone
                    + " (from: " + taskList.get(taskNumber-1).startTime
                    + " to: "+ taskList.get(taskNumber-1).endTime + ")");
        }
        taskList.remove(taskNumber-1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}

