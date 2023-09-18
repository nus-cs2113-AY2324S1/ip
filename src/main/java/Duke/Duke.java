package Duke;

import Duke.tasks.Deadline;
import Duke.tasks.Event;
import Duke.tasks.Todo;

import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(" Hello! I'm Bot Hilary ");
        Task[] list = new Task[100];
        System.out.println(" What can I do for you? ");
        String line = in.nextLine();
        String eventTime = "";
        while (!line.equals("bye")) {
            String command = line.split(" ", 2)[0];
            switch (command){
            case "list":
                for (int i = 0; i < Task.listCount; ++i) {
                    System.out.print(i+1 + ".");
                    System.out.println(list[i]);
                }
                break;
            case "unmark":
                line = line.replace("unmark ", "");
                try {
                    list[Integer.parseInt(line) - 1].unmark();
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println(list[Integer.parseInt(line) - 1]);
                } catch(NumberFormatException | NullPointerException e){
                    System.out.println(" OOPS!!! Need to specify which task want to unmark");
                }
                break;
            case "mark":
                line = line.replace("mark ", "");
                try {
                    list[Integer.parseInt(line) - 1].markAsDone();
                    System.out.print(" Nice! I've marked this task as done:\n\t");
                    System.out.println(list[Integer.parseInt(line) - 1]);
                } catch(NumberFormatException | NullPointerException e){
                    System.out.println(" OOPS!!! Need to specify which task want to mark as done");
                }
                break;
            case "deadline":
                try {
                    eventTime = line.split("/by ", 2)[1];
                    line = line.split(" ", 2)[1];
                    line = line.split("/", 2)[0];
                    eventTime = eventTime.replace("from", "from:");
                    eventTime = eventTime.replace("/", "");
                    list[Task.listCount] = new Deadline(line, eventTime);
                    System.out.println(" Got it. I've added this task:\n\t" + list[Task.listCount] + "\n Now you have "+ ++Task.listCount + " tasks in the list.");
                } catch(IndexOutOfBoundsException e){
                    System.out.println("OOPS!!! The deadline need to separated by \"/by\"");
                }
                break;
            case "event":
                try {
                    eventTime = line.split("/from ", 2)[1];
                    line = line.split(" ", 2)[1];
                    line = line.split("/",2)[0];
                    eventTime = eventTime.replace("/to", "to:");
                    list[Task.listCount] = new Event(line, eventTime);
                    System.out.println(" Got it. I've added this task:\n\t" + list[Task.listCount] + "\n Now you have "+ ++Task.listCount + " tasks in the list.");
                } catch(IndexOutOfBoundsException e){
                    System.out.println("OOPS!!! The event timing need to separated by \"/from\"");
                }
            case "todo":
                try {
                    list[Task.listCount] = new Todo(line.split(" ", 2)[1]);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println(list[Task.listCount]);
                    Task.listCount++;
                    System.out.println("Now you have " + Task.listCount + " in the list");
                } catch(IndexOutOfBoundsException e){
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            default:
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            line = in.nextLine();
        }
        System.out.println("  Bye. Hope to see you again soon! ");
    }
}