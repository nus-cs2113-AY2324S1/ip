import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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
                " Hi Sir! I'm JARVIS \n" +
                " What can I do for you today?\n" +
                "____________________________________________________________\n" );

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while(!userInput.equals("bye")){
            System.out.println("____________________________________________________________");
            if(userInput.equals("list")){
                System.out.println("Here's your tasks!");
                for(int i = 0; i < taskList.size(); i++){
                    int indexNum = i + 1;
                    System.out.println(taskList.get(i).formatForList(indexNum));
                }
            }
            else if(userInput.startsWith("mark ")){
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                // begin index at end of "mark ", subtract 1 to index-0 format
                if(taskList.size() <= index){
                    System.out.println("Invalid task number " + (index + 1) + ". Try Again!");
                    // index + 1 to reflect back to index-1 format
                    System.out.println("____________________________________________________________");
                    userInput = sc.nextLine();
                    continue;
                }
                System.out.println("I've marked these tasks as done");
                taskList.get(index).markAsDone();
                System.out.println(taskList.get(index).formatForMark(index + 1));

            }
            else{
                System.out.println("added: " + userInput);
                taskList.add(new Task(userInput));
            }
            System.out.println("____________________________________________________________");
            userInput = sc.nextLine();
        }
        System.out.println("--------------------------------------\n" +
                "Good bye sir! Have a good day" + "\n"
                + "--------------------------------------");
    }
}
