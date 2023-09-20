package duke.commands;

import duke.tasks.*;


import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

public class ToDoList {
    public ArrayList<Task> taskList;
    public String[] markedTask;
    public String checker;
    public String marker;
    public String unmarked;
    public String toDo;
    public String delete;


    String taskListFile = "./duke.txt";


    public ToDoList() throws IOException {
        taskList = new ArrayList<>();
        markedTask = new String[100];
        checker = "list";
        marker = "mark";
        unmarked = "unmark";
        toDo = "todo";
        delete ="delete";
    }
    static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }


    public void listTask(){

    System.out.println("Here are the tasks in your list");
    for(int j = 0; j < taskList.size(); j += 1) {

        if (Echo.newList.taskList.get(j).taskType[0] == "T") {
            System.out.println(j + 1 + "." + Arrays.toString(Echo.newList.taskList.get(j).taskType) +
                    Arrays.toString(Echo.newList.taskList.get(j).markAsDone) + " "
                    + Echo.newList.taskList.get(j).toBeDone);
        }
        else if(Echo.newList.taskList.get(j).taskType[0] == "D"){
            System.out.println(j + 1 + "." + Arrays.toString(Echo.newList.taskList.get(j).taskType) +
                    Arrays.toString(Echo.newList.taskList.get(j).markAsDone)
                    + " " + Echo.newList.taskList.get(j).toBeDone
                    + " (by: " + Echo.newList.taskList.get(j).dueDate + ")");
        }
        else if (Echo.newList.taskList.get(j).taskType[0] == "E"){
            System.out.println(j + 1 + "." + Arrays.toString(Echo.newList.taskList.get(j).taskType) +
                    Arrays.toString(Echo.newList.taskList.get(j).markAsDone) + " " +
                        Echo.newList.taskList.get(j).toBeDone
                        + " (from: " + Echo.newList.taskList.get(j).startTime
                        + " to: "+ Echo.newList.taskList.get(j).endTime + ")");
        }

    }
}
    public void mark(String input) throws IOException {
        if(input.startsWith("mark")) {
            int dividerPosition = input.indexOf(" ");
            String taskNumberString = input.substring(dividerPosition + 1);
            int taskNumber = Integer.parseInt(taskNumberString.trim());
            Task taskToBeMarked =Echo.newList.taskList.get(taskNumber - 1);
            taskToBeMarked.setDone();
            finalFile();

            System.out.println("Task Completed!");
            System.out.println(Arrays.toString(taskToBeMarked.markAsDone) + " " + taskToBeMarked.toBeDone);
        }
    }
    public void unmark(String input) throws IOException {
        if(input.startsWith("unmark")){
            int dividerPosition = input.indexOf(" ");
            String taskNumberString = input.substring(dividerPosition + 1);
            int taskNumber = Integer.parseInt(taskNumberString);
            Task taskToBeUnmarked = Echo.newList.taskList.get(taskNumber - 1);
            taskToBeUnmarked.setNotDone();
            finalFile();
            System.out.println("Task marked as uncompleted!");
            System.out.println(Arrays.toString(taskToBeUnmarked.markAsDone)+ " " + taskToBeUnmarked.toBeDone);
        }
    }
    public void addToDo(String incomingTask) throws IOException {
        ToDos newToDo = new ToDos(incomingTask);
        Echo.newList.taskList.add(newToDo);
        finalFile();

        System.out.println("added: " + newToDo.toBeDone);
        System.out.println("Now you have " + Echo.newList.taskList.size() + " tasks in the list.");

    }

    public void addDeadline(String incomingTask, String deadline) throws IOException {
        Deadline newDeadline = new Deadline(incomingTask,deadline);
        Echo.newList.taskList.add(newDeadline);
        finalFile();
        System.out.println("added: " + newDeadline.toBeDone + " (by: " + deadline +")");
        System.out.println("Now you have " + Echo.newList.taskList.size() + " tasks in the list.");
    }

    public void addEvent(String incomingTask, String starting, String ending) throws IOException {
        Event newEvent = new Event(incomingTask,starting,ending);
        Echo.newList.taskList.add(newEvent);
        finalFile();
        System.out.println("added: " + newEvent.toBeDone + " (from: " + starting +" to:" + ending + ")");
        System.out.println("Now you have " +Echo.newList.taskList.size() + " tasks in the list.");

    }

    public void removeTask(String input) throws IOException {
        int dividerPosition = input.indexOf(" ");
        String taskNumberString = input.substring(dividerPosition + 1);
        int taskNumber = Integer.parseInt(taskNumberString);

        System.out.println("Task Removed");

        if (Echo.newList.taskList.get(taskNumber-1).taskType[0] == "T") {
            System.out.println(Arrays.toString(Echo.newList.taskList.get(taskNumber-1).taskType) +
                    Arrays.toString(Echo.newList.taskList.get(taskNumber-1).markAsDone) + " " +
                    Echo.newList.taskList.get(taskNumber-1).toBeDone);
        }
        else if(Echo.newList.taskList.get(taskNumber-1).taskType[0] == "D"){
            System.out.println(Arrays.toString(Echo.newList.taskList.get(taskNumber-1).taskType) +
                    Arrays.toString(Echo.newList.taskList.get(taskNumber-1).markAsDone)
                    + " " + Echo.newList.taskList.get(taskNumber-1).toBeDone
                    + " (by: " + Echo.newList.taskList.get(taskNumber-1).dueDate + ")");
        }
        else{
            System.out.println(Arrays.toString(Echo.newList.taskList.get(taskNumber-1).taskType) +
                    Arrays.toString(Echo.newList.taskList.get(taskNumber-1).markAsDone) + " " + Echo.newList.taskList.get(taskNumber-1).toBeDone
                    + " (from: " + Echo.newList.taskList.get(taskNumber-1).startTime
                    + " to: "+ Echo.newList.taskList.get(taskNumber-1).endTime + ")");
        }
        Echo.newList.taskList.remove(taskNumber-1);
        System.out.println("Now you have " +Echo.newList.taskList.size() + " tasks in the list.");
        finalFile();
    }

    public void addFile(Task toAdd) throws IOException {

            if (toAdd.taskType[0] == "T") {
                appendToFile(taskListFile, Arrays.toString(toAdd.taskType) + "/" +
                        Arrays.toString(toAdd.markAsDone) + "/ " +  toAdd.toBeDone  + "/ / " + System.lineSeparator());
            } else if (toAdd.taskType[0] == "D") {
                appendToFile(taskListFile, Arrays.toString(toAdd.taskType) +"/" +
                        Arrays.toString(toAdd.markAsDone) + "/ " + toAdd.toBeDone + "/"
                        + " (by:" + toAdd.dueDate +"/ "  + ")" + System.lineSeparator());
            } else if (toAdd.taskType[0] == "E") {
                appendToFile(taskListFile, Arrays.toString(toAdd.taskType)  + "/" +
                        Arrays.toString(toAdd.markAsDone) + "/ " + toAdd.toBeDone + "/"
                        + " (from: "  + toAdd.startTime + "/"
                        + " to: " + toAdd.endTime + "/"+  ")" + System.lineSeparator());
            }
    }

    public void finalFile() throws IOException {
        writeToFile("./duke.txt", "");
        for(int i = 0; i < taskList.size(); i += 1){
             addFile(taskList.get(i));
        }
    }

}

