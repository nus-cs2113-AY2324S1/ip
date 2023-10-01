package utility;

import exception.FrankException;
import task.Task;
import task.TaskList;
import task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import static utility.Constants.LOGO;
import static utility.Constants.SOLIDLINE;

public class Ui {
    public void showTasks(ArrayList<Task> tasks) {
        System.out.print(SOLIDLINE);
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i).toString() );
        }
        System.out.print(SOLIDLINE);
    }
    public void showSelectedTasks(ArrayList<Task> tasks, ArrayList<Integer> index) {
        System.out.print(SOLIDLINE);
        if(index.isEmpty()) {
            System.out.println("There was nothing matching the search terms");
        } else {
            System.out.println("Here are the selected tasks: ");
            for (Integer i : index) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
        System.out.print(SOLIDLINE);
    }
    public void showMarked(Task task) {
        System.out.println(SOLIDLINE + "Tres Bien! I have marked this as " +
                (task.getIsDone() ? "done: " : "not done yet: "));
        System.out.println("[" + task.getStatusIcon() + "] " +
                task.getDescription() + SOLIDLINE);
    }
    public void showDeleted(Task task) {
        System.out.println(SOLIDLINE + "Deleted: " + task + SOLIDLINE);
    }
    public void showAdded(Task task) {
        System.out.println(SOLIDLINE + "Added: " + task + SOLIDLINE);
    }
    public void showGoodbye() {
        System.out.println(SOLIDLINE + "\nGoodbye User, zai jian yong ze, sampai jumpa lagi user \n" + SOLIDLINE);
    }
    public boolean confirmExecute() {
        System.out.println("This will remove all tasks! Are you sure? + " +
                "Type Y to confirm, another key to cancel. ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("y")){
            System.out.println(SOLIDLINE + "Clearing tasks..." + SOLIDLINE);
            return true;
        }
        System.out.println(SOLIDLINE + "Cancelling..." + SOLIDLINE);
        return false;
    }
    public String getTodo() throws FrankException {
        Scanner input = new Scanner(System.in);
        System.out.println("What is your todo?" );
        String description = input.nextLine();
        if(description.isEmpty()) {
            throw new FrankException("Brough this description is missing");
        }
        return description;
    }
    public String[] getDeadline() throws FrankException {
        String[] descDueDate = new String[2];
        Scanner input = new Scanner(System.in);
        System.out.println("Bisa! What is the task?");
        descDueDate[0] = input.nextLine();
        System.out.println("Ke Yi! When is it due?");
        descDueDate[1] = input.nextLine();
        if(descDueDate[0].isEmpty() || descDueDate[1].isEmpty()) {
            throw new FrankException("Brough you forgot to fill in something!");
        }
        return descDueDate;
    }
    public String[] getEvent() throws FrankException {
        String[] descStartEnd = new String[3];
        Scanner input = new Scanner(System.in);
        System.out.println("Boleh! What is the event?");
        descStartEnd[0] = input.nextLine();
        System.out.println("When does it start?");
        descStartEnd[1] = input.nextLine();
        System.out.println("When does it end?");
        descStartEnd[2] = input.nextLine();
        if(descStartEnd[0].isEmpty() || descStartEnd[1].isEmpty() || descStartEnd[2].isEmpty()) {
            throw new FrankException("Brough you forgot to fill in something!");
        }
        return descStartEnd;
    }
    public void showWelcome() {
        System.out.println(SOLIDLINE + LOGO + SOLIDLINE);
        System.out.println("Hello user, I'm FRANK! Nice to meet you!\n" + SOLIDLINE);
    }
    public void showError(String error) {
        System.out.println(SOLIDLINE + error + SOLIDLINE);
    }
}