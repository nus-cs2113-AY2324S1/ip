package Duke;

import Duke.tasks.Deadline;
import Duke.tasks.Event;
import Duke.tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Bot Hilary");
        ArrayList<Task> list= new ArrayList<>();
        System.out.println("What can I do for you?");
        String line = in.nextLine();
        String eventTime = "";
        while (!line.equals("bye")) {
            String command = line.split(" ", 2)[0];
            if (!command.equals("list")){
                try{
                    line = line.split(" ", 2)[1];
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                    line = in.nextLine();
                    continue;
                }
            }
            switch (command){
            case "list":
                for (int i = 0; i < list.size(); ++i) {
                    System.out.print("\t" + (i+1) + ".");
                    System.out.println(list.get(i));
                }
                break;
            case "unmark":
                try {
                    list.get(Integer.parseInt(line) - 1).unmark();
                    System.out.print("\tOK, I've marked this task as not done yet:\n\t\t");
                    System.out.println(list.get(Integer.parseInt(line) - 1));
                } catch(NumberFormatException | NullPointerException | IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! Need to specify which task want to unmark");
                }
                break;
            case "mark":
                try {
                    list.get(Integer.parseInt(line) - 1).markAsDone();
                    System.out.print("\tNice! I've marked this task as done:\n\t\t");
                    System.out.println(list.get(Integer.parseInt(line) - 1));
                } catch(NumberFormatException | NullPointerException | IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! Need to specify which task want to mark as done");
                }
                break;
            case "deadline":
                try {
                    eventTime = line.split("/by ", 2)[1];
                    line = line.split("/", 2)[0];
                    eventTime = eventTime.replace("from", "from:");
                    eventTime = eventTime.replace("/", "");
                    list.add(new Deadline(line, eventTime));
                    System.out.println("\tGot it. I've added this task:\n\t\t" + list.get(list.size() - 1) + "\n\tNow you have "+ list.size() + " tasks in the list.");
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! The deadline need to separated by \"/by\"");
                }
                break;
            case "event":
                try {
                    eventTime = line.split("/from ", 2)[1];
                    line = line.split("/",2)[0];
                    eventTime = eventTime.replace("/to", "to:");
                    list.add(new Event(line, eventTime));
                    System.out.println("\tGot it. I've added this task:\n\t\t" + list.get(list.size() - 1) + "\n\tNow you have "+ list.size() + " tasks in the list.");
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! The event timing need to separated by \"/from\"");
                }
                break;
            case "todo":
                try {
                    list.add(new Todo(line));
                    System.out.print("\tGot it. I've added this task: \n\t\t");
                    System.out.println(list.get(list.size() - 1));
                    Task.listCount++;
                    System.out.println("\tNow you have " + list.size() + " in the list");
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "delete":
                try {
                    System.out.print("\tNoted. I've removed this task:\n\t\t");
                    System.out.println(list.get(Integer.parseInt(line) - 1));
                    list.remove(Integer.parseInt(line) - 1);
                    System.out.println("\tNow you have " + list.size() + " in the list");
                } catch(NumberFormatException | NullPointerException | IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! Need to specify which task want to unmark");
                }
                break;
            default:
                System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            line = in.nextLine();
        }
        System.out.println("  Bye. Hope to see you again soon! ");
    }
}