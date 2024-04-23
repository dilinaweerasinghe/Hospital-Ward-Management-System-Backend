package group17.HospitalWardManagementSystem.Service.Scheduling;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class DateUtils {
    public static LocalDate getStartOfWeek() {
        LocalDate today = LocalDate.now();
        return today.with(DayOfWeek.MONDAY);
    }

    public static LocalDate getEndOfWeek() {
        LocalDate today = LocalDate.now();
        return today.with(DayOfWeek.FRIDAY);
    }

    public static LocalDate getToday() {
        return LocalDate.now();
    }
}
