import java.util.Scanner;

public class Duke {
    private static Task[] records = new Task[100];
    private static int recordsNum = 0;
    public static void generateResponse(String input){

        String[] commandDetails = input.split(" ", 2);
        String instructionString = commandDetails[0];

        if(instructionString.equals("list")){
            executeListCommand();
            return;
        }

        String removedInstructionString = "";
        try {
            removedInstructionString = commandDetails[1];
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Please ensure that " + instructionString
                    + " has the correct number of parameters.");
            return;
        }

        switch(instructionString) {
        case ("unmark"):
            executeUnmarkCommand(removedInstructionString);
            break;
        case ("mark"):
            executeMarkCommand(removedInstructionString);
            break;
        case ("todo"):
            createNewTask("todo", removedInstructionString);
            break;
        case ("deadline"):
            createNewTask("deadline", removedInstructionString);
            break;
        case ("event"):
            createNewTask("event", removedInstructionString);
            break;
        default:
            System.out.println("Invalid Command");
        }
    }

    private static void executeMarkCommand(String removedInstructionString) {
        int taskNum;
        try {
            taskNum = Integer.parseInt(removedInstructionString);
            records[taskNum - 1].setDone();
            executeListCommand();
        }catch(NumberFormatException e){
            System.out.println("Please enter an integer");
        }catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
            if (recordsNum == 0) {
                System.out.println("Please create a task to continue");
            } else if(recordsNum == 1){
                System.out.println("There are " + recordsNum + " task.");
                System.out.println("Please enter 'mark 1' to check the first task as completed");
            } else {
                System.out.println("There are " + recordsNum + " tasks.");
                System.out.println("Please enter a valid number from 1 to "
                        + recordsNum + "(inclusive).");
            }
        }
    }

    private static void executeUnmarkCommand(String removedInstructionString) {
        int taskNum;
        try {
            taskNum = Integer.parseInt(removedInstructionString);
            records[taskNum - 1].setUndone();
            executeListCommand();
        }catch(NumberFormatException e){
            System.out.println("Please enter an integer");
        }catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
            if (recordsNum == 0) {
                System.out.println("Please create a task to continue");
            } else if(recordsNum == 1){
                System.out.println("There are " + recordsNum + " task.");
                System.out.println("Please enter 'mark 1' to check the first task as completed");
            } else {
                System.out.println("There are " + recordsNum + " tasks.");
                System.out.println("Please enter a valid number from 1 to "
                        + recordsNum + "(inclusive).");
            }
        }
    }

    private static void createNewTask(String instructionString, String removedInstructionString) {
        Task task = null;
        String taskDescription;
        try {
            switch (instructionString) {
            case "todo":
                task = createToDo(removedInstructionString);
                break;
            case "deadline":
                task = createDeadline(removedInstructionString);
                break;
            case "event":
                task = createEvent(removedInstructionString);
                break;
            default:
                System.out.println("Command is invalid!");
            }
            addTaskToList(task);
            printTaskAdded(task);
        } catch(StringIndexOutOfBoundsException e){
            System.out.println("Please ensure that " + instructionString
                    + " has the correct number of parameters.");
        }

    }

    private static void printTaskAdded(Task task) {
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("\tNow you have " + recordsNum + " tasks in the list." );
        printLine();
    }

    private static void addTaskToList(Task task) {
        records[recordsNum] = task;
        recordsNum++;
    }

    private static Task createEvent(String removedInstructionString) {
        String startDateIndicator = "/from";
        String endDateIndicator = "/to";

        int fromIndex = removedInstructionString.indexOf(startDateIndicator);
        int toIndex = removedInstructionString.indexOf(endDateIndicator);

        String taskDescription = removedInstructionString.substring(0,fromIndex).trim();
        String fromDate = removedInstructionString.substring(fromIndex + startDateIndicator.length(), toIndex).trim();
        String toDate = removedInstructionString.substring(toIndex + endDateIndicator.length());

        return new Event(taskDescription, fromDate, toDate);
    }

    private static Task createDeadline(String removedInstructionString) {
        String dateIndicator = "/by";
        int byIndex = removedInstructionString.indexOf(dateIndicator);
        String taskDescription = removedInstructionString.substring(0,byIndex).trim();
        String byDate = removedInstructionString.substring(byIndex + dateIndicator.length()).trim();
        return new Deadline(taskDescription, byDate);
    }

    private static Task createToDo(String taskDescription) {
        return new ToDo(taskDescription);
    }

    private static void executeListCommand() {
        printLine();
        for(int i = 0; i < recordsNum; i++){
            System.out.println(records[i]);
        }
        printLine();
    }
    public static void printMessage(String message){
        printLine();
        System.out.println("\t" + message);
        printLine();
    }
    private static void printLine(){
        System.out.println("\t____________________________________________________________");
    }
    private static void interactWithUser() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while(! line.trim().equals("bye")){
            generateResponse(line);
            line = in.nextLine();
        }
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String startMessage = "____________________________________________________________\n"+
                 "Hello! I'm Chatty\n"+
                 "What can I do for you?\n"+
                "____________________________________________________________";


        System.out.println("Hello from\n" + logo);
        System.out.println(startMessage);
    }

    private static void printByeMessage(){
        String endMessage = "Bye. Hope to see you again soon!";
        printMessage(endMessage);
    }
    public static void main(String[] args) {
        printWelcomeMessage();
        interactWithUser();
        printByeMessage();
    }
}

