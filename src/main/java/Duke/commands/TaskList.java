package duke.commands;

import duke.tasks.*;


import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

public class TaskList {
    public ArrayList<Task> taskList;
    public String[] markedTask;
    public String checker;
    public String marker;
    public String unmarked;
    public String toDo;
    public String delete;
    public String find;

    String taskListFile = "./duke.txt";
    static Parser Parser = new Parser();

    public TaskList() throws IOException {
        taskList = new ArrayList<>();
        markedTask = new String[100];
        checker = "list";
        marker = "mark";
        unmarked = "unmark";
        toDo = "todo";
        delete ="delete";
        find = "find";
    }
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
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

        System.out.print(j+1);
        duke.commands.Parser.parseAndPrintTasks(taskList.get(j));
        System.out.print("\n");

    }
}
    public void mark(String input) throws IOException {
        if(input.startsWith("mark")) {
            int dividerPosition = input.indexOf(" ");
            String taskNumberString = input.substring(dividerPosition + 1);
            int taskNumber = Integer.parseInt(taskNumberString.trim());
            Task taskToBeMarked =Ui.newList.taskList.get(taskNumber - 1);
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
            Task taskToBeUnmarked = Ui.newList.taskList.get(taskNumber - 1);
            taskToBeUnmarked.setNotDone();
            finalFile();
            System.out.println("Task marked as uncompleted!");
            System.out.println(Arrays.toString(taskToBeUnmarked.markAsDone)+ " " + taskToBeUnmarked.toBeDone);
        }
    }
    public void addToDo(String incomingTask) throws IOException {
        ToDos newToDo = new ToDos(incomingTask);
        Ui.newList.taskList.add(newToDo);
        finalFile();

        System.out.println("added: " + newToDo.toBeDone);
        System.out.println("Now you have " + Ui.newList.taskList.size() + " tasks in the list.");

    }

    public void addDeadline(String incomingTask, String deadline) throws IOException {
        Deadline newDeadline = new Deadline(incomingTask,deadline);
        Ui.newList.taskList.add(newDeadline);
        finalFile();
        System.out.println("added: " + newDeadline.toBeDone + " (by: " + deadline +")");
        System.out.println("Now you have " + Ui.newList.taskList.size() + " tasks in the list.");
    }

    public void addEvent(String incomingTask, String starting, String ending) throws IOException {
        Event newEvent = new Event(incomingTask,starting,ending);
        Ui.newList.taskList.add(newEvent);
        finalFile();
        System.out.println("added: " + newEvent.toBeDone + " (from: " + starting +" to:" + ending + ")");
        System.out.println("Now you have " +Ui.newList.taskList.size() + " tasks in the list.");

    }

    public void removeTask(String input) throws IOException {
        int dividerPosition = input.indexOf(" ");
        String taskNumberString = input.substring(dividerPosition + 1);
        int taskNumber = Integer.parseInt(taskNumberString);

        System.out.println("Task Removed");

        if (Ui.newList.taskList.get(taskNumber-1).taskType[0] == "T") {
            System.out.println(Arrays.toString(Ui.newList.taskList.get(taskNumber-1).taskType) +
                    Arrays.toString(Ui.newList.taskList.get(taskNumber-1).markAsDone) + " " +
                    Ui.newList.taskList.get(taskNumber-1).toBeDone);
        }
        else if(Ui.newList.taskList.get(taskNumber-1).taskType[0] == "D"){
            System.out.println(Arrays.toString(Ui.newList.taskList.get(taskNumber-1).taskType) +
                    Arrays.toString(Ui.newList.taskList.get(taskNumber-1).markAsDone)
                    + " " + Ui.newList.taskList.get(taskNumber-1).toBeDone
                    + " (by: " + Ui.newList.taskList.get(taskNumber-1).dueDate + ")");
        }
        else{
            System.out.println(Arrays.toString(Ui.newList.taskList.get(taskNumber-1).taskType) +
                    Arrays.toString(Ui.newList.taskList.get(taskNumber-1).markAsDone) + " " +
                    Ui.newList.taskList.get(taskNumber-1).toBeDone
                    + " (from: " + Ui.newList.taskList.get(taskNumber-1).startTime
                    + " to: "+ Ui.newList.taskList.get(taskNumber-1).endTime + ")");
        }
        Ui.newList.taskList.remove(taskNumber-1);
        System.out.println("Now you have " +Ui.newList.taskList.size() + " tasks in the list.");
        finalFile();
    }

    public static void findTask(String toFind){
        System.out.println("Here are the matching tasks in your list: ");

        int taskNumberCounter = 0;
        for(int i = 0; i <Ui.newList.taskList.size(); i += 1){
            boolean taskfound = false;
            Task taskToSearch = Ui.newList.taskList.get(i);
            String taskInstructions = taskToSearch.toBeDone;
            String[] splitTaskInstructions = taskInstructions.split(" ");

            for(int j = 0; j < splitTaskInstructions.length; j += 1){
                if( toFind.contains(splitTaskInstructions[j])){
                    taskfound = true;
                }
                break;
            }


            if(taskfound == true){
                System.out.print(taskNumberCounter+1);
                duke.commands.Parser.parseAndPrintTasks(taskToSearch);
                System.out.print("\n");
            }

            taskNumberCounter += 1;

        }
    }

    public void addFile(Task toAdd) throws IOException {
            if (toAdd.taskType[0] == "T") {
                appendToFile(taskListFile, Arrays.toString(toAdd.taskType) + "/" +
                        Arrays.toString(toAdd.markAsDone) + "/ " +  toAdd.toBeDone.trim()  + "/ / " + System.lineSeparator());
            } else if (toAdd.taskType[0] == "D") {
                appendToFile(taskListFile, Arrays.toString(toAdd.taskType) +"/" +
                        Arrays.toString(toAdd.markAsDone) + "/ " + toAdd.toBeDone.trim() + "/"
                        + " (by:" + toAdd.dueDate +"/ "  + ")" + System.lineSeparator());
            } else if (toAdd.taskType[0] == "E") {
                appendToFile(taskListFile, Arrays.toString(toAdd.taskType)  + "/" +
                        Arrays.toString(toAdd.markAsDone) + "/ " + toAdd.toBeDone.trim() + "/"
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

