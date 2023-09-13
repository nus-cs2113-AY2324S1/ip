package Objects;

public class deadlines extends toDos {

    protected String by;

    public deadlines(String description, String by) {
        super(description);
        this.by = by;
        this.type = 'D';
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }
    
    @Override
     public String toString() {
         return String.format("%s (by: %s)",this.description,this.by);
     }
}
