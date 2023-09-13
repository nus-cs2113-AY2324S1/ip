package Objects;

public class events extends deadlines {
    protected String end;

    public events(String description, String by, String end) {
        super(description,by);
        this.end = end;
        this.type = 'E';
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)",this.description,this.by,this.end);
    }
}
