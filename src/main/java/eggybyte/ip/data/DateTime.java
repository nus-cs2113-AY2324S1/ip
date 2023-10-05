package eggybyte.ip.data;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.util.Logger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.google.gson.annotations.SerializedName;

public class DateTime {
    transient LocalDateTime dateTime;

    @SerializedName("rawData")
    public String standardString;

    private static DateTimeFormatter[] formatters = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-M-d HHmm"),
            DateTimeFormatter.ofPattern("yyyy-M-d H:m"),
            DateTimeFormatter.ofPattern("M-d-yyyy HHmm"),
            DateTimeFormatter.ofPattern("M-d-yyyy H:m"),
            DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm", Locale.ENGLISH), };
    private static DateTimeFormatter toStringFormatter = formatters[formatters.length - 1];

    public void setRawData(String rawData) throws TipsException {
        for (DateTimeFormatter formatter : formatters) {
            try {
                dateTime = LocalDateTime.parse(rawData, formatter);
                standardString = this.toString();
                return;
            } catch (Exception exception) {
            }
        }
        throw new TipsException("Unable to parse date time!",
                "Please check the date time string you have input, and make sure you are using the supported format!");
    }

    public DateTime(String rawData) throws TipsException {
        setRawData(rawData);
    }

    @Override
    public String toString() {
        return dateTime.format(toStringFormatter);
    }

    public int compareDate(DateTime dateTime) {
        LocalDate date1 = this.dateTime.toLocalDate(), date2 = dateTime.dateTime.toLocalDate();
        if (date1.isBefore(date2)) {
            return 1;
        }
        if (date1.isAfter(date2)) {
            return -1;
        }
        return 0;
    }

    public int compareDate(Date date) {
        LocalDate date1 = this.dateTime.toLocalDate(), date2 = date.date;
        if (date1.isBefore(date2)) {
            return -1;
        }
        if (date1.isAfter(date2)) {
            return 1;
        }
        return 0;
    }
}
