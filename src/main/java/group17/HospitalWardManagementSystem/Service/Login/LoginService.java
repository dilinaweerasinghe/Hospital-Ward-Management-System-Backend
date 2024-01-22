package group17.HospitalWardManagementSystem.Service.Login;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.LoginDto;
import group17.HospitalWardManagementSystem.Repository.Login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticateUser(LoginDto loginDto){
        User user = userRepository.findByUsername(loginDto.getUsername());
        return passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
    }
}


