import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(" Hello! I'm Bot Hilary ");
        Task[] list = new Task[100];
        System.out.println(" What can I do for you? ");
        String line = in.nextLine();
        while (!line.equals("bye")){
            if (line.equals("list")) {
                for (int i = 0; i < Task.listCount; ++i) {
                    System.out.print(i+1 + ".");
                    System.out.println(list[i]);
                }
            }
            else if (line.contains("unmark")){
                line = line.replace("unmark ", "");
                try {
                    list[Integer.parseInt(line) - 1].unmark();
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println(list[Integer.parseInt(line) - 1]);
                } catch(IndexOutOfBoundsException e){
                    System.out.println("OOPS!!! Need to specify which task want to unmark");
                }

            }
            else if (line.contains("mark")){
                line = line.replace("mark ", "");
                try {
                    list[Integer.parseInt(line) - 1].markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println(list[Integer.parseInt(line) - 1]);
                } catch(IndexOutOfBoundsException e){
                    System.out.println("OOPS!!! Need to specify which task want to mark as done");
                }

            }
            else{
                String eventTime = "";
                if (line.contains("deadline")){
                    try {
                        eventTime = line.split("/by ", 2)[1];
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("OOPS!!! The deadline need to separated by \"/by\"");
                    }
                    try {
                        line = line.split(" ", 2)[1];
                        line = line.split("/", 2)[0];
                        eventTime = eventTime.replace("from", "from:");
                        eventTime = eventTime.replace("/", "");
                        list[Task.listCount] = new Deadline(line, eventTime);
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("OOPS!!! The deadline of the task is needed");
                    }
                }
                else if (line.contains("event")){
                    try {
                        eventTime = line.split("/from ", 2)[1];
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("OOPS!!! The event timing need to separated by \"/from\"");
                    }
                    try {
                        line = line.split(" ", 2)[1];
                        line = line.split("/",2)[0];
                        eventTime = eventTime.replace("/to", "to:");
                        list[Task.listCount] = new Event(line, eventTime);
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("OOPS!!! The timing of the event is needed");
                    }
                }
                else if (line.contains("todo")){
                    try {
                        list[Task.listCount] = new Todo(line.split(" ", 2)[1]);
                        System.out.println(" Got it. I've added this task:");
                        System.out.println(list[Task.listCount]);
                        Task.listCount++;
                        System.out.println("Now you have " + Task.listCount + " in the list");
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    }
                }
                else{
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            }
            line = in.nextLine();
        }
        System.out.println("  Bye. Hope to see you again soon! ");
    }
}
