package Ken;

public class Ui {
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

    public static void greetUser() {
        String hiLogo = " ____  __.___________ _______\n"
                + "\t|    |/ _|\\_   _____/ \\      \\\n"
                + "\t|      <   |    __)_  /   |   \\\n"
                + "\t|    |  \\  |        \\/    |    \\\n"
                + "\t|____|__ \\/_________/\\____|__  /\n"
                + "\t\\/        \\/         \\/";
        printTexts(new String[] {
                "Greetings, fashionista! I'm",
                hiLogo,
                "your dream planner extraordinaire.",
                "Ready to make your day as fabulous as a Barbie runway show?"
        });
    }

    public static void byeUser() {
        String byeLogo = "  ___________________  _____ __________ ____  __.____    .___ _______    ________\n"
                + "\t/   _____/\\______   \\/  _  \\\\______   \\    |/ _|    |   |   |\\      \\  /  _____/\n"
                + "\t \\_____  \\  |     ___/  /_\\  \\|       _/      < |    |   |   |/   |   \\/   \\  ___\n"
                + "\t /        \\ |    |  /    |    \\    |   \\    |  \\|    |___|   /    |    \\    \\_\\  \\\n"
                + "\t/_______  / |____|  \\____|__  /____|_  /____|__ \\_______ \\___\\____|__  /\\______  /\n"
                + "\t        \\/                  \\/       \\/        \\/       \\/           \\/        \\/";
        printTexts(new String[] {
                "Until we meet again, my fellow dream chaser! Keep",
                byeLogo,
        });
    }
}
