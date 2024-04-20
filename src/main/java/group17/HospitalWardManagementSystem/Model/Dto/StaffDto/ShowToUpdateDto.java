package group17.HospitalWardManagementSystem.Model.Dto.StaffDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowToUpdateDto {
    private String nic;
    private String fullName;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String wardNo;
    private String email;
    private String position;
    private String mobileNo;
    private LocalDate serviceStartedDate;
    private String leaveNo;
    private int remainingVacationLeaves;
    private int remainingCasualLeaves;

}
