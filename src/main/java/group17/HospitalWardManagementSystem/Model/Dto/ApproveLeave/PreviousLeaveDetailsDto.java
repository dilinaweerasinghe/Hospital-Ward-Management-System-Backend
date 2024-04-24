package group17.HospitalWardManagementSystem.Model.Dto.ApproveLeave;

import group17.HospitalWardManagementSystem.Model.LeaveStatus;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PreviousLeaveDetailsDto {
    private int leaveId;
    private int numberOfLeaveDays;
    private LocalDate leaveBeginDate;
    private String Reason;
    private LeaveStatus leaveStatus;

}
