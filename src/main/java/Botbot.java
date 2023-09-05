//import scanner
import java.util.Scanner;

public class Botbot {
    //create array for list
    public static Task[] list = new Task[100];
    public static int listSize = 0;

    public static void main(String[] args) {
        String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        //message
        System.out.println("Hello! I'm Botbot");
        System.out.println("What can I do for you?");
        System.out.println(line);

        //create new scanner object
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();
            //for command bye
            if (input.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            //for command list
            } else if(input.equals("list")) {
                for (int i = 0; i < listSize; i++) {
                    System.out.print((i + 1) + ". ");
                    list[i].printTask();
                }
                System.out.println(line);
            }else if(input.contains("mark")){
                int itemIndex; //int to store index of item to mark/unmark
                //for command unmark
                if(input.contains("unmark")){
                    //find the given index to unmark
                    itemIndex = Integer.parseInt(input.substring(7))-1;
                    //if given index is out of range
                    if (itemIndex>=listSize){
                        System.out.println("invalid list item");
                        return;
                    }
                    list[itemIndex].unmark();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    list[itemIndex].printTask();
                    System.out.println(line);
                } else { //for command mark
                    //find the given index to mark
                    itemIndex = Integer.parseInt(input.substring(5))-1;
                    //if given index is out of range
                    if (itemIndex>=listSize){
                        System.out.println("invalid list item");
                        return;
                    }
                    list[itemIndex].mark();
                    System.out.println("Nice! I've marked this task as done: ");
                    list[itemIndex].printTask();
                    System.out.println(line);
                }
            }else{
                //instantiate new Task object
                Task newTask = new Task(input);
                //Echo input
                System.out.println("Added: " + input);
                System.out.println(line);
                //edit list array
                list[listSize] = newTask;
                listSize++;
            }
        }

        //close scanner
        scanner.close();
    }
}
