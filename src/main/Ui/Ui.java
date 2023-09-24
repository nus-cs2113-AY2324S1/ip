package Ui;

import java.util.ArrayList;
import java.util.Iterator;

import Task.Task;

public class Ui {
    private static final String BOT_NAME = "JS";
    private static final String LINE_DIVIDER = "----------------------------------------";
    private static final String TODO_USAGE_MSG = "Usage: todo <description>";
    private static final String EVENT_USAGE_MSG = "Usage: event <description> /from <dd mmm yyyy> /to <dd mmm yyyy>";
    private static final String DEADLINE_USAGE_MSG = "Usage: deadline <description> /by <dd mmm yyyy>";
    private static final String MARK_LIST_USAGE_MSG = "Usage: mark <index>";
    private static final String UNMARK_LIST_USAGE_MSG = "Usage: unmark <index>";
    private static final String DELETE_TASK_USAGE_MSG = "Usage: delete <index>";    

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_DIVIDER);
    }

    public void printBotMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
    }

    public void printLineDivider() {
        System.out.println(LINE_DIVIDER);
    }

    public void printListLength(ArrayList<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " task in the list");
    }

    public void printList(ArrayList<Task> taskList) {
        System.out.println("Here are the task in your list:");
        Iterator<Task> taskListIter = taskList.iterator();
        for(int i = 1; taskListIter.hasNext() ; i++) {
            Task event = taskListIter.next();
            System.out.println(i + "." + event);
        }
    }

    public void printTask(Task task, boolean isDelete) {
        if(task == null) {
            return;
        }
        if(isDelete) {
            System.out.println("Noted. I've removed this task:");
        } else {
            System.out.println("Got it. I've added this task:");
        }
        System.out.print("  ");
        System.out.println(task);
    }

    public void printMarked(Task task) {
        if(task == null) {
            return;
        }
        if(task.isCompleted()) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task);
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);
        }
    }

    public void printInvalidMessage() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void printEventUsage() {
        System.out.println(EVENT_USAGE_MSG);
    }

    public void printToDoUsage() {
        System.out.println(TODO_USAGE_MSG);
    }

    public void printDeadlineUsage() {
        System.out.println(DEADLINE_USAGE_MSG);
    }

    public void printMarkUsage() {
        System.out.println(MARK_LIST_USAGE_MSG);
    }

    public void printUnmarkUsage() {
        System.out.println(UNMARK_LIST_USAGE_MSG);
    }

    public void printDeleteUsage() {
        System.out.println(DELETE_TASK_USAGE_MSG);
    }
}
