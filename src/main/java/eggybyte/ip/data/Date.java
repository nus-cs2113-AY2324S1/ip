package eggybyte.ip.data;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.util.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.google.gson.annotations.SerializedName;

public class Date {
    transient LocalDate date;

    @SerializedName("rawData")
    public String standardString;

    private static DateTimeFormatter[] formatters = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("M-d-yyyy"),
            DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH), };
    private static DateTimeFormatter toStringFormatter = formatters[formatters.length - 1];

    public void setRawData(String rawData) throws TipsException {
        for (DateTimeFormatter formatter : formatters) {
            try {
                date = LocalDate.parse(rawData, formatter);
                standardString = this.toString();
                return;
            } catch (Exception exception) {
            }
        }
        throw new TipsException("Unable to parse date time!",
                "Please check the date time string you have input, and make sure you are using the supported format!");
    }

    public Date(String rawData) throws TipsException {
        setRawData(rawData);
    }

    @Override
    public String toString() {
        return date.format(toStringFormatter);
    }
}
