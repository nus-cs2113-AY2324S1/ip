package exceptionhandler;

public class KenergeticBotException extends Exception {
  public static String INVALID_COMMAND = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
  public static String TODO_MISSING_DESCRIPTION = "     \u02D9\u25E0\u02D9 OOPS!!! The description of a todo cannot be empty.";
  public static String DEADLINE_MISSING_DESCRIPTION = "     \u02D9\u25E0\u02D9 OOPS!!! The description of a deadline cannot be empty.";
  public static String DEADLINE_MISSING_DATE = "     \uD83D\uDCC5 OOPS!!! The date of a deadline cannot be empty.";
  public static String DEADLINE_MISSING_DATE_INTERMEDIATE = "     \uD83D\uDCC5 OOPS!!! The date of a deadline cannot be empty. Please use the command /by";
  public static String EVENT_MISSING_DESCRIPTION = "     \u02D9\u25E0\u02D9 OOPS!!! The description of a event cannot be empty.";
  public static String EVENT_MISSING_START = "     ⩇:⩇⩇ OOPS!!! The start time of a event cannot be empty.";
  public static String EVENT_MISSING_END = "     ⏳ OOPS!!! The end time of a event cannot be empty.";
  public static String EVENT_MISSING_START_INTERMEDIATE = "     ⩇:⩇⩇ OOPS!!! The start time of a event cannot be empty. Please use the command /from";
  public static String EVENT_MISSING_END_INTERMEDIATE = "     ⏳ OOPS!!! The end time of a event cannot be empty. Please use the command /to";
  public static String COMMAND_TYPO = "     ¯\\_(ツ)_/¯ OOPS!!! There may be a typo in the command used.";
  public static String COMMAND_TYPO_DEADLINE_BY = COMMAND_TYPO + " Please use the command /by";
  public static String COMMAND_TYPO_EVENT_FROM = COMMAND_TYPO + " Please use the command /from";
  public static String COMMAND_TYPO_EVENT_TO = COMMAND_TYPO + " Please use the command /to";

  public KenergeticBotException(String errMSG) {
    super(errMSG);
  }
}