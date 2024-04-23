package group17.HospitalWardManagementSystem.Service.Profile;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.PassWord.ChangePwdDto;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChangePwdService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    public String changePassword(ChangePwdDto changePwdDto,String nic) throws RuntimeException {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(changePwdDto.getUsername(), changePwdDto.getCurrentPassword()));
        User user=findUser(nic);
        if(authenticate.isAuthenticated()){
            user.setPassword(getEncodedPassword(changePwdDto.getNewPassword()));
            userRepository.save(user);
            return "Successfully changed the password";
        }else{
            throw new RuntimeException("Current Password is not match");
        }
    }

    public User findUser(String nic){
        return userRepository.findByNic(nic).get();
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
