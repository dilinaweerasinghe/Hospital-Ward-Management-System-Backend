package group17.HospitalWardManagementSystem.Service.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.AddStaffDto;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.MailDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.*;
import group17.HospitalWardManagementSystem.Service.GeneralServices.MailService;
import group17.HospitalWardManagementSystem.Service.GeneralServices.PasswordGenerateService;
import group17.HospitalWardManagementSystem.Service.GeneralServices.UsernameGenerateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddStaffService {

    @Autowired
    public AddStaffRepository addStaffRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AddUserRepository addUserRepository;

    @Autowired
    public WardRepository wardRepository;

    @Autowired
    public MailService mailService;

    @Autowired
    private PasswordGenerateService passwordGenerateService;

    @Autowired
    private UsernameGenerateService usernameGenerateService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffRepository staffRepository;


    @Transactional
    public String staffSave(AddStaffDto addStaffDto){

        User user=new User();
        Staff staff=new Staff();
        Ward ward= wardRepository.findByWardNo(addStaffDto.getWardNo());
        ward.setNumberOfNurses(ward.getNumberOfNurses()+1);


        if(Objects.equals(addStaffDto.getPosition(), "Sister")){
            Staff existingSister = staffRepository.findSisterByWard(ward);
            if(existingSister != null && (!Objects.equals(existingSister.getNic(), addStaffDto.getNic()))){
                User existingUser = userRepository.findById(existingSister.getNic()).orElseThrow(()-> new EntityNotFoundException("User can not find for given nic"));
                existingUser.setUsername(null);
                existingUser.setPassword(null);
                staffRepository.deleteById(existingSister.getNic());

            }
        }


        String password=passwordGenerateService.passwordGenerate();

        String username=usernameGenerateService.generateUsername(addStaffDto.getEmail());


        user.setNic(addStaffDto.getNic());
        user.setFullName(addStaffDto.getFullName());
        user.setFirstName(addStaffDto.getFirstName());
        user.setLastName(addStaffDto.getLastName());
        user.setUsername(username);
        user.setPassword(getEncodedPassword(password));
        user.setDob(addStaffDto.getDob());
        user.setEmail(addStaffDto.getEmail());
        if(addStaffDto.getPosition().equals("Admin")){
            user.setPosition(UserRole.Admin);
        }else if(addStaffDto.getPosition().equals("Matron")){
            user.setPosition(UserRole.Matron);
        }else if(addStaffDto.getPosition().equals("Sister")){
            user.setPosition(UserRole.Sister);
        }else if(addStaffDto.getPosition().equals("Nurse")){
            user.setPosition(UserRole.Nurse);
        }
        user.setMobileNo(addStaffDto.getMobileNo());
        staff.setNic(addStaffDto.getNic());
        staff.setWardNo(findWard(addStaffDto));
        //staff.setServiceStartedDate(addStaffDto.getServiceStartedDate());
        user.setCareerStatedDate(addStaffDto.getServiceStartedDate());
        staff.setLeaveNum(addStaffDto.getLeaveNum());
        staff.setRemainingCasualLeaves(addStaffDto.getRemainingCasualLeaves());
        staff.setRemainingVacationLeave(addStaffDto.getRemainingVacationLeave());

         //new add
        addUserRepository.save(user);
        addStaffRepository.save(staff);

        MailDto mailDto=new MailDto();

        mailDto.setUsername(username);
        mailDto.setPassword(password);

        mailService.sendMail(user.getEmail(),mailDto);

        return "Successfully added ward Details";
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public Ward findWard(AddStaffDto addStaffDto){
        return wardRepository.findByWardNo(addStaffDto.getWardNo());
    }


    public List<String> findAllWardNumbers(){
        List<Ward> wards = wardRepository.findAll();
        return wards.stream().map(Ward::getWardNo).collect(Collectors.toList());
    }

    @Transactional
    public AddStaffDto getExistingUser(String nic){
        System.out.println(nic);
        Optional<User> user= userRepository.findByNic(nic);
        if(user.isPresent()){
            User existingUser = user.get();
            Staff staff = staffRepository.findByNic(nic);
            AddStaffDto addStaffDto = new AddStaffDto();

            addStaffDto.setFirstName(existingUser.getFirstName());
            addStaffDto.setLastName(existingUser.getLastName());
            addStaffDto.setFullName(existingUser.getFullName());
            addStaffDto.setNic(existingUser.getNic());
            addStaffDto.setDob(existingUser.getDob());
            addStaffDto.setEmail(existingUser.getEmail());
            addStaffDto.setPosition(existingUser.getPosition().toString());
            addStaffDto.setMobileNo(existingUser.getMobileNo());

            if(staff != null ){
                addStaffDto.setServiceStartedDate(staff.getServiceStartedDate());
                addStaffDto.setWardNo(staff.getWardNo().getWardNo());
                addStaffDto.setLeaveNum(staff.getLeaveNum());
                addStaffDto.setRemainingCasualLeaves(staff.getRemainingCasualLeaves());
                addStaffDto.setRemainingVacationLeave(staff.getRemainingVacationLeave());
            }
            else{
                addStaffDto.setServiceStartedDate(null);
                addStaffDto.setWardNo(null);
                addStaffDto.setLeaveNum(null);
                addStaffDto.setRemainingCasualLeaves(0);
                addStaffDto.setRemainingVacationLeave(0);
            }
            return addStaffDto;
        }
        else {
            return null;
        }
    }

    public List<String> retrieveAllUserNics(){
        List<String> nics = userRepository.findAllNics();
        return nics;
    }


}



