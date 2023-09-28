package Duchess.TaskObjects;

import Duchess.FunctionObjects.DateParser;
import java.util.Scanner;


public class Event extends Task{
    private String Starttime, endTime;

    public Event() {
        super();
    }

    public Event(String name, String Starttime, String endTime) {
        super(name);

        DateParser parsedStart = new DateParser(Starttime);
        DateParser parsedEnd = new DateParser(endTime);
        
        if (parsedStart.getDate() == null) {
            this.Starttime = Starttime;
        } else {
            this.Starttime = parsedStart.getDate();
        }

        if (parsedEnd.getDate() == null) {
            this.endTime = endTime;
        } else {
            this.endTime = parsedEnd.getDate();
        }
    }

    public void makeNewEvent(Scanner sc){
        System.out.println("What is the name of the event?");
        String name = sc.nextLine();
        System.out.println("When does the event start?");
        String Starttime = sc.nextLine();
        System.out.println("When does the event end?");
        String endTime = sc.nextLine();
        this.setName(name);
        this.Starttime = Starttime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.Starttime + " to: " + this.endTime + ")";
    }

    @Override
    public String toFileString(){
        return "E | " + super.toFileString() + " | " + this.Starttime + " | " + this.endTime;
    }

}
