import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Scanner scanner = new Scanner(System.in);

    public static String getUserInput() {
        return scanner.nextLine();
    }

    public static void printUserInput(String output, int listIndex, ArrayList<String> items) {
        if(output.toLowerCase().equals("bye")){
            System.out.println("____________________________________________________________\n" +
                    " Bye. Hope to see you again soon! " + "\n" +
                    "____________________________________________________________\n");
        }
        else if(output.toLowerCase().equals("list")){
            System.out.println("____________________________________________________________\n");
            displayList(listIndex, items);
            System.out.println("____________________________________________________________\n");
        }
        else{
            System.out.println("____________________________________________________________\n" +
                    " added: " + output + "\n" +
                    "____________________________________________________________\n");
        }
    }

    public static void displayList(int listIndex, ArrayList<String> items){
        // print out the full list with indexing
        for(int i = 0; i < listIndex; i++){
            System.out.println(i+1 + ". " + items.get(i) + "\n");
        }
    }

    public static void closeScanner() {
        scanner.close();
    }

    public static void main(String[] args) {

        String logo = "\n" +
        "     ____.  _____ ______________   ____.___  _________\n" +
        "    |    | /  _  \\\\______   \\   \\ /   /|   |/   _____/\n" +
        "    |    |/  /_\\  \\|       _/\\   Y   / |   |\\_____  \\ \n" +
        "/\\__|    /    |    \\    |   \\ \\     /  |   |/        \\\n" +
        "\\________\\____|__  /____|_  /  \\___/   |___/_______  /\n" +
        "                 \\/       \\/                       \\/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm JARVIS \n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n" );

        ArrayList<String> taskList = new ArrayList<>(); // Array of tasks (Strings)

        String userInput;
        int indexList = 0; // index for list of user inputs
//        t.markAsDone();
//        System.out.println(t.getStatusIcon());

        do{
            userInput = getUserInput();
            if(!userInput.equals("list")){
                Task t = new Task(userInput); // add a new task
//                taskList[indexList] = userInput;
                taskList.add(userInput);
                indexList++;
            }
            printUserInput(userInput, indexList, taskList);
        }while(!userInput.equals("bye"));
        closeScanner();


    }
}
