package RC.task;

import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String text = "D | 0 | " + this.getDescription() + " | " + this.by + "\n";
        fw.write(text);
        fw.close();
    }
}
