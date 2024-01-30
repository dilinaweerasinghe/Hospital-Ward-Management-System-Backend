package group17.HospitalWardManagementSystem.Model.Dto.AddStaffDto;

import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddStaffDto {

    private String nic;
    private String fullName;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private LocalDate dob;
    private String email;
    private String position;
    private String mobileNo;
    private LocalDate serviceStartedDate;
    private String leaveNum;
    private int remainingCasualLeaves;
    private int remainingVacationLeave;

}
