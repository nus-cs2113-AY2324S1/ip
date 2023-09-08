import java.util.Arrays;
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
        	} else {
        		String[] mark = command.split(" ");
        		if (mark[0].contains("mark")) {
            		int index = Integer.parseInt(mark[1]) - 1;
        			if (mark[0].equals("mark")) {
            			toDoList[index].setDone(true);
            		} else {
            			toDoList[index].setDone(false);
            		}
        		} else if (mark[0].equals("todo")) {
        			String description = String.join(" ", Arrays.copyOfRange(mark, 1, mark.length));
        			Task toDoTask = new ToDo(description);
        			addTask(toDoTask);
        		} else if (mark[0].equals("deadline")) {
        			String[] descAndDue = getDeadlineDescription(mark);
        			Task deadlineTask = new Deadline(descAndDue[0], descAndDue[1]);
        			addTask(deadlineTask);
        		} else if (mark[0].equals("event")) {
        			String[] info = getEventDescription(mark);
        			Task eventTask = new Event(info[0], info[1], info[2]);
        			addTask(eventTask);
        		} else {
        			System.out.println(command);
        		}
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
    
    public static void addTask(Task task) {
    	toDoList[size++] = task;
    	printLines();
    	//System.out.println("     Got it. I've added this task:\n       " + task.toString() + "\n     Now you have " + size + " tasks in the list.");
    	System.out.println("     Got it. I've added this task:");
    	System.out.println("       " + task);
    	System.out.println("     Now you have " + size + " tasks in the list.");
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
    
    public static String[] getDeadlineDescription(String[] description) {
    	String due = "";
    	String desc = "";
    	for (int i = 1; i < description.length; i++) {
    		if (!description[i].equals("/by")) {
    			desc += description[i] + " ";
    		} else {
    			due += String.join(" ", Arrays.copyOfRange(description, i + 1, description.length));
    			break;
    		}
    	}
    	String[] descAndDue = {desc, due};
    	return descAndDue;
    }
    
    public static String[] getEventDescription(String[] description) {
    	int fromIndex = 0;
    	int toIndex = 0;
    	for (int i = 0; i < description.length; i++) {
    		if (description[i].equals("/from")) {
    			fromIndex = i;
    		} else if (description[i].equals("/to")) {
    			toIndex = i;
    			break;
    		}
    	}
    	String desc = String.join(" ", Arrays.copyOfRange(description, 1, fromIndex));
    	String from = String.join(" ", Arrays.copyOfRange(description, fromIndex + 1, toIndex));
    	String to = String.join(" ", Arrays.copyOfRange(description, toIndex + 1, description.length));
    	String[] info = {desc, from, to};
    	return info;
    }
}
