package message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static Oriento.Oriento.list;
import static Oriento.Oriento.taskCount;

public class Text {
    /**
     * this class contains all user interacting messages
     */

    public static void printWelcomeMessage() {
        System.out.println("Hello! I'm Oriento.");
        System.out.println("What can I help you?");
    }

    public static void printHelpMessage() {
        System.out.println("Sorry I do not tell you use how to give commands. Please see the following hints.");
        System.out.println("Hint1: To create a task, please start with 'todo', 'event', 'deadline'.");
        System.out.println("Hint2: To modify task statue, please start with 'mark' or 'unmark'.");
        System.out.println("please use 'list' to look for your task list, or 'delete' to remove a task.");
        System.out.println("You may use 'find' to search for your task.");
    }

    public static void printByeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printFileContents(String filePath) throws IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void printList() throws IOException{
        if (taskCount == 0){
            System.out.println("You don't have any tasks now. Do you want to add a new task?");
            return;
        }
        printFileContents("data/taskList.txt");

    }

    public static void createTaskSuccessMsg() throws IOException{
        System.out.println("Got it. I've added this task:");
        System.out.println(list[taskCount]);
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");

        String taskAppend = taskCount + ". " +  list[taskCount - 1].toString() + "\n";
        writeToFile("data/taskList.txt", taskAppend);
    }

    public static void restoreTaskIntoFile() throws IOException {
        String taskAppend = taskCount + ". " +  list[taskCount - 1].toString() + "\n";
        writeToFile("data/taskList.txt", taskAppend);
    }

    private static void writeToFile(String filePath, String taskAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(taskAppend);
        fw.close();
    }

    public static void deleteTaskSuccessMsg(int deleteIndex) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(list[deleteIndex - 1]);
        taskCount--;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void printdottedline() {
        System.out.println("================================================");
    }

}
