package message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static duke.Duke.list;
import static duke.Duke.taskCount;

public class Text {

    public static void printWelcomeMessage() {
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

}
