package group17.HospitalWardManagementSystem.Service.WardDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.WardDto.ShowWardDto;
import group17.HospitalWardManagementSystem.Model.Dto.WardDto.ShowWardMore;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowWardService {


    private final WardRepository wardRepository;
    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    @Autowired
    public ShowWardService(WardRepository wardRepository, StaffRepository staffRepository, UserRepository userRepository) {
        this.wardRepository = wardRepository;
        this.staffRepository = staffRepository;
        this.userRepository = userRepository;
    }

    public ShowWardDto showWardDetails(String wardName){

        ShowWardDto wardDto=new ShowWardDto();

        Ward targetWard=findWard(wardName);

        wardDto.setWardNo(targetWard.getWardNo());
        wardDto.setWardName(targetWard.getWardName());
        wardDto.setNumberOfNurses(targetWard.getNumberOfNurses());
        wardDto.setSisterName(findSister(targetWard));
        wardDto.setMatronName(findMatronName(targetWard.getMatron().getNic()));

        return wardDto;

    }

    public List<String> getAllWardNames(){
        List<String> wardNames = new ArrayList<>();

        List<Ward> wards = wardRepository.findAll();
        if(wards.isEmpty()){
            throw new IllegalArgumentException("There is not exist any ward!");
        }

        for(Ward ward : wards){
            wardNames.add(ward.getWardName());
        }
        return wardNames;
    }

    public ShowWardDto showWardDetailsByWardNo(String wardNo){

        ShowWardDto wardDto=new ShowWardDto();

        Ward targetWard = wardRepository.findById(wardNo).orElseThrow(()->new EntityNotFoundException("Cannot found ward details under ward No: " + wardNo));

        wardDto.setWardNo(targetWard.getWardNo());
        wardDto.setWardName(targetWard.getWardName());
        wardDto.setNumberOfNurses(targetWard.getNumberOfNurses());
        wardDto.setSisterName(findSister(targetWard));
        wardDto.setMatronName(findMatronName(targetWard.getMatron().getNic()));

        return wardDto;

    }

    public List<ShowWardDto> showAllWardDetails(){

        List<ShowWardDto> wardDtos = new ArrayList<>();
        List<Ward> wards = wardRepository.findAll();

        if(wards.isEmpty()){
            throw new IllegalArgumentException("Ward list is empty!");
        }

        for(Ward targetWard : wards) {
            ShowWardDto wardDto = new ShowWardDto();
            wardDto.setWardNo(targetWard.getWardNo());
            wardDto.setWardName(targetWard.getWardName());
            wardDto.setNumberOfNurses(targetWard.getNumberOfNurses());
            String sisterName = findSister(targetWard);
            wardDto.setSisterName(sisterName != null ? sisterName : "Not Assigned");
            wardDto.setMatronName(
                    targetWard.getMatron() == null
                            ? "Not Assigned"
                            : findMatronName(targetWard.getMatron().getNic())
            );
            wardDtos.add(wardDto);
        }
        return wardDtos;

    }

    public Ward findWard(String wardName){
        return wardRepository.findByWardName(wardName);
    }

    public String findSister(Ward wardNo){
        Staff staff = staffRepository.findSisterByWard(wardNo);
        if (staff != null) {
            return findSisterName(staff.getNic());
        } else {
            return null; // or handle the case where staff is null
        }
    }


    public String findSisterName(String nic){
        Optional<User> user = userRepository.findByNic(nic);
        if(user.isPresent()){
            User user1 = user.get();
            return user1.getFullName();
        }
        else{
            System.out.println("user is null");
            return null;

        }
    }

    public String findMatronName(String nic){
        User user = userRepository.findByNic(nic).orElseThrow(
                () -> new EntityNotFoundException("Matron not found with NIC: " + nic));

        return user.getFullName();
    }

    public ShowWardMore getWardMoreDetails(String wardNo){
        ShowWardMore showWardMore = new ShowWardMore();
        Ward ward = wardRepository.findById(wardNo).orElseThrow(
                () -> new EntityNotFoundException("Ward not found with Ward No: " + wardNo)
        );

        showWardMore.setWardName(ward.getWardName());
        showWardMore.setMatronName(ward.getMatron() == null
                ? "Not Assigned"
                : findMatronName(ward.getMatron().getNic()));
        showWardMore.setMatronNic(ward.getMatron() == null
                ? "Not Assigned" : ward.getMatron().getNic());
        showWardMore.setNumberOfNurses(ward.getNumberOfNurses());
        showWardMore.setMorningShiftNurses(ward.getMorningShift());
        showWardMore.setEveningShiftNurses(ward.getEveningShift());
        showWardMore.setNightShiftNurses(ward.getNightShift());

        return showWardMore;
    }

}
