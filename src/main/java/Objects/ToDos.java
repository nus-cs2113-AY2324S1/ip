package Objects;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
        this.isDone = false;
        this.type = 'T';
    }
    
    @Override
     public String toString() {
         return String.format(this.description);
     }
}
