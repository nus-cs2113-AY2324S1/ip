import java.util.Scanner;

public class Duke {
    static private List taskList = new List();

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args){

        System.out.println("Hello! I'm TheChattyFatty");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while(true) {
            // Get user input
            String response = scanner.nextLine();

            // Handle basic response cases
            if (response.equals("bye")) {
                break;
            }
            else if (response.equals("list")) {
                taskList.show();
                continue;
            }

            // Split user input into array of words
            String[] words = response.split(" ");

            // Handling mark case
            if(words[0].equals("mark")) {
                // Handle first exception case: number of elements not equal to 2
                if (words.length != 2) {
                    taskList.add(response);
                    continue;
                }

                // Handle second exception case: second element is not an int
                try {
                    int markIndex = Integer.parseInt(words[1]);
                    taskList.mark(markIndex);
                }
                catch (NumberFormatException e) {
                    System.out.println("Please enter am integer value for the index");
                }
            }
            else if(words[0].equals("unmark")){
                // Handle first exception case: number of elements not equal to 2
                if (words.length != 2){
                    taskList.add(response);
                    continue;
                }

                // Handle second exception case: second element is not an int
                try {
                    int unmarkIndex = Integer.parseInt(words[1]);
                    taskList.unmark(unmarkIndex);
                }
                catch (NumberFormatException e){
                    System.out.println("Please enter an integer value for the index");
                }
            }

            // Default case, add to list
            else{
                taskList.add(response);
            }
        }


        System.out.println("Bye. Hope to see you again soon!");
    }
}