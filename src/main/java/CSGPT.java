import java.util.Scanner;

public class CSGPT {
    private static final Task[] TASKS = new Task[100];
    private static int taskCount = 0;
    public static void getTasks() {
        printHorizontalLine();
        System.out.println("These are the chores you have at hand, mortal:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + TASKS[i].toString());
        }
        printHorizontalLine();
    }
    public static void addTask(String task) {
        Task newTask = new Task(task);
        TASKS[taskCount] = newTask;
        taskCount++;
    }
    public static void echo(String command) {
        printHorizontalLine();
        System.out.println(command);
        printHorizontalLine();
    }
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
    public static void greet() {
        String logo =  "▄▀▄▄▄▄   ▄▀▀▀▀▄  ▄▀▀▀▀▄   ▄▀▀▄▀▀▀▄  ▄▀▀▀█▀▀▄\n" +
                       "█ █    ▌ █ █   ▐ █        █   █   █ █    █  ▐\n" +
                       "▐ █         ▀▄   █    ▀▄▄ ▐  █▀▀▀▀  ▐   █\n" +
                       "  █      ▀▄   █  █     █ █   █         █\n" +
                       " ▄▀▄▄▄▄▀  █▀▀▀   ▐▀▄▄▄▄▀ ▐ ▄▀        ▄▀\n" +
                       "█     ▐   ▐      ▐        █         █\n" +
                       "▐                         ▐         ▐\n";
        String[] greetingLines = {
            "Ah, another day for our delightful rendezvous, don't you agree?",
            "Greetings, mortals, may my presence grace your existence.",
            "Salutations, dear souls, ready to dance on the edge of destiny?",
            "Welcome to my realm, where shadows whisper and secrets beckon.",
            "Hark, for the orchestrator of mayhem has arrived. Ready to play?",
            "A toast to this peculiar meeting – may it be as intriguing as the last.",
            "I trust you've brought your curiosity, for we're about to embark on quite the journey."
        };

        String randomGreetingLine = greetingLines[(int) (Math.random() * greetingLines.length)];

        printHorizontalLine();
        System.out.println("I'm\n" + logo);
        System.out.println(randomGreetingLine);
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }
    public static void farewell() {
        String[] farewellLines = {
            "Farewell, dear pawns. Until our paths cross again in the tapestry of time.",
            "The curtain falls, but remember, the show never truly ends.",
            "As shadows lengthen, it's time to bid adieu. Until fate conspires to reunite us.",
            "Ah, the thrill of departure, a prelude to our next devilish escapade.",
            "Take your leave, but tread carefully – the world outside is a canvas of treachery.",
            "May the echoes of our encounter resonate in your thoughts until destiny beckons anew.",
            "The tendrils of night stretch out, embracing you as you venture beyond my domain.",
            "Cherish the fleeting moments we've shared, for they are the embers of a fire we've stoked.",
            "Like whispers on the wind, I vanish into the shadows. Until next we cross paths...",
            "And so, the threads of our interaction fray, yet the tapestry remains forever changed."
        };

        String randomFarewellLine = farewellLines[(int) (Math.random() * farewellLines.length)];
        printHorizontalLine();
        System.out.println(randomFarewellLine);
        printHorizontalLine();
    }
    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);

        greet();
        command = in.nextLine();
        while(true) {
            if (command.equals("list")) {
                getTasks();
            } else if (command.equals("bye")) {
                farewell();
                return;
            } else if (command.startsWith("mark ")) {
                String remainder = command.substring(5);
                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(remainder);
                    if (taskNumber > taskCount) {
                        echo("Please enter a valid task number mortal. You do not have this many tasks!");
                    } else {
                        TASKS[taskNumber - 1].markAsDone();
                        echo("Consider it done:\n" + TASKS[taskNumber - 1].toString());
                    }
                } catch (NumberFormatException e) {
                    echo("Please enter a valid task number mortal.");
                }
            } else {
                addTask(command);
                echo("added: " + command);
            }
            command = in.nextLine();
        }
    }
}
