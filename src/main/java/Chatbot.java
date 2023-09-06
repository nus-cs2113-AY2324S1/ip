import java.util.Scanner;

public class Chatbot {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int numOfTasks = 0;

        String greetingMsg = "____________________________________________________________\n" +
                " Hello! I'm Chatbot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String byeMsg = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(greetingMsg);


        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < numOfTasks; i++) {
                    //System.out.println(" " + (i + 1) + ".[" + tasks[i].getTypeIcon() + "][" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark ")) {
                String number = input.replace("mark ", "").trim();
                int markTaskNo = Integer.parseInt(number);
                if (markTaskNo > 0) {
                    tasks[markTaskNo - 1].markAsDone();
                }
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   [" + tasks[markTaskNo - 1].getStatusIcon() + "] " + tasks[markTaskNo - 1].getDescription());
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("unmark ")) {
                String number = input.replace("unmark ", "").trim();
                int unmarkTaskNo = Integer.parseInt(number);
                if (unmarkTaskNo > 0) {
                    tasks[unmarkTaskNo - 1].markAsUndone();
                }
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   [" + tasks[unmarkTaskNo - 1].getStatusIcon() + "] " + tasks[unmarkTaskNo - 1].getDescription());
                System.out.println("____________________________________________________________");
            } else if ( input.startsWith("todo") ) {
                String msg = input.replace("todo ", "").trim();

                Task task = new Todo(msg);
                tasks[numOfTasks] = task;
                numOfTasks++;

                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + task);
                System.out.println(" Now you have " + String.valueOf(numOfTasks) + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }  else if ( input.startsWith("deadline") ) {
                String msg = input.replace("deadline", "").trim();
                String byDate = msg.substring(msg.indexOf("/by ") + 4).trim(); // will contain the byDate
                String desc = msg.substring(0, msg.indexOf("/by")); // will contain the deadline description

                Task task = new Deadline(desc, byDate);
                tasks[numOfTasks] = task;
                numOfTasks++;

                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + task);
                System.out.println(" Now you have " + String.valueOf(numOfTasks) + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if ( input.startsWith("event") ) {
                String msg = input.replace("event", "").trim();
                String dateRange = msg.substring(msg.indexOf("/from ") + 6).trim();
                String fromDate = dateRange.substring(0, dateRange.indexOf("/to ")).trim();
                String toDate = dateRange.substring(dateRange.indexOf("/to ") + 4).trim();

                String desc = msg.substring(0, msg.indexOf("/from ")); // will contain the deadline description

                Task task = new Event(desc, fromDate, toDate);
                tasks[numOfTasks] = task;
                numOfTasks++;

                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + task);
                System.out.println(" Now you have " + String.valueOf(numOfTasks) + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                // if not bye, list, mark, then the command by default is add
                tasks[numOfTasks] = new Task(input);
                numOfTasks++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }

            input = in.nextLine();
        }
        System.out.println(byeMsg);

    }
}
