package Duchess.TaskObjects;

import Duchess.FunctionObjects.DateParser;
import java.util.Scanner;

/** Event class for event tasks. Extension of Task class.
 * 
 */
public class Event extends Task{
    private String Starttime, endTime;

    /** Empty constructor. */
    public Event() {
        super();
    }

    /** Main constructor for Event class.
     * @param name Name of task.
     * @param Starttime Start time of event.
     * @param endTime End time of event.
     */
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

    /** Sets new parameters of event task in case of error or edits.
     * @param sc Scanner to take in user input.
     */
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

    /** Converts Event object to string.
     * @return String representation of Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.Starttime + " to: " + this.endTime + ")";
    }

    /** Converts Event object to string for saving.
     * @return String representation of Event object for saving.
     */
    @Override
    public String toFileString(){
        return "E | " + super.toFileString() + " | " + this.Starttime + " | " + this.endTime;
    }

}
