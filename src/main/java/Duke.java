import java.util.Scanner;

public class Duke {
    private static Task[] records = new Task[100];
    private static int recordsNum = 0;

    public static String generateResponse(String input){
        String endMessage = "Bye. Hope to see you again soon!";

        String trimmedInput = input.trim();
        if(trimmedInput.equals("bye")){
            return endMessage;
        }

        if(trimmedInput.equals("list")){
            System.out.println("\t____________________________________________________________");
            for(int i = 0; i < recordsNum; i++){
                System.out.println("\t" + Integer.toString(i + 1) + ". [" + records[i].getStatusIcon() + "] " + records[i].getDescription());
            }
            System.out.println("\t____________________________________________________________");
            return "";
        }

        if(trimmedInput.contains("unmark")){
            int numIdx = trimmedInput.indexOf(" ");
            int taskNum = Integer.parseInt(trimmedInput.substring(numIdx+1));
            records[taskNum-1].setUndone();
            System.out.println("\t____________________________________________________________");
            for(int i = 0; i < recordsNum; i++){
                System.out.println("\t" + Integer.toString(i + 1) + ". [" + records[i].getStatusIcon() + "] " + records[i].getDescription());
            }
            System.out.println("\t____________________________________________________________");
            return "";
        }

        if(trimmedInput.contains("mark")){
            int numIdx = trimmedInput.indexOf(" ");
            int taskNum = Integer.parseInt(trimmedInput.substring(numIdx+1));
            records[taskNum-1].setDone();
            System.out.println("\t____________________________________________________________");
            for(int i = 0; i < recordsNum; i++){
                System.out.println("\t" + Integer.toString(i + 1) + ". [" + records[i].getStatusIcon() + "] " + records[i].getDescription());
            }
            System.out.println("\t____________________________________________________________");
            return "";
        }
        Task task = new Task(input);
        records[recordsNum] = task;
        recordsNum ++;
        return "added: " + input;
    }

    public static void printMessage(String message){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
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

