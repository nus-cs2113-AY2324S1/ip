package Duchess.FunctionObjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class DateParser {

    private String[] rawdate;
    private int Time;
    private LocalDate date;

    public DateParser(String date){
        //split rawdat into date and time
        this.rawdate = date.split(" ");
        try{
            this.Time = Integer.parseInt(this.rawdate[1].strip());
            this.date = LocalDate.parse(this.rawdate[0].strip());
        }catch(Exception e){
            System.out.println(e.getMessage());
            try{
                this.Time = 0;
                this.date = LocalDate.parse(date);
                }catch(Exception f){
                    System.out.println(f.getMessage());
                    this.date = null;

            }
        }
        
    }

    public int getTime(){
        return this.Time;
    }

    public String getDate(){
        if (this.date == null){
            return null;
        }
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    
}
