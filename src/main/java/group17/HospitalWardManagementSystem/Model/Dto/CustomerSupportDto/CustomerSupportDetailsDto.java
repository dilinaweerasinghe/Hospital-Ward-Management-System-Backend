package group17.HospitalWardManagementSystem.Model.Dto.CustomerSupportDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSupportDetailsDto {
    private String nic;
    private String username;
    private String email;
    private String description;
}
