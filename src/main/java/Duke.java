import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = "\n" +
        "     ____.  _____ ______________   ____.___  _________\n" +
        "    |    | /  _  \\\\______   \\   \\ /   /|   |/   _____/\n" +
        "    |    |/  /_\\  \\|       _/\\   Y   / |   |\\_____  \\ \n" +
        "/\\__|    /    |    \\    |   \\ \\     /  |   |/        \\\n" +
        "\\________\\____|__  /____|_  /  \\___/   |___/_______  /\n" +
        "                 \\/       \\/                       \\/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hi Sir! I'm JARVIS \n" +
                " What can I do for you today?\n" +
                "____________________________________________________________\n" );

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while(!userInput.equals("bye")){
            System.out.println("____________________________________________________________");
            if(userInput.equals("list")){
                System.out.println("Here's your tasks!");
                for(int i = 0; i < taskList.size(); i++){
                    int indexNum = i + 1;
                    System.out.println(indexNum + "." + taskList.get(i));
                }
            }
            else if(userInput.startsWith("mark ")){
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                // begin index at end of "mark ", subtract 1 to index-0 format
                if(taskList.size() <= index){
                    System.out.println("Invalid task number " + (index + 1) + ". Try Again!");
                    // index + 1 to reflect back to index-1 format
                    System.out.println("____________________________________________________________");
                    userInput = sc.nextLine();
                    continue;
                }
                System.out.println("Nice! I've marked this task as done:");
                taskList.get(index).markAsDone();
                System.out.println("    " + taskList.get(index));
            }
            else{
                // To-do, Deadline & Events
                System.out.println("Got it. I've added this task:");
                if(userInput.startsWith("todo")){
                    Todo todo = new Todo(userInput.substring(5));
                    taskList.add(todo);
                    System.out.println("    " + todo);
                }
                else if (userInput.startsWith("deadline")){
                    int lastIndex = userInput.lastIndexOf("/by"); // Use the lastIndexOf method to get the last occurrence of /by in the input string.
                    if (lastIndex == -1) {
                        System.out.println("Invalid format. Use: deadline <description> /by <time>");
                    } else {
                        String description = userInput.substring(8, lastIndex).trim();
                        String time = userInput.substring(lastIndex + 3).trim();
                        Deadline deadline = new Deadline(description, time);
                        taskList.add(deadline);
                        System.out.println("    " + deadline.toString());
                    }
                }
                else if (userInput.startsWith("event")) {
                    String[] parts = userInput.substring(5).trim().split("/from|/to", 3); //substring(5) to remove "event" prefix
                    if (parts.length < 3) {
                        System.out.println("Invalid format. Use: event <description> /from <start_time> /to <end_time>");
                    } else {
                        String description = parts[0].trim();
                        String startTime = parts[1].trim();
                        String endTime = parts[2].trim();
                        String timeRange = startTime + " to " + endTime;
                        Event event = new Event(description, timeRange);
                        taskList.add(event);
                        System.out.println("    " + event.toString());
                    }
                }
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }
            System.out.println("____________________________________________________________");
            userInput = sc.nextLine();
        }
        System.out.println("--------------------------------------\n" +
                "Good bye sir! Have a good day" + "\n"
                + "--------------------------------------");
    }
}
