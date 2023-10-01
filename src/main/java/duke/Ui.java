package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final String HORIZONTAL_LINE = "--------------------------------------------";
    private Scanner in;

    public Ui(){
        in = new Scanner(System.in);
    }

    public void introduceBot(TaskList tasks){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Lexi";

        System.out.println(logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm " + name);
        if (tasks.getTasks().size() > 0) {
            printList(tasks);
        } else {
            System.out.println("Currently, you have no tasks in your list.");
        }
        System.out.println("How can I help you buddy?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void farewellBot(){
        System.out.println("Have a wonderful day! Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printList(TaskList tasks){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.println((i+1) + "." + tasks.getTasks().get(i));
        }
    }

    public void printTaskAddedMessage(Task task, ArrayList<Task> tasks){
        System.out.println("Ok, I have added the following task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void printTaskRemovedMessage(Task task, TaskList tasks){
        System.out.println("Alright, I have removed the following task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }

    public String readCommand(){
        String input = in.nextLine().trim();
        return input;
    }

    public void printHorizontalLine(){
        System.out.println(HORIZONTAL_LINE);
    }

    public void printMark(boolean done, TaskList tasks, int index){
        if (done){
            System.out.println("Great! I have marked this task as done:");
        } else{
            System.out.println("Alright, I have marked this task as not done:");
        }
        System.out.println(tasks.getTasks().get(index - 1));
    }

    public void findKeyword(TaskList tasks, String keyword){
        if(keyword==null || keyword.isEmpty()){
            printList(tasks);
            return;
        }
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : tasks.getTasks()){
            if (task.getDescription().contains(keyword)){
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.size() == 0){
            System.out.println("There are no matching tasks in your list!");
        } else{
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i+1) + "." + matchingTasks.get(i));
            }
        }
    }

    public void printInvalidDeadlineMessage(){
        System.out.println("Oops, I don't understand that! Please provide a valid deadline in the format: " +
                "deadline <description> /by <due date>");
    }

    public void printEmptyDeadlineMessage(){
        System.out.println("I am sorry, the deadline cannot be empty! Please provide a valid deadline in the " +
                "format: deadline <description> /by <due date>");
    }

    public void printInvalidEventMessage(){
        System.out.println("Oops, I don't understand that! Please provide a valid event in the format: event " +
                "<description> /from <start date> /to <end date>");
    }

    public void printEmptyEventMessage(){
        System.out.println("I am sorry, the event cannot be empty! Please provide a valid event in the format: " +
                "event <description> /from <start date> /to <end date>");
    }

    public void printInvalidTaskIdMessage(){
        System.out.println("This task id does not exist, please provide a valid task number!");
    }

    public void printEmptyTodoMessage(){
        System.out.println("I am sorry, the todo cannot be empty!");
    }

    public void printInvalidDateTimeMessage(){
        System.out.println("Oops, I don't understand that! Please provide valid date and time in a readable format " +
                "like: " +
                "YYYY-MM-DD HH:MM");
    }
}
