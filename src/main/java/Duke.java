import java.util.Random;
import java.util.Scanner;

public class Duke {
    private static final String[] TASKS = new String[100];
    private static int taskCount = 0;
    public static void getTasks() {
        printHorizontalLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + ". " + TASKS[i]);
        }
        printHorizontalLine();
    }
    public static void addTask(String task) {
        TASKS[taskCount] = task;
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
            switch(command) {
                case "list":
                    getTasks();
                    break;
                case "bye":
                    farewell();
                    return;
                default:
                    addTask(command);
                    echo("added: " + command);
                    break;
            }
            command = in.nextLine();
        }
    }
}
