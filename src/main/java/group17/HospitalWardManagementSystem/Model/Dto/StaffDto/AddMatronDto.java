package group17.HospitalWardManagementSystem.Model.Dto.StaffDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddMatronDto {
    private String nic;
    private String fullName;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String mobileNo;
}
