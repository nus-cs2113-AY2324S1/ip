package duke;


import CommandFormat.Command;
import task.*;

import exception.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Duke {

    public static int taskCount = 0;
    private static Task[] list = new Task[100];

    public static void main(String[] args) throws IOException{
        printWelcomeMessage();
        Scanner keyboard = new Scanner(System.in);
        outputFileInitialization();
        executeCommand(keyboard);
    }

    private static void executeCommand(Scanner keyboard) {
        while (true) {
            int taskNo;
            String command = keyboard.nextLine();
            command = Command.formattedCommand(command);
            String[] commandSplits = command.split(" ");
            if (missingOrExtraTaskDescription(commandSplits)){
                continue;
            }

            try {
                switch (commandSplits[0]) {
                case "bye":
                    printByeMessage();
                    keyboard.close();
                    return;

                case "list":
                    printList();
                    break;

                case "mark":
                    // command format e.g. mark 1
                    taskNo = getTaskNo(commandSplits[1]);
                    list[taskNo - 1].setDone(taskNo, taskCount, list);
                    break;

                case "unmark":
                    taskNo = getTaskNo(commandSplits[1]);
                    list[taskNo - 1].setNotDone(taskNo, taskCount, list);
                    break;

                case "delete":
                    taskNo = getTaskNo(commandSplits[1]);
                    deleteTask(taskNo);
                    break;

                case "todo":
                    // command format e.g. todo borrow book
                    list[taskCount] = Todo.newTodoTask(command);
                    createTaskSuccessMsg();
                    break;

                case "deadline":
                    // command e.g. deadline return book /by Sunday
                    list[taskCount] = Deadline.newDdl(command);
                    createTaskSuccessMsg();
                    break;

                case "event":
                    //command e.g. event project meeting /from Mon 2pm /to 4pm
                    list[taskCount] = Event.newEventTask(command);
                    createTaskSuccessMsg();
                    break;

                case "save":
                    backupTaskFile();
                    break;

                default:
                    throw new DukeException();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Hey, please input your command with the correct task number.");
            } catch (NullPointerException npe){
                System.out.println("Your target task doesn't exist. Please input a correct task.");
            } catch (DukeException e){
                e.incorrectFormatException(commandSplits[0]);
            } catch (FileNotFoundException fnf){
                System.out.println("Sorry, I cannot find the task source. Please check the task file.");
            } catch (IOException io){
                System.out.println("OMG! Something went wrong! Please check if the source files are available.");
            }
        }
    }

    private static void outputFileInitialization() throws IOException {
        checkAndCreateDataFolder();
        checkAndCreateFile("data\\taskList.txt");
        clearData(); //if the output file in not empty
    }

    private static int getTaskNo(String taskNum) {
        //exception: taskNum is not number, or containing non-numerical value
        return Integer.parseInt(taskNum);
    }

    public static Task[] getTaskList() {
        return list;
    }

    private static void deleteTask(int deleteIndex) throws IOException{
        if (deleteIndex <= 0 || deleteIndex> Duke.taskCount){
            System.out.println("Oh, No! invalid index! You don't have that task. Please try again.");
            return;
        }
        deleteTaskSuccessMsg(deleteIndex);
        list = Task.updatedTaskList(deleteIndex - 1);
        overwriteToFile("data/taskList.txt", getConcatenateTasks());
    }

    //This method will return all tasks inside the list
    private static String getConcatenateTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            String taskAppend = (i +1) + ". " +  list[i].toString();
            stringBuilder.append(taskAppend).append("\n");
        }
        return stringBuilder.toString();
    }

    //To tackle cases of invalid input like 'todo', 'event', etc.
    private static boolean missingOrExtraTaskDescription(String[] cmd){
        if (cmd.length == 1){
            if(cmd[0].equals("todo") || cmd[0].equals("event") || cmd[0].equals("deadline")
                    || cmd[0].equals("mark") || cmd[0].equals("unmark") || cmd[0].equals("delete")){
                System.out.println("Please describe your target.");
                return true;
            }
        }
        else if (cmd.length>=2 && cmd[0].equals("list") ){
            System.out.println("Do you mean to see the list? Please try again using 'list'.");
            return true;
        }else if (cmd.length>=2 && cmd[0].equals("save") ){
            System.out.println("Do you mean to save the tasks? Please try again using 'save'.");
            return true;
        }
        return false;
    }

    private static void createTaskSuccessMsg() throws IOException{
        System.out.println("Got it. I've added this task:");
        System.out.println(list[taskCount]);
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");

        String taskAppend = taskCount + ". " +  list[taskCount - 1].toString() + "\n";
        writeToFile("data/taskList.txt", taskAppend);
    }

    private static void deleteTaskSuccessMsg(int deleteIndex) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(list[deleteIndex - 1]);
        taskCount--;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }


    private static void printByeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void clearData() throws IOException {
        Path clearFile = Paths.get("data/taskList.txt");
        if (Files.size(clearFile) != 0) {
            Files.write(clearFile, new byte[0], StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    private static void backupTaskFile() throws IOException{
        Path sourcePath = Paths.get("data/taskList.txt");
        Path targetPath = Paths.get("data/backup_taskList.txt");
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING); //if file not exist, create one;
        System.out.println("Great! I have saved the current tasks.");
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello! I'm Oriento.");
        System.out.println("What can I help you?");
        System.out.println("⣿⣿⣿⠟⠛⠛⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⢋⣩⣉⢻⣿\n" +
                "⣿⣿⣿⠀⣿⣶⣕⣈⠹⠿⠿⠿⠿⠟⠛⣛⢋⣰⠣⣿⣿⠀⣿⣿\n" +
                "⣿⣿⣿⡀⣿⣿⣿⣧⢻⣿⣶⣷⣿⣿⣿⣿⣿⣿⠿⠶⡝⠀⣿⣿\n" +
                "⣿⣿⣿⣷⠘⣿⣿⣿⢏⣿⣿⣋⣀⣈⣻⣿⣿⣷⣤⣤⣿⡐⢿⣿\n" +
                "⣿⣿⣿⣿⣆⢩⣝⣫⣾⣿⣿⣿⣿⡟⠿⠿⠦⠀⠸⠿⣻⣿⡄⢻⣿\n" +
                "⣿⣿⣿⣿⣿⡄⢻⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣾⣿⣿⣿⣿⠇⣼⣿\n" +
                "⣿⣿⣿⣿⣿⣿⡄⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣰⣿\n" +
                "⣿⣿⣿⣿⣿⣿⠇⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢀⣿⣿\n" +
                "⣿⣿⣿⣿⣿⠏⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢸⣿⣿\n" +
                "⣿⣿⣿⣿⠟⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⣿⣿\n" +
                "⣿⣿⣿⠋⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⣿⣿\n" +
                "⣿⣿⠋⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⢸⣿\n");
    }

    private static void printList() throws IOException{
        if (taskCount == 0){
            System.out.println("You don't have any tasks now. Do you want to add a new task?");
            return;
        }
        printFileContents("data/taskList.txt");

        // This is to print the whole task message directly
        /*
        for (int i = 0; i < count; i++) {
            //example 1.[T][X] read book
            System.out.println((i + 1) + "." + list[i]);
        }*/
    }

    private static void printFileContents(String filePath) throws IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void writeToFile(String filePath, String taskAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(taskAppend);
        fw.close();
    }

    private static void overwriteToFile(String filePath, String taskAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(taskAppend);
        fw.close();
    }

    public static void checkAndCreateFile(String path) throws IOException {
        File f = new File(path);
        if(!f.exists()){
            if(!f.createNewFile()){
                throw new IOException();
            }
        }
    }

    public static void checkAndCreateDataFolder() throws IOException {
        File folder = new File("data");
        if (!folder.isDirectory()) {
            if(!folder.mkdirs()){
                throw new IOException();
            }
        }
    }

}
