package Objects;

public class Events extends Deadlines {
    protected String end;

    public Events(String description, String by, String end) {
        super(description,by);
        this.end = end;
        this.type = 'E';
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)",this.description,this.by,this.end);
    }
}
