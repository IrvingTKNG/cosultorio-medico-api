package com.example.consultorio_medico_api.utils;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public final class DateUtils {
    private DateUtils() {
    }

    public static LocalDate objectToLocalDate(Object fecha) {
        LocalDate result = null;
        if (fecha != null) {
            result = ((Date) fecha).toLocalDate();
        }

        return result;
    }

    public static LocalTime objectToLocalTime(Object timeObject) {
        LocalTime result = null;
        if (timeObject != null) {
            result = ((Time) timeObject).toLocalTime();
        }

        return result;
    }

    public static final String LOCAL_DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("America/Mexico_City");
}
