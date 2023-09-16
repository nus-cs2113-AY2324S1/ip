package RC.task;

import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String text = "E | 0 | " + this.getDescription() + " | " + this.from + "-" + this.to + "\n";
        fw.write(text);
        fw.close();
    }
}
