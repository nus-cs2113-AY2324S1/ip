import java.util.Scanner;  // Import the Scanner class
public class Duke {

    //Initialize create a list of tasks
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Create a greeting message for the user
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        //Get user input
        Scanner userScan = new Scanner(System.in);  // Create scanner object
        String userInput = userScan.nextLine();  // Get user input

        //Check the arguments provided unless it is "bye" which quits the program
        while( !(Check.isBye(userInput)) ){

            //If userInput is "list" print all tasks
            if(Check.isList(userInput)){
                //Print out the list of tasks
                System.out.println("Here are the tasks in your list:");
                for(int i=0;i<taskCount;i++){
                    System.out.println((i+1) + ". " + tasks[i]);
                }
                userInput = userScan.nextLine();  // Get user input again
            }

            //If userInput is "unmark" get the task number and unmark the task as done
            else if(Check.isUnmark(userInput)){
                //get the task number
                int taskNumber = Integer.parseInt(userInput.substring(7));
                //mark the task as done
                tasks[taskNumber-1].markAsNotDone();
                //print out the task that was marked as done
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskNumber-1]);
                userInput = userScan.nextLine();  // Get user input again              
            }

            //If userInput is "mark" get the task number and mark the task as done
            else if(Check.isMark(userInput)){
                //get the task number
                int taskNumber = Integer.parseInt(userInput.substring(5));
                //mark the task as done
                tasks[taskNumber-1].markAsDone();
                //print out the task that was marked as done
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[taskNumber-1]);
                userInput = userScan.nextLine();  // Get user input again              
            }

            //If userInput is "todo" add a todo task to the list
            else if(Check.isTodo(userInput)){
                //get the task name
                String taskName = userInput.substring(5);
                //create a todo task
                tasks[taskCount] = new Todo(taskName);
                //increment the task count
                taskCount++;
                //print out the task that was added
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount-1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                userInput = userScan.nextLine();  // Get user input again
            }

            //If userInput is "deadline" add a deadline task to the list
            else if(Check.isDeadline(userInput)){
                //get the task name
                String taskName = userInput.substring(9,userInput.indexOf("/"));
                //get the deadline string without any "/"
                String deadline = userInput.substring(userInput.indexOf("/")).replace("/","");
                //create a deadline task
                tasks[taskCount] = new Deadline(taskName,deadline);
                //increment the task count
                taskCount++;
                //print out the task that was added
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount-1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                userInput = userScan.nextLine();  // Get user input again
            }

            //If userInput is "event" add an event task to the list
            else if(Check.isEvent(userInput)){
                //get the task name
                String taskName = userInput.substring(6,userInput.indexOf("/"));
                //get the event time without any "/"
                String eventTime = userInput.substring(userInput.indexOf("/")).replace("/","");
                //create an event task
                tasks[taskCount] = new Event(taskName,eventTime);
                //increment the task count
                taskCount++;
                //print out the task that was added
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount-1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                userInput = userScan.nextLine();  // Get user input again
            }

            //If userInput is not any of the commands add the task to the list
            else{
                System.out.println("added: " + userInput); // Print user input
                //add to task list
                tasks[taskCount]=new Task(userInput);
                taskCount++;
                userInput = userScan.nextLine();  // Get user input again
            
            }
        }

        //Print out a goodbye message
        System.out.println("Bye. Hope to see you again soon!");
        
        //Close the scanner
        userScan.close();

    }
}