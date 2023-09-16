package RC.task;

import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String text = "T | 0 | " + this.getDescription() + "\n";
        fw.write(text);
        fw.close();
    }
}
