package group17.HospitalWardManagementSystem.Model.Dto;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private User user;
    private String jwtToken;
    private String imgUrl;

}
