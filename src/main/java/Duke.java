import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int count = 0;

        System.out.println("Hello! I'm Oriento.");
        System.out.println("What can I do for you?");

        while (true) {
            String command = scanner.nextLine();
            String[] parts = command.split(" ");
            int spaceIndex;

            switch (parts[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                return;

            case "list":
                for (int i = 0; i < count; i++) {
                    //example like 1.[T][X] read book
                    System.out.print((i + 1) + ".");
                    System.out.println(list[i]);
                }
                break;

            case "mark":
                int taskNo = Integer.parseInt(parts[1]);   //assume  saved in part[1], need further improve
                if( (taskNo > count ) || (taskNo <1) ){
                    System.out.println("Oops! You don't have any task in this positions.");
                }
                else{
                    list[taskNo].Done();
                    System.out.println("  Nice! I've marked this task as done:\n"
                                        + "  [X] " + list[taskNo].description);
                }
                break;

            case "unmark":
                int task = Integer.parseInt(parts[1]);   //assume  saved in part[1], need further improve
                if( (task > count ) || (task <1) ){
                    System.out.println("Oops! You don't have the task in this positions.");
                }
                else{
                    list[count].unDone();
                    System.out.println("OK, I've marked this task as not done yet:\n"
                                         + "  [ ] " + list[task].description);
                }
                break;

            case "todo":
                // command e.g. todo borrow book
                String[] todoSplit = command.split(" ", 2);
                list[count] = new Todo(todoSplit[1]);
                System.out.println("Got it. I've added this task:");
                System.out.println(list[count]);
                count++;
                System.out.println("Now you have " + count + " tasks in the list.");
                break;

            case "deadline":
                // command e.g. deadline return book /by Sunday
                String[] ddlSplit = command.split("/");
                spaceIndex = ddlSplit[0].indexOf(" ");
                String ddlTask = ddlSplit[0].substring(spaceIndex + 1).trim();
                list[count] = new Deadline(ddlTask, ddlSplit[1].substring(3));
                System.out.println("Got it. I've added this task:");
                System.out.println(list[count]);
                count++;
                System.out.println("Now you have " + count + " tasks in the list.");
                break;

            case "event":
                //command e.g. event project meeting /from Mon 2pm /to 4pm
                String[] eventSplit = command.split("/");
                spaceIndex = eventSplit[0].indexOf(" ");
                String eventTask = eventSplit[0].substring(spaceIndex + 1).trim();
                String start = eventSplit[1].trim().substring(5); // Remove "/from " prefix
                String end = eventSplit[2].trim().substring(3); // Remove "/to " prefix
                list[count] = new Event(eventTask, start, end);
                System.out.println("Got it. I've added this task:");
                System.out.println(list[count]);
                count++;
                System.out.println("Now you have " + count + " tasks in the list.");

            default:
                System.out.println("This is not expected. Do it again");
            }
        }


    }
}
