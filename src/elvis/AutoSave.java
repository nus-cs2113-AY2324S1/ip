package elvis;

import java.io.File;

public class AutoSave {

    public static void saver() {
        System.out.println("SAVING...");
        File f = new File("saved.txt");
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
        System.out.println("SAVED");
    }

}
