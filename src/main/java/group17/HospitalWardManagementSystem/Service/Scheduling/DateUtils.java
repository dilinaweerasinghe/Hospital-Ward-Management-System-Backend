package group17.HospitalWardManagementSystem.Service.Scheduling;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class DateUtils {
    public static LocalDate getStartOfWeek() {
        LocalDate today = LocalDate.now();
        return today.with(DayOfWeek.MONDAY);
    }

    public static LocalDate getStartOfWeekForDate(LocalDate date) {

        return date.with(DayOfWeek.MONDAY);
    }

    public static LocalDate getEndOfWeek() {
        LocalDate today = LocalDate.now();
        return today.with(DayOfWeek.FRIDAY);
    }

    public static LocalDate getToday() {
        return LocalDate.now();
    }

    public static String getDayOfWeekName(LocalDate date) {
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
}
