import java.util.Scanner;

public class Duke {
    private static Task[] records = new Task[100];
    private static int recordsNum = 0;

    public static String generateResponse(String input){

        String trimmedInput = input.trim();
        String endMessage = "Bye. Hope to see you again soon!";

        if(trimmedInput.equals("bye")){
            return endMessage;
        }

        if(trimmedInput.equals("list")){
            printAllTasks();
            return "";
        }

        int spaceIdx = trimmedInput.indexOf(" ");
        int numStartIdx = spaceIdx + 1;
        String removedInstructionString = trimmedInput.substring(numStartIdx);
        String instructionString = trimmedInput.substring( 0, spaceIdx);
        int taskNum;

        if(instructionString.contains("unmark")){
            taskNum = Integer.parseInt(removedInstructionString);
            records[taskNum-1].setUndone();
            printAllTasks();
            return "";
        }
        if(instructionString.contains("mark")){
            taskNum = Integer.parseInt(removedInstructionString);
            records[taskNum-1].setDone();
            printAllTasks();
            return "";
        }
        createNewTask(instructionString, removedInstructionString);
        return "";
    }

    private static void createNewTask(String instructionString, String removedInstructionString) {
        Task task;
        String taskDescription;
        switch(instructionString) {
        case "todo":
            taskDescription = removedInstructionString;
            task = new ToDo(taskDescription);
            break;
        case "deadline":
            String dateIndicator = "/by";
            int byIndex = removedInstructionString.indexOf(dateIndicator);
            taskDescription = removedInstructionString.substring(0,byIndex).trim();
            String byDate = removedInstructionString.substring(byIndex + dateIndicator.length()).trim();
            task = new Deadline(taskDescription, byDate);
            break;
        case "event":
            String startDateIndicator = "/from";
            String endDateIndicator = "/to";
            int fromIndex = removedInstructionString.indexOf(startDateIndicator);
            int toIndex = removedInstructionString.indexOf(endDateIndicator);
            taskDescription = removedInstructionString.substring(0,fromIndex).trim();
            String fromDate = removedInstructionString.substring(fromIndex + startDateIndicator.length(), toIndex).trim();
            String toDate = removedInstructionString.substring(toIndex + endDateIndicator.length());

            task = new Event(taskDescription, fromDate, toDate);//need to divide the string and get the contents
            break;
        default:
            return ;
        }
        records[recordsNum] = task;
        recordsNum++;
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("\tNow you have " + recordsNum + " tasks in the list." );
        printLine();
        return ;
    }

    private static void printAllTasks() {
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
    public static void main(String[] args) {

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
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String response;
        while(! line.trim().contains("bye")){

            response = generateResponse(line);
            if(!response.isEmpty()) {
                printMessage(response);
            }
            line = in.nextLine();

        }
        response = generateResponse("bye");
        printMessage(response);
    }
}

