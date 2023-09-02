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
        public void markAsDone(){
            this.isDone = true;
        }
        public String toString(){
            return this.taskName;
        }
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
                for(int i=0;i<taskCount;i++){
                    System.out.println((i+1) + ". " + tasks[i]);
                }
                userInput = userScan.nextLine();  // Get user input again
                continue;
            }
            System.out.println("added: " + userInput); // Print user input
            //add to task list
            tasks[taskCount]=new Task(userInput);
            taskCount++;
            userInput = userScan.nextLine();  // Get user input again
        }
        System.out.println("Bye. Hope to see you again soon!");
        
        //close the scanner
        userScan.close();

    }
}