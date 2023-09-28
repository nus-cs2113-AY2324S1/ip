package Duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate endDate;

    public Deadline(String description, LocalDate endDate) {
        super(description);
        this.endDate = endDate;
        taskType = "Deadline";
        symbol = "D";
    }

    @Override
    public String convertToSaveFormat() {
        String doneMarker = isDone ? "1" : "0";
        String endDateString = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return symbol + " | " + doneMarker + " | " + description + "/by " + endDateString;
    }

    @Override
    public String toString() {
        String endDateString = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "\t[D]" + super.toString() + " (by: " + endDateString + ")";
    }

}
