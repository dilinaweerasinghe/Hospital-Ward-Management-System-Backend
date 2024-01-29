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
public class UserDto {
    private String fullName;
    private UserRole position;
}
