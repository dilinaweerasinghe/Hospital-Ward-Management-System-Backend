package group17.HospitalWardManagementSystem.Service.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.FullStaffMmeberDetailsDto;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.ShowToUpdateDto;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditStaffMemberDetailsService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WardRepository wardRepository;


    public FullStaffMmeberDetailsDto findSister(String wardNo){

        FullStaffMmeberDetailsDto showSisterDto = new FullStaffMmeberDetailsDto();

        Ward ward = wardRepository.findByWardNo(wardNo);
        String nic = staffRepository.findByWard(ward);

        Staff staff = staffRepository.findByNic(nic);

        Optional<User> user = userRepository.findByNic(nic);

        if(user.isEmpty()){
            System.out.println("User empty");
            return null;
        }
        else{
            User sister = user.get();

            showSisterDto.setNic(sister.getNic());
            showSisterDto.setPosition(sister.getPosition());
            showSisterDto.setLeaveNo(staff.getLeaveNum());
            showSisterDto.setBirthdate(sister.getDob());
            showSisterDto.setServiceStartDate(sister.getCareerStatedDate());
            showSisterDto.setFullName(sister.getFullName());
            showSisterDto.setFirstName(sister.getFirstName());
            showSisterDto.setLastName(sister.getLastName());
            showSisterDto.setEmail(sister.getEmail());
            showSisterDto.setMobileNo(sister.getMobileNo());

            return showSisterDto;
        }
    }

    public boolean saveStaffMemberEditedDetails(FullStaffMmeberDetailsDto sisterDto){

        Optional<User> member = userRepository.findByNic(sisterDto.getNic());

        if(member.isPresent()){

            User editedMember =member.get();
            Staff staff = staffRepository.findByNic(sisterDto.getNic());

            editedMember.setFirstName(sisterDto.getFirstName());
            editedMember.setLastName(sisterDto.getLastName());
            editedMember.setFullName(sisterDto.getFullName());
            editedMember.setEmail(sisterDto.getEmail());
            editedMember.setMobileNo(sisterDto.getMobileNo());
            editedMember.setNic(sisterDto.getNic());
            editedMember.setDob(sisterDto.getBirthdate());
            editedMember.setPosition(sisterDto.getPosition());
            editedMember.setCareerStatedDate(sisterDto.getServiceStartDate());

            staff.setLeaveNum(sisterDto.getLeaveNo());
            //staff.setServiceStartedDate(sisterDto.getServiceStartDate());

            userRepository.save(editedMember);
            staffRepository.save(staff);

            return true;
        }
        else{
            return false;
        }
    }

    public Boolean saveNurseEditedDetails(ShowToUpdateDto nurseDto){

        User member = userRepository.findByNic(nurseDto.getNic()).orElseThrow(()->new EntityNotFoundException("Staff member do not found"));




            Staff staff = staffRepository.findByNic(nurseDto.getNic());
            Ward ward = wardRepository.findByWardNo(nurseDto.getWardNo());

            member.setFirstName(nurseDto.getFirstName());
            member.setLastName(nurseDto.getLastName());
            member.setFullName(nurseDto.getFullName());
            member.setEmail(nurseDto.getEmail());
            member.setMobileNo(nurseDto.getMobileNo());
            member.setNic(nurseDto.getNic());
            member.setDob(nurseDto.getDob());
            staff.setWardNo(ward);
            staff.setRemainingCasualLeaves(nurseDto.getRemainingVacationLeaves());
            staff.setRemainingCasualLeaves(nurseDto.getRemainingCasualLeaves());
           // staff.setServiceStartedDate(nurseDto.getServiceStartedDate());
            member.setCareerStatedDate(nurseDto.getServiceStartedDate());
            staff.setLeaveNum(nurseDto.getLeaveNo());
            //member.setPosition(nurseDto.getPosition());


            userRepository.save(member);
            staffRepository.save(staff);

            return true;



    }
}
