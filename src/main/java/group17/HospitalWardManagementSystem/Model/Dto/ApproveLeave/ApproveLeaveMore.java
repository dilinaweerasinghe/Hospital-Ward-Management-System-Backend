package group17.HospitalWardManagementSystem.Model.Dto.ApproveLeave;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
public class ApproveLeaveMore extends ApproveLeaveDto{
    private int totalLeaveOfTheMonth;
    private String reason;
    private int remainingCasualLeaves;
    private int remainingVacationLeave;

}
