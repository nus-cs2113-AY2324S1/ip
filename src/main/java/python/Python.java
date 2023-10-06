package python;

import python.exception.PythonException;
import python.ui.Ui;

public class Python {
    static final private Ui ui = new Ui();

    public static void main(String[] args) {
        ui.welcomeUser();
        do {
            ui.getUserInput();
            try {
                ui.parseLine();
                ui.executeLine();
            } catch (PythonException e) {
                ui.displayException(e);
            }
        } while(!ui.isExit());
    }
}
