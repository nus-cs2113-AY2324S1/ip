import java.util.Scanner;  // Import the Scanner class
import java.io.File; // Import the File class

public class Duke {

    //Initialize create a list of tasks
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;

    public static void main(String[] args) {

        //check if there is a file to read from
        try{
            File file = new File("data/duke.txt");
            Scanner fileScan = new Scanner(file);
            while(fileScan.hasNextLine()){
                String line = fileScan.nextLine();
                String[] lineSplit = line.split(" \\| ");
                if(lineSplit[0].equals("T")){
                    tasks[taskCount] = new Todo(lineSplit[2]);
                    if(lineSplit.length > 3){
                        throw new DukeException("Your data file is corrupted");
                    }
                    if(lineSplit[1].equals("true")){
                        tasks[taskCount].markAsDone();
                    }
                    taskCount++;
                }else if(lineSplit[0].equals("D")){
                    if(lineSplit.length > 4){
                        throw new DukeException("Your data file is corrupted");
                    }
                    tasks[taskCount] = new Deadline(lineSplit[2], lineSplit[3]);
                    if(lineSplit[1].equals("true")){
                        tasks[taskCount].markAsDone();
                    }
                    taskCount++;
                }else if(lineSplit[0].equals("E")){
                    if(lineSplit.length > 4){
                        throw new DukeException("Your data file is corrupted");
                    }
                    tasks[taskCount] = new Event(lineSplit[2], lineSplit[3]);
                    if(lineSplit[1].equals("true")){
                        tasks[taskCount].markAsDone();
                    }
                    taskCount++;
                }
            }
            //print the tasks in the file
            
            fileScan.close();
        //otherwise create a new file
        } catch(Exception e){
            //Create a new file and directory
            try{
                File file = new File("data/duke.txt");
                file.getParentFile().mkdirs();
                file.createNewFile();
            }catch(Exception e1){
                e1.printStackTrace();
            }
        }

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
                    for(int i=0;i<taskCount;i++){
                        System.out.println((i+1) + ". " + tasks[i]);
                    }
                    //Get user input again
                    userInput = userScan.nextLine();  
                }

                //If userInput is "unmark" get the task number and unmark the task as done
                else if(Check.isUnmark(userInput)){
                    //get the task number
                    int taskNumber = Integer.parseInt(userInput.substring(7));
                    //if the task number is greater than the task count throw an exception
                    if(taskNumber>taskCount){
                        throw new IllegalArgumentException("The task number is greater than the number of tasks.");
                    }
                    //if the task number is less than 1 throw an exception
                    if(taskNumber<1){
                        throw new IllegalArgumentException("The task number is less than 1.");
                    }
                    //if there is no task at the task number throw an exception
                    if(tasks[taskNumber-1]==null){
                        throw new IllegalArgumentException("There is no task at the task number.");
                    }
                    //if the task is already not done throw an exception
                    if(tasks[taskNumber-1].isDone==false){
                        throw new IllegalArgumentException("The task is already not done.");
                    }
                    //mark the task as done
                    tasks[taskNumber-1].markAsNotDone();
                    //print out the task that was marked as done
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[taskNumber-1]);
                    //Get user input again
                    userInput = userScan.nextLine();          
                }

                //If userInput is "mark" get the task number and mark the task as done
                else if(Check.isMark(userInput)){
                    //get the task number
                    int taskNumber = Integer.parseInt(userInput.substring(5));
                    //if the task number is greater than the task count throw an exception
                    if(taskNumber>taskCount){
                        throw new IllegalArgumentException("The task number is greater than the number of tasks.");
                    }
                    //if the task number is less than 1 throw an exception
                    if(taskNumber<1){
                        throw new IllegalArgumentException("The task number is less than 1.");
                    }
                    //if there is no task at the task number throw an exception
                    if(tasks[taskNumber-1]==null){
                        throw new IllegalArgumentException("There is no task at the task number.");
                    }
                    //if the task is already done throw an exception
                    if(tasks[taskNumber-1].isDone==true){
                        throw new IllegalArgumentException("The task is already done.");
                    }
                    //mark the task as done
                    tasks[taskNumber-1].markAsDone();
                    //print out the task that was marked as done
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[taskNumber-1]);
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
                    tasks[taskCount] = new Todo(taskName);
                    //increment the task count
                    taskCount++;
                    //print out the task that was added
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount-1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
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
                    tasks[taskCount] = new Deadline(taskName,deadline);
                    //increment the task count
                    taskCount++;
                    //print out the task that was added
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount-1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    //Get user input again
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
                    tasks[taskCount] = new Event(taskName,eventTime);
                    //increment the task count
                    taskCount++;
                    //print out the task that was added
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount-1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
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
        FileRW.writeToFile(tasks, taskCount);
        
    }

}