package group17.HospitalWardManagementSystem.Model.Dto.Profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProDetailsUpdateDto {

    private String nic;

    private String fullName;

    private String username;

    private LocalDate dob;

    private String email;

    private String mobileNo;
}
