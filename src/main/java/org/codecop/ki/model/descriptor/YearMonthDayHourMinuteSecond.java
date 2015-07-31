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
public class YearMonthDayHourMinuteSecond {

    private static final DateFormat YEAR_MONTH_DAY_HOUR_MINUTES_SECONDS_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    String formattedDate;

    public YearMonthDayHourMinuteSecond(String formattedDate) {
        if (!formattedDate.matches("[12]\\d{3}[01]\\d[0-3]\\d[0-2]\\d[0-5]\\d[0-5]\\d")) {
            throw new IllegalArgumentException(formattedDate);
        }
        this.formattedDate = formattedDate;
    }

    @Override
    public String toString() {
        return formattedDate;
    }

    @SuppressWarnings("null")
    public static synchronized YearMonthDayHourMinuteSecond from(Date date) {
        return new YearMonthDayHourMinuteSecond(YEAR_MONTH_DAY_HOUR_MINUTES_SECONDS_FORMAT.format(date));
    }

}
