import java.util.Scanner;

public class Duke {
	static String[] toDoList = new String[100];
	static int size = 0;
	
    public static void main(String[] args) {
    	greetUser();
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        
        while (!command.equals("bye")) {
        	if (command.equals("list"))
        		printToDoList();
        	else {
        		toDoList[size++] = command;
        		respond("added: " + command);
        	}
        	command = input.nextLine();
        }
        sayGoodbye();
    }
    
    public static void greetUser() {
    	 printLines();
         System.out.println("    Hello! I'm Remy");
         System.out.println("    What can I do for you?");
         printLines();
    }
    
    public static void printLines() {
    	System.out.println("    ____________________________________________________________");
    }
    
    public static void respond(String response) {
    	printLines();
    	System.out.println("    " + response);
    	printLines();
    	System.out.println();
    }
    
    public static void sayGoodbye() {
    	printLines();
        System.out.println("    Bye. Hope to see you again soon!");
        printLines();
    }
    
    public static void printToDoList() {
    	printLines();
    	for (int i = 0; i < size; i++)
    		System.out.println("    " + Integer.toString(i + 1) + ". " + toDoList[i]);
    	printLines();
    }
}
