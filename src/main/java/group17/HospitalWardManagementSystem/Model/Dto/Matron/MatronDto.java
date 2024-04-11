package group17.HospitalWardManagementSystem.Model.Dto.Matron;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MatronDto {
    private String nic;
    private String fullName;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private LocalDate serviceDate;
    private String email;
    private String mobileNo;
}
