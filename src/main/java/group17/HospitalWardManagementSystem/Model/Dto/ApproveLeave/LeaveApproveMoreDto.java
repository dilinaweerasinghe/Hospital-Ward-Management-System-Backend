package group17.HospitalWardManagementSystem.Model.Dto.ApproveLeave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
public class LeaveApproveMoreDto {
        private String name;
        private LocalDateTime requestedDateTime;
        private LocalDate leaveStartDate;
        private LocalDate leaveEndDate;
        private int numberOfLeavesInMonth;
        private int remainingCasualityLeaves;
        private int remainingVacationalLeave;
        private String reason;
}
