package org.codecop.ki.model.descriptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import org.codecop.ki.model.descriptor.ValueObject;

@ValueObject
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
@EqualsAndHashCode
public class Year {

    private static final DateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");

    String year;

    public Year(String year) {
        if (!year.matches("[12]\\d{3}")) {
            throw new IllegalArgumentException(year);
        }
        this.year = year;
    }

    @Override
    public String toString() {
        return year;
    }

    @SuppressWarnings("null")
    public static synchronized Year from(Date date) {
        return new Year(YEAR_FORMAT.format(date));
    }

}
