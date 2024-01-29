package group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto {
    private String leaveNo;
    private String nic;
    //private User user;
}

