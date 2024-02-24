package group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import jakarta.persistence.*;
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

    private String nic;
    private LocalDate leaveBeginDate;
    private LocalDate leaveEndDate;
    private LocalDateTime requestedDateAndTime;
    private String reason;
}
