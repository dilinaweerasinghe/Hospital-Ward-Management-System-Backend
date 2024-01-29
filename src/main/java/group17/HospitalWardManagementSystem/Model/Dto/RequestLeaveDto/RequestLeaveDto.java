package group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestLeaveDto {
    private int noOfRequestedDates;
    private LocalDate leaveBeginDate;
    private LocalDate leaveEndDate;
    private LocalDateTime requestedDateAndTime;
    private String reason;
    private LocalDate dueDate;
}
