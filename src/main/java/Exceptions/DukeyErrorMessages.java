package Exceptions;


public class DukeyErrorMessages {

    public static void todoEmptyInputError() {
        printLine();
        System.out.println("Oops! I'm sorry, but description cannot be empty.\nPlease input your command for todo " +
                "in this " +
                "format:\ntodo (\"insert description here\")");
        printLine();
    }

    public static void todoFormatError() {
        printLine();
        System.out.println("Oops! I'm sorry, I didn't understand your command.\nPlease input your command for todo " +
                "in this " +
                "format:\ntodo (\"insert description here\")");
        printLine();
    }

    public static void eventEmptyInputError() {
        printLine();
        System.out.println("Oops! I'm sorry, but none of the fields can be empty.\nPlease input your " +
                "command for event in this " +
                "format:\nevent (\"insert description here\") /from (\"insert description" +
                " here\") /to (\"insert description here\")");
        printLine();
    }

    public static void eventFormatError() {
        printLine();
        System.out.println("Oops! I'm sorry, I didn't understand your command.\nPlease input your " +
                "command for event in this " +
                "format:\nevent (\"insert description here\") /from (\"insert description" +
                " here\") /to (\"insert description here\")");
        printLine();
    }


    public static void deadlineEmptyInputError() {
        printLine();
        System.out.println("Oops! I'm sorry, but none of the fields can be empty.\nPlease input your " +
                "command for deadline in this " +
                "format:\ndeadline (\"insert description here\") /by (\"insert description" +
                " here\")");
        printLine();
    }

    public static void deadlineFormatError() {
        printLine();
        System.out.println("Oops! I'm sorry, I didn't understand your command.\nPlease input your " +
                "command for deadline in this " +
                "format:\ndeadline (\"insert description here\") /by (\"insert description" +
                " here\")");
        printLine();
    }

    public static void unrecognizedCommandError() {
        printLine();
        System.out.println("Oops! I'm sorry, I didn't understand your command.\nPlease input  " +
                "one of the available commands in its specific format\n" +
                "Commands: todo, event, deadline, delete, find, mark, unmark");
        printLine();
    }

    public static void printLine() {
        System.out.println("_____________________________________________________");
    }

    public static void markInvalidError() {
        printLine();
        System.out.println("Oops! I'm sorry, your input for the mark command is invalid.\nPlease input  " +
                "your command for mark in this specific format\n" +
                "mark (\"index\")");
        printLine();
    }

    public static void markInvalidTypeError() {
        printLine();
        System.out.println("Oops! I'm sorry, your input for the mark command is not an integer.\nPlease input  " +
                "your command for mark in this specific format\n" +
                "mark (\"index\")");
        printLine();
    }

    public static void unmarkInvalidError() {
        printLine();
        System.out.println("Oops! I'm sorry, your input for the unmark command is invalid.\nPlease input  " +
                "your command for unmark in this specific format\n" +
                "find (\"index\")");
        printLine();
    }

    public static void unmarkInvalidTypeError() {
        printLine();
        System.out.println("Oops! I'm sorry, your input for the unmark command is not an integer.\nPlease input  " +
                "your command for unmark in this specific formatt\n" +
                "mark (\"index\")");
        printLine();
    }

    public static void findEmptyInputError() {
        printLine();
        System.out.println("Oops! I'm sorry, but description cannot be empty.\nPlease input  " +
                "command for find in this specific format\n" +
                "find (\"description\")");
        printLine();
    }

    public static void deleteEmptyInputError() {
        printLine();
        System.out.println("Oops! I'm sorry, but index cannot be empty.\nPlease input  " +
                "command for delete in this specific format\n" +
                "delete (\"index\")");
        printLine();
    }

    public static void deleteInvalidTypeError() {
        printLine();
        System.out.println("Oops! I'm sorry, your input for the deleted command is invalid.\nPlease input  " +
                "command for delete in this specific format\n" +
                "delete (\"index\")");
        printLine();
    }


}
