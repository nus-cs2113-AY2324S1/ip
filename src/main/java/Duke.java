import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList; // Import the ArrayList class

public class Duke {

    //Initialize create a list of tasks
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        FileRW.readFromFile(tasks);

        //Print out the logo
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
            
            try{
                //If userInput is "list" print all tasks
                if(Check.isList(userInput)){
                    //Print out the list of tasks
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    //Get user input again
                    userInput = userScan.nextLine();  
                }

                //If userInput is "unmark" get the task number and unmark the task as done
                else if(Check.isUnmark(userInput)){
                    //get the task number
                    int taskNumber = Integer.parseInt(userInput.substring(7));
                    //if the task number is greater than the task count throw an exception
                    if(taskNumber>tasks.size()){
                        throw new IllegalArgumentException("The task number is greater than the number of tasks.");
                    }
                    //if the task number is less than 1 throw an exception
                    if(taskNumber<1){
                        throw new IllegalArgumentException("The task number is less than 1.");
                    }
                    //if there is no task at the task number throw an exception
                    if(tasks.get(taskNumber-1)==null){
                        throw new IllegalArgumentException("There is no task at the task number.");
                    }
                    //if the task is already not done throw an exception
                    if(tasks.get(taskNumber-1).isDone==false){
                        throw new IllegalArgumentException("The task is already not done.");
                    }
                    //mark the task as done
                    tasks.get(taskNumber-1).markAsNotDone();
                    //print out the task that was marked as done
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(taskNumber-1));
                    //Get user input again
                    userInput = userScan.nextLine();          
                }

                //If userInput is "mark" get the task number and mark the task as done
                else if(Check.isMark(userInput)){
                    //get the task number
                    int taskNumber = Integer.parseInt(userInput.substring(5));
                    //if the task number is greater than the task count throw an exception
                    if(taskNumber>tasks.size()){
                        throw new IllegalArgumentException("The task number is greater than the number of tasks.");
                    }
                    //if the task number is less than 1 throw an exception
                    if(taskNumber<1){
                        throw new IllegalArgumentException("The task number is less than 1.");
                    }
                    //if there is no task at the task number throw an exception
                    if(tasks.get(taskNumber-1)==null){
                        throw new IllegalArgumentException("There is no task at the task number.");
                    }
                    //if the task is already done throw an exception
                    if(tasks.get(taskNumber-1).isDone==true){
                        throw new IllegalArgumentException("The task is already done.");
                    }
                    //mark the task as done
                    tasks.get(taskNumber-1).markAsDone();
                    //print out the task that was marked as done
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(taskNumber-1));
                    //Get user input again
                    userInput = userScan.nextLine();              
                }

                //If userInput is "todo" add a todo task to the list
                else if(Check.isTodo(userInput)){
                    //get the task name
                    String taskName = userInput.substring(5);
                    //If the task name is empty throw an exception
                    if(taskName.equals("")){
                        throw new IllegalArgumentException("The description of a todo cannot be empty.");
                    }
                    //create a todo task
                    tasks.add(new Todo (taskName));
                    //print out the task that was added
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size()-1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    //Get user input again
                    userInput = userScan.nextLine();  
                }

                //If userInput is "deadline" add a deadline task to the list
                else if(Check.isDeadline(userInput)){
                    //get the task name
                    String taskName = userInput.substring(9,userInput.indexOf("/"));
                    //get the deadline string without any "/"
                    String deadline = userInput.substring(userInput.indexOf("/")).replace("/","");
                    //If the task name is empty throw an exception
                    if(taskName.equals("")){
                        throw new IllegalArgumentException("The description of a deadline cannot be empty.");
                    }
                    //If the deadline is empty throw an exception
                    if(deadline.equals("")){
                        throw new IllegalArgumentException("The deadline of a deadline cannot be empty. Add a / argument to specify time.");
                    }
                    //create a deadline task
                    tasks.add(new Deadline(taskName,deadline));
                    //print out the task that was added
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size()-1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    userInput = userScan.nextLine();  
                }

                //If userInput is "event" add an event task to the list
                else if(Check.isEvent(userInput)){
                    //get the task name
                    String taskName = userInput.substring(6,userInput.indexOf("/"));
                    //get the event time without any "/"
                    String eventTime = userInput.substring(userInput.indexOf("/")).replace("/","");
                    //create an event task
                    //if the taskname is empty throw an exception
                    if(taskName.equals("")){
                        throw new IllegalArgumentException("The description of an event cannot be empty.");
                    }
                    //if the event time is empty throw an exception
                    if(eventTime.equals("")){
                        throw new IllegalArgumentException("The event time of an event cannot be empty. Add a / argument to specify time.");
                    }
                    //create an event task
                    tasks.add(new Event(taskName,eventTime));
                    //print out the task that was added
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size()-1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    //Get user input again
                    userInput = userScan.nextLine();  
                }

                //If userInput is not any of the commands throw an illegal argument exception
                else{
                    //throw an exception
                    throw new IllegalArgumentException("Invalid command. Please try again.");     
                }

            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                //Get user input again
                userInput = userScan.nextLine();  
            }
            catch (StringIndexOutOfBoundsException e){
               System.out.println("Invalid date entered. Please try again and enter / before the date.");
                //Get user input again
                userInput = userScan.nextLine();
            }
            
        }

        //Print out a goodbye message
        System.out.println("Bye. Hope to see you again soon!");
        
        //Close the scanner
        userScan.close();

        //Write the tasks to the file
        FileRW.writeToFile(tasks);
        
    }

}