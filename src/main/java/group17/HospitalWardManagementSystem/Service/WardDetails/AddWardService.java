package group17.HospitalWardManagementSystem.Service.WardDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Matron;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.Matron.MatronDto;
import group17.HospitalWardManagementSystem.Model.Dto.WardDto.AddWardDto;
import group17.HospitalWardManagementSystem.Repository.MatronRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AddWardService {
    @Autowired
    public WardRepository wardRepository;

    @Autowired
    public MatronRepository matronRepository;



    public String saveWard(AddWardDto addWardDto){
        Ward ward=new Ward();

        ward.setWardNo(addWardDto.getWardNo());
        ward.setWardName(addWardDto.getWardName());

        ward.setMatron(findMatron(addWardDto));
        ward.setNumberOfNurses(addWardDto.getNumberOfNurses());
        ward.setMorningShift(addWardDto.getMorningShift());
        ward.setEveningShift(addWardDto.getEveningShift());
        ward.setNightShift(addWardDto.getNightShift());

        wardRepository.save(ward);
        return findMatron(addWardDto).getNic();
    }


    public Matron findMatron(AddWardDto addWardDto){

        return  matronRepository.findByNic(addWardDto.getMatron());
    }

    public List<String> allMatrons(){
        return matronRepository.findAllNic();
    }

}
