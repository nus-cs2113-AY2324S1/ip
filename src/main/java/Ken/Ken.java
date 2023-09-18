package Ken;

import Commands.Add;
import Commands.List;
import Exceptions.KenException;
import Exceptions.KenMissingTaskException;
import Exceptions.KenParsingException;

import java.util.Scanner;

public class Ken {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String BYE = "bye";
    private static final TaskList list = new TaskList();


    public static void main(String[] args) {
        Ui.greetUser();

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (true) {
            String command = input.contains(" ") ? input.split(" ")[0] : input;
            try {
                switch (command) {
                case TODO:
                    if (input.trim().equals(TODO)) {
                        throw new KenParsingException("Oopsie! A todo without a description is like a party without glitter, so not fabulous!");
                    }
                    String todoName = input.substring(TODO.length() + 1);
                    Todo todo = new Todo(todoName);
                    new Add(todo).run(list);
                    break;
                case DEADLINE:
                    if (!input.contains("/by")) {
                        throw new KenParsingException("Please enter a proper deadline using '/by'.");
                    }
                    String[] deadlineInfo = input.substring(DEADLINE.length() + 1).split("/by", 2);
                    String deadlineName = deadlineInfo[0];
                    String by = deadlineInfo[1];
                    if (deadlineName.isEmpty()) {
                        throw new KenParsingException("Oopsie! A deadline without a description is like a party without glitter, so not fabulous!");
                    }
                    if (by.isEmpty()) {
                        throw new KenParsingException("Oopsie! Did you forget to include the deadline?");
                    }
                    Deadline deadline = new Deadline(deadlineName, by);
                    new Add(deadline).run(list);
                    break;
                case EVENT:
                    if (!input.contains("/from") || !input.contains("/to")) {
                        throw new KenParsingException("Please enter a proper event using '/from' and ' /to'.");
                    }
                    String[] eventInfo = input.substring(EVENT.length() + 1).split("/from |/to", 3);
                    String eventName = eventInfo[0];
                    String from = eventInfo[1];
                    String to = eventInfo[2];
                    if (eventName.isEmpty()) {
                        throw new KenParsingException("Oopsie! An event without a description is like a party without glitter, so not fabulous!");
                    }
                    if (from.isEmpty()) {
                        throw new KenParsingException("Oopsie! Did you forget to include the start date of the event?");
                    }
                    if (to.isEmpty()) {
                        throw new KenParsingException("Oopsie! Did you forget to include the end date of the event?");
                    }
                    Event event = new Event(eventName, from, to);
                    new Add(event).run(list);
                    break;
                case LIST:
                    new List().run(list);
                    break;
                case MARK:
                    if (input.trim().equals(MARK)) {
                        throw new KenParsingException("Pick one to mark and let the Barbie magic flow!");
                    }
                    String markTaskString = input.split(" ", 2)[1];
                    int markTaskNumber;
                    try {
                        markTaskNumber = Integer.parseInt(markTaskString);
                        if (markTaskNumber < 1 || markTaskNumber > list.getSize()) {
                            throw new KenParsingException("There's no task like that in our fabulous list, darling!");
                        }
                    } catch (NumberFormatException e) {
                        throw new KenParsingException("Oh darling, that's not a number fit for a Barbie world.");
                    }

                    list.updateStatus(markTaskNumber, true);
                    Ui.printTexts(new String[]{
                            "Barbie-tastic! You've completed this task with glamour!",
                            list.getTask(markTaskNumber).toString()
                    });
                    break;
                case UNMARK:
                    if (input.trim().equals(UNMARK)) {
                        throw new KenParsingException("Pick one to unmark and let the Barbie magic flow!");
                    }
                    String unmarkTaskString = input.split(" ", 2)[1];
                    int unmarkTaskNumber;
                    try {
                        unmarkTaskNumber = Integer.parseInt(unmarkTaskString);
                        if (unmarkTaskNumber < 1 || unmarkTaskNumber > list.getSize()) {
                            throw new KenParsingException("There's no task like that in our fabulous list, darling!");
                        }
                    } catch (NumberFormatException e) {
                        throw new KenParsingException("Oh darling, that's not a number fit for a Barbie world.");
                    }

                    list.updateStatus(unmarkTaskNumber, false);
                    Ui.printTexts(new String[]{
                            "Back to the runway, darling! This task needs more Barbie magic!",
                            list.getTask(unmarkTaskNumber).toString()
                    });
                    break;
                case BYE:
                    Ui.byeUser();
                    return;
                default:
                    throw new KenParsingException("Uh-oh, darling! Your input needs a makeover for me to understand!");
                }
            } catch (Exception e) {
                Ui.printTexts(new String[] {
                        e.getMessage()
                });
            }
            input = scan.nextLine();
        }
    }
}
