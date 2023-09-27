package Duke;

import Duke.dealWithFiles.GetFromFile;
import Duke.dealWithFiles.SaveToFile;
import Duke.inputProcess.TaskList;
import Duke.tasks.Deadline;
import Duke.tasks.Event;
import Duke.tasks.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {

    private static GetFromFile getTasks;
    private static SaveToFile saveTasks;
    private static TaskList tasks;
    private static Ui ui;
    private String filePath;

    public Duke(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        getTasks = new GetFromFile(filePath);
        saveTasks = new SaveToFile(filePath);
        tasks = new TaskList();
    }

    public static void main(String[] args) {
        new Duke("Duke.txt");
        try{
            getTasks.getFromTextFile(tasks);
        } catch(FileNotFoundException e){
            System.out.println("\tOOPS!!! File not found.");
        }
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        String eventTime = "";
        while (!userInput.equals("bye")) {
            String command = "list";
            if (!userInput.equals("list")){
                try{
                    command = userInput.split(" ", 2)[0].toLowerCase();
                    userInput = userInput.split(" ", 2)[1];
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                    userInput = in.nextLine();
                    continue;
                }
            }
            switch (command){
            case "list":
                tasks.printList();
                break;
            case "unmark":
                tasks.unmark((Integer.parseInt(userInput) - 1));
                break;
            case "mark":
                tasks.mark((Integer.parseInt(userInput) - 1));
                break;
            case "deadline":
                try {
                    eventTime = userInput.split("/by ", 2)[1];              // split the userInput into two part
                    userInput = userInput.split("/", 2)[0];
                    tasks.addDeadline(userInput, eventTime);
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! The deadline need to separated by \"/by\"");
                }
                break;
            case "event":
                try {
                    eventTime = userInput.split("/from ", 2)[1];
                    eventTime = eventTime.replace("/to", "to:");
                    userInput = userInput.split("/",2)[0];
                    tasks.addDeadline(userInput, eventTime);
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! The event timing need to separated by \"/from\"");
                }
                break;
            case "todo":
                tasks.addTodo(userInput);
                break;
            case "delete":
                try {
                    tasks.get(Integer.parseInt(userInput) - 1);
                    tasks.deleteTask(Integer.parseInt(userInput) - 1);
                } catch(NumberFormatException | NullPointerException | IndexOutOfBoundsException e){
                    System.out.println("OOPS!!! Need to specify which task want to delete");
                }
                break;
            default:
                System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            try {
                saveTasks.saveToTextFile(tasks);
            } catch(IOException e){
                System.out.println("Something wrong to save the file");
            }
            userInput = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon! ");
    }
}

