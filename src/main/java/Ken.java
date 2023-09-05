import java.util.ArrayList;
import java.util.Arrays;
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

    public static void printLine() {
        System.out.println("_____________________________________________________________");
    }

    public static void printTexts(String[] texts) {
        printLine();
        for (String text : texts) {
            System.out.println("\t" + text);
        }
        printLine();
    }

    public static void main(String[] args) {
        String greetingLogo = " ____  __.___________ _______\n"
                + "\t|    |/ _|\\_   _____/ \\      \\\n"
                + "\t|      <   |    __)_  /   |   \\\n"
                + "\t|    |  \\  |        \\/    |    \\\n"
                + "\t|____|__ \\/_________/\\____|__  /\n"
                + "\t\\/        \\/         \\/";

        String byeLogo = "  ___________________  _____ __________ ____  __.____    .___ _______    ________\n"
                + "\t/   _____/\\______   \\/  _  \\\\______   \\    |/ _|    |   |   |\\      \\  /  _____/\n"
                + "\t \\_____  \\  |     ___/  /_\\  \\|       _/      < |    |   |   |/   |   \\/   \\  ___\n"
                + "\t /        \\ |    |  /    |    \\    |   \\    |  \\|    |___|   /    |    \\    \\_\\  \\\n"
                + "\t/_______  / |____|  \\____|__  /____|_  /____|__ \\_______ \\___\\____|__  /\\______  /\n"
                + "\t        \\/                  \\/       \\/        \\/       \\/           \\/        \\/";

        printTexts(new String[]{
                "Greetings, fashionista! I'm",
                greetingLogo,
                "your dream planner extraordinaire.",
                "Ready to make your day as fabulous as a Barbie runway show?"
                }
        );

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (true) {
            switch (input.contains(" ") ? input.split(" ")[0] : input) {
            case TODO:
                String todoName = input.substring(TODO.length() + 1);
                Todo todo = new Todo(todoName);
                TASKS[taskSize] = todo;
                taskSize++;
                printTexts(new String[]{
                        "Barbie-approved! You've added this glamorous task:",
                        todo.toString(),
                        "Now your list is sparkling with " + taskSize + " glamorous tasks, darling!"
                        }
                );
                break;
            case DEADLINE:
                String[] deadlineInfo = input.substring(DEADLINE.length() + 1).split(" /by", 2);
                String deadlineName = deadlineInfo[0];
                String by = deadlineInfo[1];
                Deadline deadline = new Deadline(deadlineName, by);
                TASKS[taskSize] = deadline;
                taskSize++;
                printTexts(new String[]{
                                "Barbie-approved! You've added this glamorous task:",
                                deadline.toString(),
                                "Now your list is sparkling with " + taskSize + " glamorous tasks, darling!"
                        }
                );
                break;
            case EVENT:
                String[] eventInfo = input.substring(EVENT.length() + 1).split(" /from", 2);
                String eventName = eventInfo[0];
                String[] eventTimeline = eventInfo[1].split(" /to", 2);
                String from = eventTimeline[0];
                String to = eventTimeline[1];
                Event event = new Event(eventName, from, to);
                TASKS[taskSize] = event;
                taskSize++;
                printTexts(new String[]{
                                "Barbie-approved! You've added this glamorous task:",
                                event.toString(),
                                "Now your list is sparkling with " + taskSize + " glamorous tasks, darling!"
                        }
                );
                break;
            case LIST:
                String[] text = new String[taskSize + 1];
                text[0] = "Behold, your list of enchanting tasks!";
                for (int i = 1; i <= taskSize; i++) {
                    text[i] = "\t" + i +"." + TASKS[i - 1].toString();
                }
                printTexts(text);
                break;
            case MARK:
                String markTaskString = input.split(" ", 2)[1];
                int markTaskNumber = Integer.parseInt(markTaskString) - 1;
                TASKS[markTaskNumber].markAsDone();
                printTexts(new String[] {
                        "Barbie-tastic! You've completed this task with glamour!",
                        TASKS[markTaskNumber].toString()
                        }
                );
                break;
            case UNMARK:
                String unmarkTaskString = input.split(" ", 2)[1];
                int unmarkTaskNumber = Integer.parseInt(unmarkTaskString) - 1;
                TASKS[unmarkTaskNumber].unmarkAsDone();
                printTexts(new String[] {
                                "Back to the runway, darling! This task needs more Barbie magic!",
                                TASKS[unmarkTaskNumber].toString()
                        }
                );
                break;
            case BYE:
                printTexts(new String[]{
                        "Until we meet again, my fellow dream chaser! Keep",
                        byeLogo
                        }
                );
                return;
            }
            input = scan.nextLine();
        }
    }
}
