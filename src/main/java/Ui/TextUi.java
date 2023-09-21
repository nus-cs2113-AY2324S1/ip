package Ui;

public class TextUi {
    private static final String[] greetingLines = {
            "Ah, another day for our delightful rendezvous, don't you agree?",
            "Greetings, mortals, may my presence grace your existence.",
            "Salutations, dear souls, ready to dance on the edge of destiny?",
            "Welcome to my realm, where shadows whisper and secrets beckon.",
            "Hark, for the orchestrator of mayhem has arrived. Ready to play?",
            "A toast to this peculiar meeting – may it be as intriguing as the last.",
            "I trust you've brought your curiosity, for we're about to embark on quite the journey."
    };
    public static void printHorizontalLine() {
        System.out.println("\t____________________________________________________________");
    }
    public static void printText(String text) {
        printHorizontalLine();
        System.out.println("\t" + text);
        printHorizontalLine();
    }

    public static void printMultipleText(String[] texts) {
        printHorizontalLine();
        for (String text : texts) {
            System.out.println("\t" + text);
        }
        printHorizontalLine();
    }
    public static void greet() {

        String randomGreetingLine = greetingLines[(int) (Math.random() * greetingLines.length)];

        printMultipleText(new String[] {
                "Hello, I'm",
                "▄▀▄▄▄▄   ▄▀▀▀▀▄  ▄▀▀▀▀▄   ▄▀▀▄▀▀▀▄  ▄▀▀▀█▀▀▄",
                "█ █    ▌ █ █   ▐ █        █   █   █ █    █  ▐",
                "▐ █         ▀▄   █    ▀▄▄ ▐  █▀▀▀▀  ▐   █",
                "  █      ▀▄   █  █     █ █   █         █",
                " ▄▀▄▄▄▄▀  █▀▀▀   ▐▀▄▄▄▄▀ ▐ ▄▀        ▄▀",
                "█     ▐   ▐      ▐        █         █",
                "▐                         ▐         ▐",
                "",
                randomGreetingLine,
                "What can I do for you?"
        });
    }
}
