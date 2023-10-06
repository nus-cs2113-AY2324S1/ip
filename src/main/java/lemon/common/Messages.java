package lemon.common;

/**
 * Utility class for managing messages displayed to the user.
 * This class contains static fields for various messages used in the chatbot.
 */
public class Messages {
    public static final String LEMON_EMOJI = "(>_<)/";
    public static final String LEMON_DIVIDER = "*\n";
    public static final String LEMON_START_AND_END = "\n* ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ *\n";
    public static final String LEMON_LOGO = "                      .--:::.                      \n"
            + "                .:-----::::.:-.                    \n"
            + "            .----:::::::::..:=:                    \n"
            + "         .---:::::::::::::::-*.                    \n"
            + "       .=-::::::::::::::::::::=.                   \n"
            + "      :=:::::::::::::::::::::::+.                  \n"
            + "     .=:::::::::::::::::::::::::=                  \n"
            + "     +.:::::::::::::::::::::::::=                  \n"
            + "    -:::::::::::::::::::::::::----:.               \n"
            + "    =:::::::::::::::::::::::-:.   .:---:           \n"
            + "    =:::::::::::::::::::::=* :.:: -  .:.--         \n"
            + "    =::::::::::::::::::::+--..   :::. .-.:=-       \n"
            + "    :-::::::::::::::::::=.:-.:    .:-.--. ::=      \n"
            + "     +:::::::::::::::::=:  + .:::::.-:-....: +     \n"
            + "     --::::::::-:::::::+   .+ -.....--:-..... =    \n"
            + "     :=::::::::::::::::=.   .=:.   .-: :-.  .:=.   \n"
            + "     --::::=--------=----.    --..:-:   :.: .:-.   \n"
            + "      .---=.    ...     =..    .-: .:.  .: .. =    \n"
            + "                        -   .    .-::-:..  .::     \n"
            + "                        .:::::.       .:=+=:       \n"
            + "                              .:::::::-:.          \n"
            + "    ______   ________  ________  ________  ________ \n"
            + "  ╱╱      ╲ ╱        ╲╱        ╲╱        ╲╱    ╱   ╲\n"
            + " ╱╱       ╱╱         ╱         ╱         ╱         ╱\n"
            + "╱        ╱╱        _╱         ╱         ╱         ╱ \n"
            + "╲________╱╲________╱╲__╱__╱__╱╲________╱╲__╱_____╱  \n";

    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String TAB = "\t";
    public static final String DOUBLE_TAB = "\t\t";

    public static final String MESSAGE_INTRODUCTION = "Hey there! I'm Lemon " + LEMON_EMOJI;
    public static final String MESSAGE_WELCOME = "How can I add some zest to your day?";
    public static final String MESSAGE_EXIT = "Squeeze the day! Until we meet again, stay fresh and zesty!";

    public static final String MESSAGE_EMPTY_LIST = "Your basket is on a lemonade break right now! " +
            "Add some fruitful tasks!";
    public static final String MESSAGE_DISPLAY_LIST = "Your basket is looking citrusy-fresh:";

    public static final String MESSAGE_ADDED_TASK = "Got it! This task has been squeezed into your basket:";
    public static final String MESSAGE_DELETED_TASK = "Got it! This task has been squeezed out of your basket:";
    public static final String MESSAGE_MARKED_TASK = "Great job! This task is now juiced:";
    public static final String MESSAGE_UNMARKED_TASK = "No problem! This task is back into the basket:";
    public static final String MESSAGE_FOUND_TASK = "On it! Here are the tasks you are looking for:";
    public static final String MESSAGE_UNFOUND_TASK = "Hmmm! The task you are looking for is not in your basket!";

    public static final String COMMAND_LIST_FORMAT = DOUBLE_TAB + "list" + LINE_SEPARATOR;
    public static final String COMMAND_TODO_FORMAT = DOUBLE_TAB + "todo <task>" + LINE_SEPARATOR;
    public static final String COMMAND_DEADLINE_FORMAT = DOUBLE_TAB + "deadline <task> /by <date/time>" +
            LINE_SEPARATOR;
    public static final String COMMAND_EVENT_FORMAT = DOUBLE_TAB + "event <task> /from <starting date/time> " +
            "/to <ending date/time>" + LINE_SEPARATOR;
    public static final String COMMAND_MARK_FORMAT = DOUBLE_TAB + "mark <task number>" + LINE_SEPARATOR;
    public static final String COMMAND_UNMARK_FORMAT = DOUBLE_TAB + "unmark <task number>" + LINE_SEPARATOR;
    public static final String COMMAND_FIND_FORMAT = DOUBLE_TAB + "find <keyword>" + LINE_SEPARATOR;
    public static final String COMMAND_DELETE_FORMAT = DOUBLE_TAB + "delete <task number>" + LINE_SEPARATOR;
    public static final String COMMAND_BYE_FORMAT = DOUBLE_TAB + "bye";

    public static final String HELP_MESSAGE = "Here is the format of valid commands!" + LINE_SEPARATOR;
    public static final String HELP_LIST_MESSAGE = TAB + "List all tasks:";
    public static final String HELP_TODO_MESSAGE = TAB + "Add todo task:";
    public static final String HELP_DEADLINE_MESSAGE = TAB + "Add deadline task:";
    public static final String HELP_EVENT_MESSAGE = TAB + "Add event task:";
    public static final String HELP_MARK_MESSAGE = TAB + "Mark task as done:";
    public static final String HELP_UNMARK_MESSAGE = TAB + "Mark task as not done:";
    public static final String HELP_FIND_MESSAGE = TAB + "Find task by keyword:";
    public static final String HELP_DELETE_MESSAGE = TAB + "Delete task:";
    public static final String HELP_BYE_MESSAGE = TAB + "Exit program:";
}
