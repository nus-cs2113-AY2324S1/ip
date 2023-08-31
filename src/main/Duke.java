import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {
    public static final String BOT_NAME = "JS";
    public static final String LINE_DIVIDER = "----------------------------------------";

    public static void markList(ArrayList<Task> toDoList, String position, boolean status) {
        try {
            int index = Integer.parseInt(position) - 1;
            Task event = toDoList.get(index);
            event.setCompleted(status);
            toDoList.set(index, event);
            if(event.isCompleted()) {
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + event.getName());
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + event.getName());
            }
        } catch (Exception exception) {
            System.out.println("Invalid Input");
        }
        
    }

    public static void printList(ArrayList<Task> toDoList, Iterator<Task> toDoListIter) {
        System.out.println("Here are the task in your list:");
        for(int i = 1; toDoListIter.hasNext() ; i++) {
            Task event = toDoListIter.next();
            System.out.print(i + ".[");
            if(event.isCompleted()) {
                System.out.print("X");
            } else {
                System.out.print(" ");
            }
            System.out.println("] " + event.getName());
        }
    }
    
    public static void main(String[] args) {
        ArrayList<Task> toDoList = new ArrayList<Task>();
        Iterator<Task> toDoListIter = toDoList.iterator();
        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE_DIVIDER);
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        while(!(userInput.equals("bye"))) {
            System.out.println(LINE_DIVIDER);
            if(userInput.split(" ").length == 1) {
                if(userInput.equals("list")) {
                    toDoListIter = toDoList.iterator();
                    printList(toDoList, toDoListIter);
                } else {
                    System.out.println("added: " + userInput);
                    toDoList.add(new Task(userInput));
                }
            } else {
                if (userInput.contains("unmark")) {
                    String[] userInputList = userInput.split(" ");
                    markList(toDoList, userInputList[1], false);
                } else if(userInput.contains("mark")) {

                    String[] userInputList = userInput.split(" ");
                    markList(toDoList, userInputList[1], true);
                }
            }
            System.out.println(LINE_DIVIDER);
            userInput = input.nextLine();
        }
        input.close();
        System.out.println(LINE_DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_DIVIDER);
    }
}
