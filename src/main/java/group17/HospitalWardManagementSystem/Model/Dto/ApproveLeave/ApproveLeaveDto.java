package group17.HospitalWardManagementSystem.Model.Dto.ApproveLeave;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class ApproveLeaveDto {
    private int leaveId;

    private String leaveNo;

    private String name;

    private LocalDate leaveBeginDate;

    private LocalDate leaveEndDate;

    private LocalDate requestedDate;


}
