package group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto;

import group17.HospitalWardManagementSystem.Model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String fullName;
    private String nic;
    private UserRole position;
    private String leaveNum;
    private int numberOfTakenCasualLeaves;
    private int numberOfTakenVacationLeaves;
}

