import java.util.Scanner;

public class itay {

    static String[] tasks = new String[100];
    static int numTasks = 0;

    public static void Respond(String line){
        if(! line.equals("list")) {
            addResponse(line);           
        } else {
            for(int i = 0; i < numTasks ; i++){
                System.out.println(i + 1 + ". " + tasks[i]);
            }
        }
    }
    
    public static void addResponse(String line){
        tasks[numTasks] = line;
        numTasks++;
        System.out.println("added: " + line);   
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // greet
        System.out.println("Hello! I'm Itay");
        System.out.println("What can i do for you?");

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        line = line.trim();

        while(! line.equals("bye")) {
            Respond(line);
            line = in.nextLine();
            line = line.trim();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
