import java.util.Scanner;

public class Duke {
    static private Task[] taskList = new Task[100];
    static private int numTasks = 0;

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) throws InputException{

        System.out.println("Hello! I'm TheChattyFatty");

        Scanner scanner = new Scanner(System.in);

        while(true) {
            printLine();
            System.out.println("What can I do for you?");
            System.out.println("Functionality:");
            System.out.println("todo [task name] (Creates a new todo)");
            System.out.println("deadline [task name] (Creates a new deadline)");
            System.out.println("event [event name] (Creates a new event)");
            System.out.println("mark [task number] (Marks todo/event/deadline as done");
            System.out.println("unmark [task number (Marks todo/event/deadline as not done yet");
            System.out.println("list (displays all todos/events/deadlines)");
            String response = scanner.nextLine();

            // For handling keyword responses with multiple words
            String[] words = response.split(" ");

            String keyword = words[0];

            // Handle "bye" keyword
            if(response.equals("bye")) {
                break;
            }

            // Handle "list" keyword
            else if(response.equals("list")) {
                for (int i = 0; i < numTasks; i++) {
                    taskList[i].show();
                }
            }

            else if(keyword.equals("mark")){
                // Check exception: number of words is not 2
                if(words.length != 2){
                    throw new InputException("Input Exception: Please enter with correct format (mark [Integer])");
                }
                // Check exception: second word cannot be converted to integer or integer out of bounds
                try{
                    int markIndex = Integer.parseInt(words[1]);

                    if(markIndex < 1 || markIndex > numTasks){
                        throw new InputException("Input Exception: Please enter a positive integer less than or equal to current number of tasks (" + numTasks + " task(s))");
                    }

                    taskList[markIndex - 1].mark();
                }
                catch(NumberFormatException e){
                    throw new InputException("Input Exception: Please enter with correct format (mark [Integer])");
                }
            }

            else if(keyword.equals("unmark")){
                // Check exception: number of words is not 2
                if(words.length != 2){
                    throw new InputException("Input Exception: Please enter with correct format (unmark [Integer])");
                }

                // Check exception: second word cannot be converted to integer or integer out of bounds
                try{
                    int markIndex = Integer.parseInt(words[1]);

                    if(markIndex < 1 || markIndex > numTasks){
                        throw new InputException("Input Exception: Please enter a positive integer less than or equal to current number of tasks (" + numTasks + ")");
                    }

                    taskList[markIndex - 1].unmark();
                }
                catch(NumberFormatException e){
                    throw new InputException("Input Exception: Please enter with correct format (unmark [Integer])");
                }
            }

            else if(keyword.equals("todo")){
                String description = response.substring(5);
                taskList[numTasks] = new ToDo(description);
                numTasks++;

                System.out.println("Created new ToDo:");
                System.out.println(description);
            }

            else if(keyword.equals("deadline")){
                String description = response.substring(9);
                System.out.println("Please enter the deadline:");
                String deadline = scanner.nextLine();

                taskList[numTasks] = new Deadline(description, deadline);
                numTasks++;

                System.out.println("Created new Deadline:");
                System.out.println(description);
                System.out.println("Due: " + deadline);
            }

            else if(keyword.equals("event")) {
                String description = response.substring(6);
                System.out.println("Please enter event start period:");
                String start = scanner.nextLine();
                System.out.println("Please enter event end period:");
                String end = scanner.nextLine();

                taskList[numTasks] = new Event(description, start, end);
                numTasks++;

                System.out.println("Created new Event:");
                System.out.println(description);
                System.out.println("From: " + start);
                System.out.println("To: " + end);
            }

            else{
                throw new InputException("Input Exception: Invalid input keyword");
            }

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}