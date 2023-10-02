package alan.common;

public class Messages {
    public static final String manDrawing = " @/\n" +
            "/| \n" +
            "/ \\";
    public static final String alanText = " ______     __         ______     __   __    \n" +
            "/\\  __ \\   /\\ \\       /\\  __ \\   /\\ \"-.\\ \\   \n" +
            "\\ \\  __ \\  \\ \\ \\____  \\ \\  __ \\  \\ \\ \\-.  \\  \n" +
            " \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_\\\\\"\\_\\ \n" +
            "  \\/_/\\/_/   \\/_____/   \\/_/\\/_/   \\/_/ \\/_/ ";

    public static final String MESSAGE_GREET = "Sup dude! I'm \n" + alanText + "\n" + manDrawing + "\n" + "What can I do for you, my man?";
    public static final String MESSAGE_GOODBYE = "Later, dude! Can't wait to catch up again real soon!";
    public static final String MESSAGE_LIST_COMMAND = "Dude, check out these tasks on your list:";
    public static final String MESSAGE_MARK_TASK = "Alright bro! This task is officially checked off:";
    public static final String MESSAGE_UNMARK_TASK = "Ok dude, I've marked this task as ain't done yet amigo:";
    public static final String MESSAGE_DELETE_TASK = "Got it, dude. This task is outta here:";
    public static final String MESSAGE_INVALID_INPUT_COMMAND = "Oof, I have no idea what are you saying duuude";
    public static final String MESSAGE_EMPTY_DESCRIPTION = "Oof Dude, you can't leave the description empty";
    public static final String MESSAGE_INVALID_DEADLINE_FORMAT = "Oof the deadline command isn't quite right you gotta fix the format, bro...\n[Remember it's: <description> /by <date>]";
    public static final String MESSAGE_INVALID_EVENT_FROM_FORMAT = "Oof duude, your /from formatting is whack\n[Remember it's: <description> /from <date> /to <date>]";
    public static final String MESSAGE_INVALID_EVENT_TO_FORMAT = "Oof my man, you need to work on that /to formatting\n[Remember it's: <description> /from <date> /to <date>]";
    public static final String MESSAGE_INVALID_TASK_NUMBER = "Oof maaaan there's no such task";
    public static final String MESSAGE_INVALID_TASK_TYPE_FOUND = "Yo I found a task type that doesn't make sense";
    public static final String MESSAGE_FIND_TASK = "Dude check it out, here are the matching tasks in your list:";
}
