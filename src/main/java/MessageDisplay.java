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
        int taskIndex = 0;
        while(!userInput.equals("bye")){
            userInput = input.nextLine();
            System.out.println("    ____________________________________________________________");
            switch (userInput.split(" ")[0]) {
                case "bye":
                    break;
                case "list":
                    TaskList.printTaskList();
                    System.out.println("    ____________________________________________________________");
                    break;
                case "mark":
                    taskIndex = Integer.parseInt(userInput.split("mark")[1].strip());
                    TaskList.markTaskAsDone(taskIndex);
                    System.out.printf("    Roger that! I have marked the following task as done >w< !\n" +
                            "      '%s'\n", TaskList.viewTaskByIndex(taskIndex));
                    System.out.println("    ____________________________________________________________");
                    break;
                case "unmark":
                    taskIndex = Integer.parseInt(userInput.split("unmark")[1].strip());
                    TaskList.markTaskAsNotDone(taskIndex);
                    System.out.printf("    Roger that! I have unmarked the following task as done >w< !\n" +
                            "      '%s'\n", TaskList.viewTaskByIndex(taskIndex));
                    System.out.println("    ____________________________________________________________");
                    break;
                case "todo":
                    TaskList.addToTaskList(userInput, Task.TaskType.TODO);
                    System.out.println("    I have added the following task OwO:");
                    System.out.printf("      [T][] %s\n", TaskList.viewTaskByIndex(TaskList.getTaskListSize() - 1));
                    System.out.println("    Now you have " + TaskList.getTaskListSize() + " tasks in the list! UWU");
                    System.out.println("    ____________________________________________________________");
                    break;
                case "deadline":
                    TaskList.addToTaskList(userInput, Task.TaskType.DEADLINE);
                    System.out.println("    I have added the following task OwO:");
                    System.out.printf("      [D][] %s\n", TaskList.viewTaskByIndex(TaskList.getTaskListSize() - 1));
                    System.out.println("    Now you have " + TaskList.getTaskListSize() + " tasks in the list! UWU");
                    System.out.println("    ____________________________________________________________");
                    break;
                case "event":
                    TaskList.addToTaskList(userInput, Task.TaskType.EVENT);
                    System.out.println("    I have added the following task OwO:");
                    System.out.printf("      [E][] %s\n", TaskList.viewTaskByIndex(TaskList.getTaskListSize() - 1));
                    System.out.println("    Now you have " + TaskList.getTaskListSize() + " tasks in the list! UWU");
                    System.out.println("    ____________________________________________________________");
                    break;
                default:
                    System.out.println("    Pwease enter a valid command :0");
                    System.out.println("    ____________________________________________________________");
                    break;
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
