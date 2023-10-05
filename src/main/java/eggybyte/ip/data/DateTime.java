package eggybyte.ip.data;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.util.Logger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.google.gson.annotations.SerializedName;

/**
 * Customized class for showing date time and parsing supported string to date
 * time.
 */
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

    /**
     * Modifying an existing date time with a rawData String.
     *
     * @param rawData A String that needs to comply with a supported format and
     *                indicates a correct time that will be recorded by this Date
     *                instance.
     * @throws TipsException Any excption will be throw in this type, which contains
     *                       information about this exception and the possible
     *                       solution.
     */
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

    /**
     * Create a new date time.
     *
     * @param rawData A String that needs to comply with a supported format and
     *                indicates a correct time that will be recorded by this Date
     *                instance time.
     * @throws TipsException Any excption will be throw in this type, which contains
     *                       information about this exception and the possible
     *                       solution.
     */
    public DateTime(String rawData) throws TipsException {
        setRawData(rawData);
    }

    @Override
    public String toString() {
        return dateTime.format(toStringFormatter);
    }

    /**
     * Compare the dates of this DateTime and another DateTime.
     *
     * @param dateTime The other DateTime that is used to compare.
     * @return If the date of current instance is earlier,it returns -1.
     *         If the date of current instance is later,it returns 1.
     *         Otherwise(when they're at exactly the same date) it returns 0.
     */
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

    /**
     * Compare the dates of this DateTime and another Date.
     *
     * @param date The other Date that is used to compare.
     * @return If the date of current instance is earlier,it returns -1.
     *         If the date of current instance is later,it returns 1.
     *         Otherwise(when they're at exactly the same date) it returns 0.
     */
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
