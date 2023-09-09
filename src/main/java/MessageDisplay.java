import java.util.Scanner;  // Import the Scanner class
public class MessageDisplay {
    static Scanner input = new Scanner(System.in);  // Create a Scanner object
    static String logo = "     ____        _        \n"
            + "    |  _ \\ _   _| | _____ \n"
            + "    | | | | | | | |/ / _ \\\n"
            + "    | |_| | |_| |   <  __/\n"
            + "    |____/ \\__,_|_|\\_\\___|\n";
    public static void displayOpeningMessage(){
        System.out.println("    Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    今日は! I am Rene Kokoro!");
        System.out.println("    Let me record your tasks!! *blushes*");
        System.out.println("    ____________________________________________________________");
    }

//    public static void echo(){
//        String userInput = "";
//        while(!userInput.equals("bye")){
//            userInput = input.nextLine();r
//            System.out.println("    ____________________________________________________________");
//            System.out.println("OuO you said " + userInput + "!");
//            System.out.println("    ____________________________________________________________");
//        }
//        displayCLosingMessage();
//    }

    public static void addInput(){
        String userInput = "";
        String taskName = "";
        int taskIndex = 0;
        while(!userInput.equals("bye")){
            userInput = input.nextLine();
            System.out.println("    ____________________________________________________________");
            try{
                switch (userInput.split(" ")[0]) {
                    case "bye":
                        break;
                    case "list":
                        TaskList.printTaskList();
                        System.out.println("    ____________________________________________________________");
                        break;
                    case "mark":
                        try{
                            taskIndex = Integer.parseInt(userInput.split("mark")[1].strip());
                            taskName = TaskList.viewTaskByIndex(taskIndex-1);
                            TaskList.markTaskAsDone(taskIndex);
                            if(taskName.equals("Task Not Found")){
                                break;
                            }
                            System.out.printf("    Roger that! I have marked the following task as done >w< !\n" +
                                    "      '%s'\n", taskName);
                            System.out.println("    ____________________________________________________________");
                            break;
                        }
                        catch(NumberFormatException invalidIndex){
                            System.out.println("    Pwease enter valid integer index!");
                            System.out.println("    ____________________________________________________________");
                            break;
                        }
                    case "unmark":
                        try{
                            taskIndex = Integer.parseInt(userInput.split("unmark")[1].strip());
                            taskName = TaskList.viewTaskByIndex(taskIndex-1);
                            TaskList.markTaskAsNotDone(taskIndex);
                            if(taskName.equals("Task Not Found")){
                                break;
                            }
                            System.out.printf("    Roger that! I have unmarked the following task as done >w< !\n" +
                                    "      '%s'\n", taskName);
                            System.out.println("    ____________________________________________________________");
                            break;
                        }
                        catch(NumberFormatException invalidIndex){
                            System.out.println("    Pwease enter valid integer index!");
                            System.out.println("    ____________________________________________________________");
                            break;
                        }
                    case "todo":
                        TaskList.addToTaskList(userInput, Task.TaskType.TODO);
                        break;
                    case "deadline":
                        TaskList.addToTaskList(userInput, Task.TaskType.DEADLINE);
                        break;
                    case "event":
                        TaskList.addToTaskList(userInput, Task.TaskType.EVENT);
                        break;
                    default:
                        throw new ReneExceptions("Invalid Input");
                }
            } catch (ReneExceptions exception){
                String exceptionMessage = exception.getMessage();
                if(exceptionMessage.equals("Invalid Input")){
                    System.out.println("    Pwease enter a valid command :0");
                    System.out.println("    Valid commands are: todo, deadline: /by time, event: /from start /to end, bye");
                    System.out.println("    ____________________________________________________________");
                }

            }

        }
        displayCLosingMessage();
    }

    public static void displayCLosingMessage(){
        System.out.println("    Aww you are leaving? *sniffs*");
        System.out.println("    Well... hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}

