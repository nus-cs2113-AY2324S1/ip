import java.io.*;
import java.util.Scanner;

public class Nupjuk {

    /* for file output */
    static PrintWriter pw;


    private static Storage storage;
    private static TaskList tasks;

    private static Ui ui;

    public Nupjuk(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadTask());
        } catch (FileNotFoundException e){
            tasks = new TaskList();
            ui.printLine ("Creating New File");
        }
    }

    /**
     * runs the program until user says "Bye"
     *
     * @throws IOException if problem in input and parsing
     */
    public void run() throws IOException{
        Scanner input = new Scanner(System.in);

        boolean isTerminate = false;

        while(!isTerminate){ // run program until terminate
            String cmd = input.nextLine();
            isTerminate = ui.runCommand(cmd, tasks, storage);
        }

        ui.exitProg();
    }

    public static void main(String[] args) throws IOException{
        new Nupjuk("./nupjuk.txt").run();
    }
}
