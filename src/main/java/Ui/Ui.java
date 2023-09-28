package Ui;

import Task.Task;

import java.util.ArrayList;

public class Ui {
    public static final String LINE_DIVIDER = "    _____________________________________";

    public static void greet() {
        System.out.println("Hello! I'm Axel!\n"
                + "What can I do for you?\n"
                + LINE_DIVIDER);
    }

    public static void reportTaskAdded(ArrayList<Task> itemList, int i) {
        System.out.println(LINE_DIVIDER + "\n"
                + "Sure, I've added this task:\n"
                + "    " + itemList.get(i).toString()
                + "\n" + LINE_DIVIDER);
    }

    public static void reportTaskDeleted(Task temp, ArrayList<Task> itemList) {
        System.out.println(LINE_DIVIDER + "\n"
                + "Alright, I've deleted this task:\n"
                + "    " + temp.toString() + "\n"
                + "You have " + (itemList.size() - 1) + " tasks left now. "
                + ((itemList.size() == 1) ? "Congrats!" : "Get on it!") +"\n"
                + LINE_DIVIDER);
    }

    public static  void reportListEmpty() {
        System.out.println(LINE_DIVIDER + "\n"
                + "    The list is empty, yo!\n"
                + LINE_DIVIDER);
    }

    public static void reportOutOfBounds() {
        System.out.println(LINE_DIVIDER + "\n"
                + "    That's out of bounds, dude!\n"
                + LINE_DIVIDER);
    }

    public static  void reportMissingCommandWord() {
        System.out.println(LINE_DIVIDER +
                "\n    I couldn't find a command word, try again!\n"
                + LINE_DIVIDER);
    }

    public static void reportSaveError() {
        System.out.println("    I couldn't save your file, try again?\n"
                + LINE_DIVIDER);
    }

    public static  void reportTaskMarked(ArrayList<Task> itemList, int i) {
        System.out.println(LINE_DIVIDER + "\n"
                + "    Nice, I've marked this task as done:\n"
                + "    " + itemList.get(i - 1).toString() + "\n"
                + LINE_DIVIDER);
    }

    public static  void reportTaskUnmarked(ArrayList<Task> itemList, int i) {
        System.out.println(LINE_DIVIDER + "\n"
                + "    Nice, I've unmarked this task as done:\n"
                + "    " + itemList.get(i - 1).toString() + "\n"
                + LINE_DIVIDER);
    }

    public static void reportMissingTaskInfo() {
        System.out.println( LINE_DIVIDER +
                "\n    Oop, looks like you forgot to add something!\n" +
                LINE_DIVIDER
        );
    }

    public static void reportFileMissingError() {
        System.out.println(LINE_DIVIDER
                + "\n    Where's the file, yo? I couldn't find it!\n"
                + LINE_DIVIDER);
    }

    public static void bye() {
        System.out.println(LINE_DIVIDER +
                "\n    Bye. Hope to see you again soon!\n"
                + LINE_DIVIDER);
    }
}
