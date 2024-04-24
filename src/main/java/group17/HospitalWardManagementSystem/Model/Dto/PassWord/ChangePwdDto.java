package group17.HospitalWardManagementSystem.Model.Dto.PassWord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePwdDto{

    private String username;

    private String currentPassword;

    private String newPassword;

    private String confirmPassword;

}
