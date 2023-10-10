package AMY;

public class AMY {

    public static void run() {
        Ui.welcomeMessage();
        Storage.loadTasksFromFile();
        Parser.manageInput();
        Storage.saveTasksToFile();
        Ui.byMessage();
    }

    public static void main(String[] args) {
        run();
    }
}
