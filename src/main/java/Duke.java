import java.util.Scanner;

public class Duke {
    public static String generateResponse(String input){
        String endMessage = "Bye. Hope to see you again soon!";

        String trimmedInput = input.trim();
        if(trimmedInput.equals("bye")){
            return endMessage;
        }
        return input;
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
                 "Hello! I'm [YOUR CHATBOT NAME]\n"+
                 "What can I do for you?\n"+
                "____________________________________________________________";


        System.out.println("Hello from\n" + logo);
        System.out.println(startMessage);
        Scanner in = new Scanner(System.in);
        String line;
        String response;
        do{
            line = in.nextLine();
            response = generateResponse(line);
            printMessage(response);

        }while(line.equals(response));




    }
}
