package group17.HospitalWardManagementSystem.Model.Dto.ApproveLeave;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
public class ApproveLeaveMore extends ApproveLeaveDto{
    private String totalLeaveOfTheMonth;
    private String reason;
    private int remainingCasualLeaves;
    private int remainingVacationLeave;
    private Set<PreviousLeaveDetailsDto> previousLeaveDetails = new HashSet<>();

}
