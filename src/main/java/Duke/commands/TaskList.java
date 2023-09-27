package duke.commands;

import duke.tasks.*;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

/**
 * Represents task-list and contains general information of tasks.
 * Contains methods relating to manipulation of tasks within task-list.
 */
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

    /**
     * Writes first task to file duke.txt to be saved.
     *
     * @param filePath relative path to duke.txt.
     * @param textToAdd text to add to duke.txt.
     * @throws IOException If inappropriate input is given.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Append subsequent tasks to duke.txt file to be saved.
     *
     * @param filePath relative path to duke.txt.
     * @param textToAppend text to add to duke.txt.
     * @throws IOException If unacceptable input is given.
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Prints out current tasks in the tasklist.
     */
    public void listTask(){

    System.out.println("Here are the tasks in your list");
    for(int j = 0; j < taskList.size(); j += 1) {
        System.out.print(j+1);
        duke.commands.Parser.parseAndPrintTasks(taskList.get(j));
        System.out.print("\n");

    }
}

    /**
     * Marks a task as done based on task number of task in list.
     *
     * @param input task to be marked.
     * @throws IOException If task number given does not exist.
     */
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

    /**
     * Remove mark from marked task based on task number in list.
     *
     * @param input task which mark is to be removed.
     * @throws IOException If task number given does not exist.
     */
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

    /**
     * Adds task of type todo to task list.
     *
     * @param incomingTask todo task to be added to list.
     * @throws IOException If inappropriate input type is given.
     */
    public void addToDo(String incomingTask) throws IOException {
        ToDos newToDo = new ToDos(incomingTask);
        Ui.newList.taskList.add(newToDo);
        finalFile();

        System.out.println("added: " + newToDo.toBeDone);
        System.out.println("Now you have " + Ui.newList.taskList.size() + " tasks in the list.");

    }

    /**
     * Adds task of type deadline to task-list.
     *
     * @param incomingTask deadline to be added to task-list.
     * @param deadline deadline of task to be completed by.
     * @throws IOException If inappropriate input is given.
     */
    public void addDeadline(String incomingTask, String deadline) throws IOException {
        Deadline newDeadline = new Deadline(incomingTask,deadline);
        Ui.newList.taskList.add(newDeadline);
        finalFile();
        System.out.println("added: " + newDeadline.toBeDone + " (by: " + deadline +")");
        System.out.println("Now you have " + Ui.newList.taskList.size() + " tasks in the list.");
    }

    /**
     * Adds task of type event to task-list.
     *
     * @param incomingTask event details to be added to the list.
     * @param starting start time of event.
     * @param ending end time of event.
     * @throws IOException If inappropriate input is given.
     */
    public void addEvent(String incomingTask, String starting, String ending) throws IOException {
        Event newEvent = new Event(incomingTask,starting,ending);
        Ui.newList.taskList.add(newEvent);
        finalFile();
        System.out.println("added: " + newEvent.toBeDone + " (from: " + starting +" to:" + ending + ")");
        System.out.println("Now you have " +Ui.newList.taskList.size() + " tasks in the list.");

    }

    /**
     * Removes task from tasklist based on task number.
     *
     * @param input task numebr of task to be removed.
     * @throws IOException If inappropriate input is given.
     */
    public void removeTask(String input) throws IOException {
        int dividerPosition = input.indexOf(" ");
        String taskNumberString = input.substring(dividerPosition + 1);
        int taskNumber = Integer.parseInt(taskNumberString);

        System.out.println("Task Removed");

        if (Ui.newList.taskList.get(taskNumber-1).taskType[0] == "T") {
            System.out.println(Arrays.toString(Ui.newList.taskList.get(taskNumber-1).taskType)
                    + Arrays.toString(Ui.newList.taskList.get(taskNumber-1).markAsDone) + " "
                    + Ui.newList.taskList.get(taskNumber-1).toBeDone);
        }
        else if(Ui.newList.taskList.get(taskNumber-1).taskType[0] == "D"){
            System.out.println(Arrays.toString(Ui.newList.taskList.get(taskNumber-1).taskType)
                    + Arrays.toString(Ui.newList.taskList.get(taskNumber-1).markAsDone)
                    + " " + Ui.newList.taskList.get(taskNumber-1).toBeDone
                    + " (by: " + Ui.newList.taskList.get(taskNumber-1).dueDate + ")");
        }
        else{
            System.out.println(Arrays.toString(Ui.newList.taskList.get(taskNumber-1).taskType) +
                    Arrays.toString(Ui.newList.taskList.get(taskNumber-1).markAsDone) + " "
                    + Ui.newList.taskList.get(taskNumber-1).toBeDone
                    + " (from: " + Ui.newList.taskList.get(taskNumber-1).startTime
                    + " to: "+ Ui.newList.taskList.get(taskNumber-1).endTime + ")");
        }

        Ui.newList.taskList.remove(taskNumber-1);
        System.out.println("Now you have " +Ui.newList.taskList.size() + " tasks in the list.");
        finalFile();
    }


    /**
     * Finds specific task containing words user inputs.
     *
     * @param toFind string used to find tasks within task-list.
     */
    public static void findTask(String toFind){
        System.out.println("Here are the matching tasks in your list: ");

        int taskNumberCounter = 0;
        for(int i = 0; i <Ui.newList.taskList.size(); i += 1){
            boolean taskfound = false;
            Task taskToSearch = Ui.newList.taskList.get(i);
            String taskInstructions = taskToSearch.toBeDone;
            String[] splitTaskInstructions = taskInstructions.split(" ");

            String wordToBeSearched = Arrays.toString(splitTaskInstructions);
            if(wordToBeSearched.contains(toFind)){
                taskfound = true;
            }

            if(taskfound == true){
                System.out.print(taskNumberCounter+1);
                duke.commands.Parser.parseAndPrintTasks(taskToSearch);
                System.out.print("\n");
                taskNumberCounter += 1;
            }
        }
    }

    /**
     * Add tasks to duke.txt file.
     *
     * @param toAdd task information to be added to duke,txt.
     * @throws IOException If inappropriate input is given.
     */
    public void addFile(Task toAdd) throws IOException {
            if (toAdd.taskType[0] == "T") {
                appendToFile(taskListFile, Arrays.toString(toAdd.taskType) + "/"
                        + Arrays.toString(toAdd.markAsDone) + "/ " +  toAdd.toBeDone.trim()  + "/ / "
                        + System.lineSeparator());
            } else if (toAdd.taskType[0] == "D") {
                appendToFile(taskListFile, Arrays.toString(toAdd.taskType) +"/"
                        + Arrays.toString(toAdd.markAsDone) + "/ " + toAdd.toBeDone.trim() + "/"
                        + " (by:" + toAdd.dueDate +"/ "  + ")" + System.lineSeparator());
            } else if (toAdd.taskType[0] == "E") {
                appendToFile(taskListFile, Arrays.toString(toAdd.taskType)  + "/"
                        + Arrays.toString(toAdd.markAsDone) + "/ " + toAdd.toBeDone.trim() + "/"
                        + " (from: "  + toAdd.startTime + "/"
                        + " to: " + toAdd.endTime + "/"+  ")" + System.lineSeparator());
            }
    }

    /**
     * Finalises task details to be saved to duke,txt file.
     *
     * @throws IOException If inappropriate input is given.
     */
    public void finalFile() throws IOException {
        writeToFile("./duke.txt", "");
        for(int i = 0; i < taskList.size(); i += 1){
             addFile(taskList.get(i));
        }
    }

}

