package Task;

public class Task {
    protected String name;
    protected boolean done;

    public Task(String name){
        this.name = name;
        this.done = false;
    }
    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean getDone(){
        return done;
    }

    @Override
    public String toString(){
        String out = "["
                + (done ? "X" : " ")
                + "] " + this.name;
        return out;
    }


    public static class ToDo extends Task {

        public ToDo(String name){
            super(name);
        }

        @Override
        public String toString(){
            String out = "[T]["
                    + (done ? "X" : " ")
                    + "] " + super.name;
            return out;
        }
    }

    public static class Event  extends Task {
        public String from;
        public String to;

        public Event(String name, String from, String to){
            super(name);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString(){
            String out = "[E]["
                    + (done ? "X" : " ")
                    + "] " + super.name
                    + " (from: " + from
                    + " to: " + to + ")";
            return out;
        }
    }

    public static class Deadline extends Task {
        protected String by;
        public Deadline(String name, String by){
            super(name);
            this.by = by;
        }

        @Override
        public String toString(){
            String out = "[D]["
                    + (done ? "X" : " ")
                    + "] " + super.name
                    + " (by: " + by + ")";
            return out;
        }
    }
}
