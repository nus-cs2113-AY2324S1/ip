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
        String taskNumString = trimmedInput.substring(numStartIdx);
        int taskNum;

        if(trimmedInput.contains("unmark")){
            taskNum = Integer.parseInt(taskNumString);
            records[taskNum-1].setUndone();
            printAllTasks();
            return "";
        }
        if(trimmedInput.contains("mark")){
            taskNum = Integer.parseInt(taskNumString);
            records[taskNum-1].setDone();
            printAllTasks();
            return "";
        }
        return createNewTask(input);
    }

    private static String createNewTask(String input) {
        Task todo = new Task(input);
        records[recordsNum] = todo;
        recordsNum++;
        return "added: " + input;
    }

    private static void printAllTasks() {
        printLine();
        for(int i = 0; i < recordsNum; i++){
            String taskIndexString = Integer.toString(i + 1);
            String checkBox = "[" + records[i].getStatusIcon() + "]";
            String taskDescription = records[i].getDescription();
            System.out.println("\t " + taskIndexString + " " + checkBox + " " + taskDescription );
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

