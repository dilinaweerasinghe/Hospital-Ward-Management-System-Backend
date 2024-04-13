package group17.HospitalWardManagementSystem.Model.Dto;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ApproveLeaveDto {
    private int leaveId;

    private String leaveNo;
    private String name;

    private LocalDate leaveBeginDate;

    private LocalDate leaveEndDate;

    private LocalDate requestedDate;


}
