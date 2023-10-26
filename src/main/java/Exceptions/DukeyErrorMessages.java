package Exceptions;


public class DukeyErrorMessages {

    /**
     * Displays an error message when the description for a todo task is empty.
     */
    public static void todoEmptyInputError() {
        printLine();
        System.out.println("Oops! I'm sorry, but description cannot be empty.\nPlease input your command for todo " +
                "in this " +
                "format:\ntodo (\"insert description here\")");
        printLine();
    }

    /**
     * Displays an error message when the format of a todo command is incorrect.
     */
    public static void todoFormatError() {
        printLine();
        System.out.println("Oops! I'm sorry, I didn't understand your command.\nPlease input your command for todo " +
                "in this " +
                "format:\ntodo (\"insert description here\")");
        printLine();
    }

    /**
     * Displays an error message when any field for an event task is empty.
     */
    public static void eventEmptyInputError() {
        printLine();
        System.out.println("Oops! I'm sorry, but none of the fields can be empty.\nPlease input your " +
                "command for event in this " +
                "format:\nevent (\"insert description here\") /from (\"insert description" +
                " here\") /to (\"insert description here\")");
        printLine();
    }


    /**
     * Displays an error message when the format of an event command is incorrect.
     */
    public static void eventFormatError() {
        printLine();
        System.out.println("Oops! I'm sorry, I didn't understand your command.\nPlease input your " +
                "command for event in this " +
                "format:\nevent (\"insert description here\") /from (\"insert description" +
                " here\") /to (\"insert description here\")");
        printLine();
    }

    /**
     * Displays an error message when any field for a deadline task is empty.
     */
    public static void deadlineEmptyInputError() {
        printLine();
        System.out.println("Oops! I'm sorry, but none of the fields can be empty.\nPlease input your " +
                "command for deadline in this " +
                "format:\ndeadline (\"insert description here\") /by (\"insert description" +
                " here\")");
        printLine();
    }

    /**
     * Displays an error message when the format of a deadline command is incorrect.
     */
    public static void deadlineFormatError() {
        printLine();
        System.out.println("Oops! I'm sorry, I didn't understand your command.\nPlease input your " +
                "command for deadline in this " +
                "format:\ndeadline (\"insert description here\") /by (\"insert description" +
                " here\")");
        printLine();
    }

    /**
     * Displays an error message for an unrecognized command.
     */
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


    /**
     * Displays an error message when the mark command has an invalid input.
     */
    public static void markInvalidError() {
        printLine();
        System.out.println("Oops! I'm sorry, your input for the mark command is invalid.\nPlease input  " +
                "your command for mark in this specific format\n" +
                "mark (\"index\")");
        printLine();
    }

    /**
     * Displays an error message when the input for mark is not an integer.
     */
    public static void markInvalidTypeError() {
        printLine();
        System.out.println("Oops! I'm sorry, your input for the mark command is not an integer.\nPlease input  " +
                "your command for mark in this specific format\n" +
                "mark (\"index\")");
        printLine();
    }


    /**
     * Displays an error message when the unmark command has an invalid input.
     */
    public static void unmarkInvalidError() {
        printLine();
        System.out.println("Oops! I'm sorry, your input for the unmark command is invalid.\nPlease input  " +
                "your command for unmark in this specific format\n" +
                "find (\"index\")");
        printLine();
    }

    /**
     * Displays an error message when the input for unmark is not an integer.
     */
    public static void unmarkInvalidTypeError() {
        printLine();
        System.out.println("Oops! I'm sorry, your input for the unmark command is not an integer.\nPlease input  " +
                "your command for unmark in this specific formatt\n" +
                "mark (\"index\")");
        printLine();
    }

    /**
     * Displays an error message when the description for a find command is empty.
     */
    public static void findEmptyInputError() {
        printLine();
        System.out.println("Oops! I'm sorry, but description cannot be empty.\nPlease input  " +
                "command for find in this specific format\n" +
                "find (\"description\")");
        printLine();
    }

    /**
     * Displays an error message when the index for a delete command is empty.
     */
    public static void deleteEmptyInputError() {
        printLine();
        System.out.println("Oops! I'm sorry, but index cannot be empty.\nPlease input  " +
                "command for delete in this specific format\n" +
                "delete (\"index\")");
        printLine();
    }

    /**
     * Displays an error message when the input for delete is not an integer.
     */
    public static void deleteInvalidTypeError() {
        printLine();
        System.out.println("Oops! I'm sorry, your input for the deleted command is invalid.\nPlease input  " +
                "command for delete in this specific format\n" +
                "delete (\"index\")");
        printLine();
    }


}
