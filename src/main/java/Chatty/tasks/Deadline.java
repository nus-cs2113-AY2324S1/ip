package Chatty.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String getDescription() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return "[D][" + getStatusIcon() + "] " + super.getDescription() + " (by: " + formattedDate + ")";
    }

    @Override
    public String saveFormat() {
        return "D | " + (getIsDone() ? "1" : "0") + " | " + description + " | " + by;
    }
}
