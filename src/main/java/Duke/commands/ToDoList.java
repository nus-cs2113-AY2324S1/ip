package duke.commands;

import duke.tasks.*;

import java.io.*;
import java.util.Arrays;

public class ToDoList {
    private Task[] taskList;
    private String[] markedTask;
    private int taskListTracker;
    public String checker;
    public String marker;
    public String unmarked;
    public String toDo;

    String taskListFile = "./duke.txt";


    public ToDoList() throws IOException {
        taskList = new Task[100];
        markedTask = new String[100];
        taskListTracker = 0;
        checker = "list";
        marker = "mark";
        unmarked = "unmark";
        toDo = "todo";
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
    int count = 0;
    for(int i = 0; i < taskList.length; i +=1) {
        if (taskList[i] == null) {
            count += 1;
        }
    }
    Task[] answer = (Arrays.copyOf(taskList,taskList.length - count));

    System.out.println("Here are the tasks in your list");
    for(int j = 0; j < answer.length; j += 1) {

        if (answer[j].taskType[0] == "T") {
            System.out.println(j + 1 + "." + Arrays.toString(answer[j].taskType) +
                    Arrays.toString(answer[j].markAsDone) + " " + answer[j].toBeDone);
        }
        else if(answer[j].taskType[0] == "D"){
            System.out.println(j + 1 + "." + Arrays.toString(answer[j].taskType) +
                    Arrays.toString(answer[j].markAsDone)
                    + " " + answer[j].toBeDone
                    + " (by: " + answer[j].dueDate + ")");
        }
        else if (answer[j].taskType[0] == "E"){
            System.out.println(j + 1 + "." + Arrays.toString(answer[j].taskType) +
                    Arrays.toString(answer[j].markAsDone) + " " + answer[j].toBeDone
                        + " (from: " + answer[j].startTime
                        + " to: "+ answer[j].endTime + ")");
        }
    }
}
    public void mark(String input) throws IOException {
        if(input.startsWith("mark")) {
            int dividerPosition = input.indexOf(" ");
            String taskNumberString = input.substring(dividerPosition + 1);
            int taskNumber = Integer.parseInt(taskNumberString.trim());
            Task taskToBeMarked = taskList[taskNumber - 1];
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
            Task taskToBeUnmarked = taskList[taskNumber-1];
            taskToBeUnmarked.setNotDone();
            finalFile();
            System.out.println("Task marked as uncompleted!");
            System.out.println(Arrays.toString(taskToBeUnmarked.markAsDone)+ " " + taskToBeUnmarked.toBeDone);
        }
    }
    public void addToDo(String incomingTask) throws IOException {
        ToDos newToDo = new ToDos(incomingTask);
        taskList[taskListTracker] = newToDo;
        taskListTracker += 1;
        finalFile();

        System.out.println("added: " + newToDo.toBeDone);
        System.out.println("Now you have " + taskListTracker + " tasks in the list.");

    }

    public void addDeadline(String incomingTask, String deadline) throws IOException {
        Deadline newDeadline = new Deadline(incomingTask,deadline);
        taskList[taskListTracker] = newDeadline;
        taskListTracker += 1;
        finalFile();


        System.out.println("added: " + newDeadline.toBeDone + " (by: " + deadline +")");
        System.out.println("Now you have " + taskListTracker + " tasks in the list.");
    }

    public void addEvent(String incomingTask, String starting, String ending) throws IOException {
        Event newEvent = new Event(incomingTask,starting,ending);
        taskList[taskListTracker] = newEvent;
        taskListTracker += 1;
        finalFile();

        System.out.println("added: " + newEvent.toBeDone + " (from: " + starting +" to:" + ending + ")");
        System.out.println("Now you have " + taskListTracker + " tasks in the list.");

    }


    public void addFile(Task toAdd) throws IOException {

            if (toAdd.taskType[0] == "T") {
                appendToFile(taskListFile, Arrays.toString(toAdd.taskType) + " " +
                        Arrays.toString(toAdd.markAsDone) + " " + toAdd.toBeDone + System.lineSeparator());
            } else if (toAdd.taskType[0] == "D") {
                appendToFile(taskListFile, Arrays.toString(toAdd.taskType) + " " +
                        Arrays.toString(toAdd.markAsDone) + " " + toAdd.toBeDone
                        + " (by: " + toAdd.dueDate + ")" + System.lineSeparator());
            } else if (toAdd.taskType[0] == "E") {
                appendToFile(taskListFile, Arrays.toString(toAdd.taskType) + " " +
                        Arrays.toString(toAdd.markAsDone) + " " + toAdd.toBeDone
                        + " (from: " + toAdd.startTime
                        + " to: " + toAdd.endTime + ")" + System.lineSeparator());
            }
    }

    public void finalFile() throws IOException {
        writeToFile("./duke.txt", "");
        for(int i = 0; i < taskListTracker; i += 1){
             addFile(taskList[i]);
        }
    }


}
