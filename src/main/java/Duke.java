import java.util.Scanner;  // Import the Scanner class
public class Duke {

    //Create a task class
    public static class Task {
        private String taskName;
        private boolean isDone;
        public Task(String taskName){
            this.taskName = taskName;
            this.isDone = false;
        }
        //mark as done
        public void markAsDone(){
            this.isDone = true;
        }
        //mark as not done
        public void markAsNotDone(){
            this.isDone = false;
        }
        //get the status of the task and the task name
        public String toString(){
            if(this.isDone){
                return "[X] " + this.taskName;
            }else{
                return "[ ] " + this.taskName;
            }
        }
    }
    //check if an input has mark as the first word
    public static boolean isMark(String input){
        if(input.length()>=4){
            if("mark".equalsIgnoreCase(input.substring(0,4))){
                return true;
            }
        }
        return false;
    }
    //check if an input has unmark as the first word
    public static boolean isUnmark(String input){
        if(input.length()>=6){
            if("unmark".equalsIgnoreCase(input.substring(0,6))){
                return true;
            }
        }
        return false;
    }

    //create a list of tasks
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

        //Echo the arguments provided unless it is "bye" which quits the program
        while(!"bye".equalsIgnoreCase(userInput)){
            if("list".equalsIgnoreCase(userInput)){
                //Print out the list of tasks
                System.out.println("Here are the tasks in your list:");
                for(int i=0;i<taskCount;i++){
                    System.out.println((i+1) + ". " + tasks[i]);
                }
                userInput = userScan.nextLine();  // Get user input again
            }
            else if(isUnmark(userInput)){
                //get the task number
                int taskNumber = Integer.parseInt(userInput.substring(7));
                //mark the task as done
                tasks[taskNumber-1].markAsNotDone();
                //print out the task that was marked as done
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskNumber-1]);
                userInput = userScan.nextLine();  // Get user input again              
            }
            else if(isMark(userInput)){
                //get the task number
                int taskNumber = Integer.parseInt(userInput.substring(5));
                //mark the task as done
                tasks[taskNumber-1].markAsDone();
                //print out the task that was marked as done
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[taskNumber-1]);
                userInput = userScan.nextLine();  // Get user input again              
            }
            else{
                System.out.println("added: " + userInput); // Print user input
                //add to task list
                tasks[taskCount]=new Task(userInput);
                taskCount++;
                userInput = userScan.nextLine();  // Get user input again
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        
        //close the scanner
        userScan.close();

    }
}