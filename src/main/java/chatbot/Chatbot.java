package chatbot;

import chatbot.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {

    public static void main(String[] args) throws Exception {
        //Task[] tasks = new Task[100];
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        int numOfTasks = 0;

        String greetingMsg = "____________________________________________________________\n" +
                " Hello! I'm Chatbot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String byeMsg = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(greetingMsg);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while(!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < numOfTasks; i++) {
                        //System.out.println(" " + (i + 1) + ".[" + tasks[i].getTypeIcon() + "][" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("mark ")) {
                    String number = input.replace("mark ", "").trim();
                    int markTaskNo = Integer.parseInt(number);
                    if (markTaskNo > 0) {
                        tasks.get(markTaskNo - 1).markAsDone();
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   [" + tasks.get(markTaskNo - 1).getStatusIcon() + "] " + tasks.get(markTaskNo - 1).getDescription());
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("unmark ")) {
                    String number = input.replace("unmark ", "").trim();
                    int unmarkedTaskNo = Integer.parseInt(number);
                    if (unmarkedTaskNo > 0) {
                        tasks.get(unmarkedTaskNo - 1).markAsUndone();
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   [" + tasks.get(unmarkedTaskNo - 1).getStatusIcon() + "] " + tasks.get(unmarkedTaskNo - 1).getDescription());
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("todo")) {
                    String msg = input.replace("todo", "").trim();
                    if(msg.isEmpty()) {
                        throw new ChatbotEmptyDescException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    Task task = new Todo(msg);
                    //tasks.set(numOfTasks, task);
                    tasks.add(task);
                    numOfTasks++;

                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + String.valueOf(numOfTasks) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }  else if (input.startsWith("deadline")) {
                    String msg = input.replace("deadline", "").trim();
                    if(msg.isEmpty()) {
                        throw new ChatbotEmptyDescException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    }

                    String byDate = msg.substring(msg.indexOf("/by ") + 4).trim(); // will contain the byDate
                    String desc = msg.substring(0, msg.indexOf("/by")); // will contain the deadline description

                    Task task = new Deadline(desc, byDate);
                    //tasks.set(numOfTasks, task);
                    tasks.add(task);
                    numOfTasks++;

                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + String.valueOf(numOfTasks) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("event")) {
                    String msg = input.replace("event", "").trim();
                    if(msg.isEmpty()) {
                        throw new ChatbotEmptyDescException(" ☹ OOPS!!! The description of a event cannot be empty.");
                    }

                    String dateRange = msg.substring(msg.indexOf("/from ") + 6).trim();
                    String fromDate = dateRange.substring(0, dateRange.indexOf("/to ")).trim();
                    String toDate = dateRange.substring(dateRange.indexOf("/to ") + 4).trim();
                    String desc = msg.substring(0, msg.indexOf("/from ")); // will contain the deadline description

                    Task task = new Event(desc, fromDate, toDate);
                    //tasks.set(numOfTasks, task);
                    tasks.add(task);
                    numOfTasks++;

                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + String.valueOf(numOfTasks) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("delete")) {
                    String parameter = input.replace("delete", "").trim();
                    if(parameter.isEmpty()) {
                        throw new ChatbotEmptyDescException(" ☹ OOPS!!! The description of delete cannot be empty.");
                    }
                    int indexToRemove = Integer.parseInt(parameter);
                    Task task = tasks.get(indexToRemove-1);
                    tasks.remove(indexToRemove-1);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task: ");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + String.valueOf(tasks.size()) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    // unknown command
                    throw new ChatbotUnknownCommandException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch(ChatbotUnknownCommandException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            } catch(ChatbotEmptyDescException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                System.out.println("Unknown exception. Error message: "  + e.getMessage());
            }

            input = in.nextLine();
        }
        System.out.println(byeMsg);

    }
}
