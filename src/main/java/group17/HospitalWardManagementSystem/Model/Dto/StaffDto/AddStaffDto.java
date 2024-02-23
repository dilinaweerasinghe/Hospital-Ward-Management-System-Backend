package group17.HospitalWardManagementSystem.Model.Dto.StaffDto;

import group17.HospitalWardManagementSystem.Model.Domain.Ward;
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
    private LocalDate dob;
    private String email;
    private String position;
    private String mobileNo;
    private LocalDate serviceStartedDate;
    private String wardNo;//new add
    private String leaveNum;
    private int remainingCasualLeaves;
    private int remainingVacationLeave;

}
