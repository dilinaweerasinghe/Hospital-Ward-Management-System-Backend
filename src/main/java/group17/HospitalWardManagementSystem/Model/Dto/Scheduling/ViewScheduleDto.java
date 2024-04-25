package group17.HospitalWardManagementSystem.Model.Dto.Scheduling;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewScheduleDto {
    private String morning;
    private String evening;
    private String night;
}
