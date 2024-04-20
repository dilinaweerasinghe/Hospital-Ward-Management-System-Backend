package group17.HospitalWardManagementSystem.Model.Dto.StaffDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import group17.HospitalWardManagementSystem.Model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FullStaffMmeberDetailsDto {
    private String fullName;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private String nic;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    private UserRole position;
    private String leaveNo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate serviceStartDate;

}
