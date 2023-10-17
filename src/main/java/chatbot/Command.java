package chatbot;

import java.util.ArrayList;

public class Command {
    private Storage storage;
    private String commandType;
    private String input;
    public Command(String commandType, String input) {
        this.storage = new Storage("./tasklist.txt");
        this.commandType = commandType;
        this.input = input;
    }
    public void execute(ArrayList<Task> tasks, boolean isUserInput) throws ChatbotUnknownCommandException, ChatbotEmptyDescException {

        if (this.commandType.equals("list")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                //System.out.println(" " + (i + 1) + ".[" + tasks[i].getTypeIcon() + "][" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
            System.out.println("____________________________________________________________");
        } else if (this.commandType.equals("mark")) {
            String number = input.replace("mark ", "").trim();
            int markTaskNo = Integer.parseInt(number);
            if (markTaskNo > 0) {
                tasks.get(markTaskNo - 1).markAsDone();
            }
            if (isUserInput) {
                storage.saveToFile(input);
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   [" + tasks.get(markTaskNo - 1).getStatusIcon() + "] " + tasks.get(markTaskNo - 1).getDescription());
                System.out.println("____________________________________________________________");
            }

        } else if (this.commandType.equals("unmark")) {
            String number = input.replace("unmark ", "").trim();
            int unmarkedTaskNo = Integer.parseInt(number);
            if (unmarkedTaskNo > 0) {
                tasks.get(unmarkedTaskNo - 1).markAsUndone();
            }
            if (isUserInput) {
                storage.saveToFile(input);
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   [" + tasks.get(unmarkedTaskNo - 1).getStatusIcon() + "] " + tasks.get(unmarkedTaskNo - 1).getDescription());
                System.out.println("____________________________________________________________");
            }

        } else if (this.commandType.equals("todo")) {
            String msg = input.replace("todo", "").trim();
            if (msg.isEmpty()) {
                throw new ChatbotEmptyDescException(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }

            Task task = new Todo(msg);
            tasks.add(task);

            if (isUserInput) {
                storage.saveToFile(input);
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + task);
                System.out.println(" Now you have " + String.valueOf(tasks.size()) + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }

        } else if (this.commandType.equals("deadline")) {
            String msg = input.replace("deadline", "").trim();
            if (msg.isEmpty()) {
                throw new ChatbotEmptyDescException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            }

            String byDate = msg.substring(msg.indexOf("/by ") + 4).trim(); // will contain the byDate
            String desc = msg.substring(0, msg.indexOf("/by")); // will contain the deadline description

            Task task = new Deadline(desc, byDate);
            tasks.add(task);

            if (isUserInput) {
                storage.saveToFile(input);
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + task);
                System.out.println(" Now you have " + String.valueOf(tasks.size()) + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }

        } else if (this.commandType.equals("event")) {
            String msg = input.replace("event", "").trim();
            if (msg.isEmpty()) {
                throw new ChatbotEmptyDescException(" ☹ OOPS!!! The description of a event cannot be empty.");
            }

            String dateRange = msg.substring(msg.indexOf("/from ") + 6).trim();
            String fromDate = dateRange.substring(0, dateRange.indexOf("/to ")).trim();
            String toDate = dateRange.substring(dateRange.indexOf("/to ") + 4).trim();
            String desc = msg.substring(0, msg.indexOf("/from ")); // will contain the deadline description

            Task task = new Event(desc, fromDate, toDate);
            tasks.add(task);

            if (isUserInput) {
                storage.saveToFile(input);
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + task);
                System.out.println(" Now you have " + String.valueOf(tasks.size()) + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }

        } else if (this.commandType.equals("delete")) {
            String msg = input.replace("delete", "").trim();
            if (msg.isEmpty()) {
                throw new ChatbotEmptyDescException(" ☹ OOPS!!! The description of delete cannot be empty.");
            }
            int indexToRemove = Integer.parseInt(msg);
            if( indexToRemove > 0 ) {
                indexToRemove -= 1;
            }
            Task task = tasks.get(indexToRemove);
            tasks.remove(indexToRemove);
            if (isUserInput) {
                storage.saveToFile(input);
                System.out.println("____________________________________________________________");
                System.out.println(" Noted. I've removed this task: ");
                System.out.println("   " + task);
                System.out.println(" Now you have " + String.valueOf(tasks.size()) + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }
        } else if (this.commandType.equals("find")) {
            String searchFor = input.replace("find", "").trim();
            if (searchFor.isEmpty()) {
                throw new ChatbotEmptyDescException(" ☹ OOPS!!! The find command needs a string to search for.");
            }
            System.out.println("____________________________________________________________");
            System.out.println(" Here are the matching tasks in your list:");

            for(Task task : tasks) {
                String desc = task.getDescription();
                if(desc.contains(searchFor)) {
                    System.out.println("   " + task);
                }
            }
            System.out.println("____________________________________________________________");
        } else {
            // unknown command
            throw new ChatbotUnknownCommandException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
