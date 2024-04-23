package group17.HospitalWardManagementSystem.Model.Dto.Scheduling;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateListDto {
    private String nic;
    private String fullName;
    private LocalDate serviceStartedDate;
    private int workingHours;
}
