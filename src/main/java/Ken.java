import java.util.Scanner;

public class Ken {
    private static final Task[] TASKS = new Task[100];
    private static int taskSize = 0;
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String BYE = "bye";

    public static void addTask(Task task) {
        TASKS[taskSize] = task;
        taskSize++;
        Ui.printTexts(new String[] {
                "Barbie-approved! You've added this glamorous task:",
                task.toString(),
                "Now your list is sparkling with " + taskSize + " glamorous tasks, darling!"
        });
    }

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
                        throw new ParsingException("Oopsie! A todo without a description is like a party without glitter, so not fabulous!");
                    }
                    String todoName = input.substring(TODO.length() + 1);
                    Todo todo = new Todo(todoName);
                    addTask(todo);
                    break;
                case DEADLINE:
                    if (!input.contains("/by")) {
                        throw new ParsingException("Please enter a proper deadline using '/by'.");
                    }
                    String[] deadlineInfo = input.substring(DEADLINE.length() + 1).split("/by", 2);
                    String deadlineName = deadlineInfo[0];
                    String by = deadlineInfo[1];
                    if (deadlineName.isEmpty()) {
                        throw new ParsingException("Oopsie! A deadline without a description is like a party without glitter, so not fabulous!");
                    }
                    if (by.isEmpty()) {
                        throw new ParsingException("Oopsie! Did you forget to include the deadline?");
                    }
                    Deadline deadline = new Deadline(deadlineName, by);
                    addTask(deadline);
                    break;
                case EVENT:
                    if (!input.contains("/from") || !input.contains("/to")) {
                        throw new ParsingException("Please enter a proper event using '/from' and ' /to'.");
                    }
                    String[] eventInfo = input.substring(EVENT.length() + 1).split("/from |/to", 3);
                    String eventName = eventInfo[0];
                    String from = eventInfo[1];
                    String to = eventInfo[2];
                    if (eventName.isEmpty()) {
                        throw new ParsingException("Oopsie! An event without a description is like a party without glitter, so not fabulous!");
                    }
                    if (from.isEmpty()) {
                        throw new ParsingException("Oopsie! Did you forget to include the start date of the event?");
                    }
                    if (to.isEmpty()) {
                        throw new ParsingException("Oopsie! Did you forget to include the end date of the event?");
                    }
                    Event event = new Event(eventName, from, to);
                    addTask(event);
                    break;
                case LIST:
                    String[] texts = new String[taskSize + 1];
                    texts[0] = "Behold, your list of enchanting tasks!";
                    for (int i = 1; i <= taskSize; i++) {
                        texts[i] = "\t" + i + "." + TASKS[i - 1].toString();
                    }
                    Ui.printTexts(texts);
                    break;
                case MARK:
                    if (input.trim().equals(MARK)) {
                        throw new ParsingException("Pick one to mark and let the Barbie magic flow!");
                    }
                    String markTaskString = input.split(" ", 2)[1];
                    int markTaskNumber;
                    try {
                        markTaskNumber = Integer.parseInt(markTaskString) - 1;
                        if (markTaskNumber < 0 || markTaskNumber >= taskSize) {
                            throw new ParsingException("There's no task like that in our fabulous list, darling!");
                        }
                    } catch (NumberFormatException e) {
                        throw new ParsingException("Oh darling, that's not a number fit for a Barbie world.");
                    }

                    TASKS[markTaskNumber].markAsDone();
                    Ui.printTexts(new String[]{
                            "Barbie-tastic! You've completed this task with glamour!",
                            TASKS[markTaskNumber].toString()
                    });
                    break;
                case UNMARK:
                    if (input.trim().equals(UNMARK)) {
                        throw new ParsingException("Pick one to unmark and let the Barbie magic flow!");
                    }
                    String unmarkTaskString = input.split(" ", 2)[1];
                    int unmarkTaskNumber;
                    try {
                        unmarkTaskNumber = Integer.parseInt(unmarkTaskString) - 1;
                        if (unmarkTaskNumber < 0 || unmarkTaskNumber >= taskSize) {
                            throw new ParsingException("There's no task like that in our fabulous list, darling!");
                        }
                    } catch (NumberFormatException e) {
                        throw new ParsingException("Oh darling, that's not a number fit for a Barbie world.");
                    }

                    TASKS[unmarkTaskNumber].unmarkAsDone();
                    Ui.printTexts(new String[]{
                            "Back to the runway, darling! This task needs more Barbie magic!",
                            TASKS[unmarkTaskNumber].toString()
                    });
                    break;
                case BYE:
                    Ui.byeUser();
                    return;
                default:
                    throw new ParsingException("Uh-oh, darling! Your input needs a makeover for me to understand!");
                }
            } catch (ParsingException e) {
                Ui.printTexts(new String[] {
                        e.getMessage()
                });
            }
            input = scan.nextLine();
        }
    }
}
