package group17.HospitalWardManagementSystem.Model.Dto.StaffDto;

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
public class ShowStaffDto {
    private String nic;
    private String fullName;
    private String email;
    private String mobileNo;
    private UserRole userRole;

}
