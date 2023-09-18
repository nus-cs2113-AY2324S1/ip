package Commands;

import Exceptions.KenException;
import Ken.TaskList;
import Ken.Ui;

public class Goodbye extends Command {
    private static final String byeLogo = "  ___________________  _____ __________ ____  __.____    .___ _______    ________\n"
            + "\t/   _____/\\______   \\/  _  \\\\______   \\    |/ _|    |   |   |\\      \\  /  _____/\n"
            + "\t \\_____  \\  |     ___/  /_\\  \\|       _/      < |    |   |   |/   |   \\/   \\  ___\n"
            + "\t /        \\ |    |  /    |    \\    |   \\    |  \\|    |___|   /    |    \\    \\_\\  \\\n"
            + "\t/_______  / |____|  \\____|__  /____|_  /____|__ \\_______ \\___\\____|__  /\\______  /\n"
            + "\t        \\/                  \\/       \\/        \\/       \\/           \\/        \\/";
    @Override
    public void run(TaskList list) throws KenException {
        Ui.printTexts(new String[] {
                "Until we meet again, my fellow dream chaser! Keep",
                byeLogo,
        });
    }
}
