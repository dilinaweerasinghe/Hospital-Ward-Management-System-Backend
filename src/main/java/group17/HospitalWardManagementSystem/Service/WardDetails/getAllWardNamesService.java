package group17.HospitalWardManagementSystem.Service.WardDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Matron;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.WardDto.AllWardNamesDto;
import group17.HospitalWardManagementSystem.Repository.MatronRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class getAllWardNamesService {

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private MatronRepository matronRepository;

    @Autowired
    private UserRepository userRepository;

    public Set<String> allWardsName(String username){

        String nic  = getMatronNic(username);
        Matron matron = matronRepository.findByNic(nic);
        Set<Ward> allWardNamesDto = matron.getWards();
        return allWardNamesDto.stream().map(Ward::getWardName).collect(Collectors.toSet());
    }

    public String getMatronNic(String username){
        User user = userRepository.findByUsername(username);
        return user.getNic();
    }
}
