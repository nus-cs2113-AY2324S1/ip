package jarvis.tasks;
import java.time.LocalDateTime;

public class EventTimeRange {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public EventTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "from: " + startTime + " to: " + endTime;
    }
}
