package magpie.task;

import java.util.ArrayList;

public class taskHandler {

    private static ArrayList<Task> tasks;
    private static int taskCount;

    public taskHandler() {
        tasks = new ArrayList<>();
        taskCount = 0;
    }

    public static void print() {

        if (taskCount == 0) {
            System.out.println("You have no tasks in your list :)");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < taskCount; i++) {

                Task item = tasks.get(i);
                System.out.println(i + 1 + ". " + item.toString());
            }

            System.out.println("____________________________________________________________\n");
        }
    }

    public static void displayIndexError(){
        if (taskCount == 0) {
            System.out.print("Wow! Looks like you have no tasks to manage. Add one first!\n");
        } else {
            System.out.print("Your input was not a valid index! Please choose from 1 to " + taskCount + "\n");
        }
    }
    public static void add(Task t) {

        tasks.add(t);
        taskCount++;

        System.out.println("____________________________________________________________\n");
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + taskCount + " task(s) in the list.");
        System.out.println("____________________________________________________________\n");

    }

    public static void delete(int index){

        try{
            Task t = tasks.get(index);
            tasks.remove(index);
            taskCount--;
            System.out.println("____________________________________________________________\n");
            System.out.println("Noted. I've removed this task: ");
            System.out.println("  " + t.toString());
            System.out.println("Now you have " + taskCount + " task(s) in the list.");
            System.out.println("____________________________________________________________\n");
        }
        catch(IndexOutOfBoundsException e){
            displayIndexError();
        }

    }
    public static void mark(int index, boolean isDone) {

        try {
            Task item = tasks.get(index);
            item.setDone(isDone);
            System.out.println("____________________________________________________________\n");
            if (isDone) {
                System.out.print("Nice! I've marked this task as done:\n");
            } else {
                System.out.print("OK, I've marked this task as not done yet:\n");

            }
            System.out.println("  " + item);
            System.out.println("____________________________________________________________\n");
        } catch (IndexOutOfBoundsException e) {
            displayIndexError();
        } catch (NullPointerException e) {
            displayIndexError();

        }


    }

}
