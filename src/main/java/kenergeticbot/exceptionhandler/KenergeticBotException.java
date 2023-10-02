package kenergeticbot.exceptionhandler;

public class KenergeticBotException extends Exception {
  public static final String INVALID_COMMAND = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
  public static final String TODO_MISSING_DESCRIPTION = "     \u02D9\u25E0\u02D9 OOPS!!! The description of a todo cannot be empty.";
  public static final String DEADLINE_MISSING_DESCRIPTION = "     \u02D9\u25E0\u02D9 OOPS!!! The description of a deadline cannot be empty.";
  public static final String DEADLINE_MISSING_DATE = "     \uD83D\uDCC5 OOPS!!! The date of a deadline cannot be empty.";
  public static final String DEADLINE_MISSING_DATE_INTERMEDIATE = "     \uD83D\uDCC5 OOPS!!! The date of a deadline cannot be empty. Please use the command /by";
  public static final String EVENT_MISSING_DESCRIPTION = "     \u02D9\u25E0\u02D9 OOPS!!! The description of a event cannot be empty.";
  public static final String EVENT_MISSING_START = "     ⩇:⩇⩇ OOPS!!! The start time of a event cannot be empty.";
  public static final String EVENT_MISSING_END = "     ⏳ OOPS!!! The end time of a event cannot be empty.";
  public static final String EVENT_MISSING_START_INTERMEDIATE = "     ⩇:⩇⩇ OOPS!!! The start time of a event cannot be empty. Please use the command /from";
  public static final String EVENT_MISSING_END_INTERMEDIATE = "     ⏳ OOPS!!! The end time of a event cannot be empty. Please use the command /to";
  public static final String OUT_OF_RANGE = "     OOPS!!! Looks like you tried on an item not in the list";
  public static final String COMMAND_TYPO = "     ¯\\_(ツ)_/¯ OOPS!!! There may be a typo in the command used.";
  public static final String COMMAND_TYPO_NO_NUMBER = COMMAND_TYPO + " Please use the command with an integer number";
  public static final String COMMAND_TYPO_NO_KEYWORD = COMMAND_TYPO + " Please use the command with a keyword";
  public static final String COMMAND_TYPO_DEADLINE_BY = COMMAND_TYPO + " Please use the command /by";
  public static final String COMMAND_TYPO_EVENT_FROM = COMMAND_TYPO + " Please use the command /from";
  public static final String COMMAND_TYPO_EVENT_TO = COMMAND_TYPO + " Please use the command /to";
  public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n";
  public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";

  public KenergeticBotException(String errMSG) {
    super(errMSG);
  }
}