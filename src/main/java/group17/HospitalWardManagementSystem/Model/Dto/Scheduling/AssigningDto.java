package group17.HospitalWardManagementSystem.Model.Dto.Scheduling;

import group17.HospitalWardManagementSystem.Model.DutyTime;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssigningDto {
    private String nurseNic;
    private LocalDate date;
    private String dutyTime;
}
