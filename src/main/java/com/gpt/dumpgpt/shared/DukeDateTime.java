package com.gpt.dumpgpt.shared;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DukeDateTime implements Serializable {
    public static final String DATETIME_INPUT_FORMAT = "dd/MM/yyyy HH:mm";
    public static final DateTimeFormatter DATETIME_INPUT_FORMATTER =
            DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);
    public static final String DATETIME_PRINT_FORMAT = "dd/MM/yyyy - hh:mma";
    public static final DateTimeFormatter DATETIME_PRINT_FORMATTER =
            DateTimeFormatter.ofPattern(DATETIME_PRINT_FORMAT);
    public static final String SERIALIZER_TYPE = "DukeDateTime";
    public static final String WRONG_DATETIME_FORMAT = "Expects datetime format to be dd/MM/yyyy HH:mm";

    private String originalDatetime = null;
    private LocalDateTime localDateTime = null;

    public DukeDateTime() {
    }

    public DukeDateTime(String datetime) throws DukeException {
        this(datetime, DATETIME_INPUT_FORMATTER);
    }

    public DukeDateTime(String datetime, DateTimeFormatter formatter) throws DukeException {
        originalDatetime = datetime;
        parse(datetime, formatter);
    }

    private void parse(String datetime, DateTimeFormatter formatter) throws DukeException {
        if (datetime == null) {
            return;
        }

        try {
            localDateTime = LocalDateTime.parse(datetime, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException(WRONG_DATETIME_FORMAT);
        }
    }

    public String format() {
        return format(DATETIME_PRINT_FORMATTER);
    }

    public String format(DateTimeFormatter formatter) {
        return localDateTime.format(formatter);
    }

    public LocalDateTime getDateTime() {
        return localDateTime;
    }

    public String getOriginalDatetime() {
        return originalDatetime;
    }

    @Override
    public Serializer serialize() throws DukeException {
        Serializer serializer = new Serializer();
        serializer.setType(SERIALIZER_TYPE);
        serializer.putString(format(DateTimeFormatter.ISO_DATE_TIME));
        return serializer;
    }

    @Override
    public void deserialize(Serializer serializer) throws DukeException, IOException {
        serializer.assertType(SERIALIZER_TYPE);
        parse(serializer.readString(), DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    public String toString() {
        return format();
    }
}
