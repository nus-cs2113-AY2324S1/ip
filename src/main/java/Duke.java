import java.util.Scanner;

public class Duke {
	static Task[] toDoList = new Task[100];
	static int size = 0;
	
    public static void main(String[] args) {
    	greetUser();
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        
        while (!command.equals("bye")) {
        	if (command.equals("list")) {
        		printToDoList();
        	} else if (command.contains("mark")) {
        		String[] mark = command.split(" ");
        		int index = Integer.parseInt(mark[1]) - 1;
        		if (mark[0].equals("mark")) {
        			toDoList[index].setDone(true);
        		} else {
        			toDoList[index].setDone(false);
        		}
        	} else {
        		toDoList[size++] = new Task(command);
        		respond(" added: " + command);
        	}
        	command = input.nextLine();
        }
        sayGoodbye();
        input.close();
    }
    
    public static void greetUser() {
    	 printLines();
    	 System.out.println("\n"
    	 		+ "    __________                      \n"
    	 		+ "    \\______   \\ ____   _____ ___.__.\n"
    	 		+ "     |       _// __ \\ /     <   |  |\n"
    	 		+ "     |    |   \\  ___/|  Y Y  \\___  |\n"
    	 		+ "     |____|_  /\\___  >__|_|  / ____|\n"
    	 		+ "            \\/     \\/      \\/\\/     \n"
    	 		+ "");
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
    	System.out.println("     " + "Here's your tasks:");
    	for (int i = 0; i < size; i++) {
    		System.out.println("     " + Integer.toString(i + 1) + "." + toDoList[i]);
    	}
    	printLines();
    }
}
