package group17.HospitalWardManagementSystem.Model.Dto.Matron;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectMatronDto {
    private String nic;
    private String name;
}
