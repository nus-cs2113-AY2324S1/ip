package python.ui;

/**
 * Defines string literals for the chatbot responses.
 */
public class Message {
    final static String MESSAGE_INTRODUCTION = "Hello! I am a Java Bot Python!";
    final static String MESSAGE_ASK = "What can I do for you?";
    final static String MESSAGE_BYE = "Bye. See you again when you run the program again!";
    final static String MESSAGE_INT_AFTER_COMMAND = "Command must be followed by an integer (task id)!";
    final static String MESSAGE_KEYWORD_AFTER_COMMAND = "Command must be followed by a keyword!";
    final static String MESSAGE_DESC_AFTER_COMMAND = "Command must be followed by a task description!!";
    final static String MESSAGE_TIME_AFTER_FROM_CLAUSE =
            "Command must have /from clause followed by time it starts!";
    final static String MESSAGE_TIME_AFTER_TO_CLAUSE =
            "Command must have /to clause followed by time it ends!";
    final static String MESSAGE_TIME_AFTER_BY_CLAUSE =
            "Command must have /by clause followed by time its due!";
    final static String MESSAGE_FUTURE_JOKE = "Are you from the future?";
    final static String MESSAGE_PAST_JOKE = "Are you from the past?";
    final static String MESSAGE_TASK_ALREADY_DONE = "Task is already done!";
    final static String MESSAGE_CONGRATUALTE = "Good job completing the task!";
    final static String MESSAGE_UNMARK_TASK_JOKE = "Alas! Only the completed tasks can be unmarked!";
    final static String MESSAGE_TASK_IDLE = "Task is already sitting idle. Get started...!!!";
    final static String MESSAGE_MISTAKE_CONSOLATION = "Its okay! To err is human! Unmarked!";
    final static String MESSAGE_DELETE_CONFIRMATION = "Okay. Deleting this task...!";
    final static String MESSAGE_EMPTY_COMMAND_JOKE = "Nothing for me?";
    final static String MESSAGE_UNKNOWN_COMMAND = "I cannot understand the command!";
    final static String MESSAGE_NEW_TODO = "New Todo! You have added this todo:";
    final static String MESSAGE_NEW_DEADLINE = "New Deadline! You have added this deadline:";
    final static String MESSAGE_NEW_EVENT = "New Event! You have added this event:";
    final static String MESSAGE_NO_MATCH = "No matching tasks found!";
    final static String MESSAGE_MATCHES_FOUND = "Here are the matching tasks:";
}
