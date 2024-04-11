package group17.HospitalWardManagementSystem.Model.Dto.Matron;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetMatronDto {
    private String nic;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
}
