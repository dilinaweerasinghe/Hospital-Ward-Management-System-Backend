package group17.HospitalWardManagementSystem.Service.Scheduling;

import group17.HospitalWardManagementSystem.Model.Domain.Duty;
import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.DutyTime;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.DutyRepository;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class CreateSchedulingService {
    private final StaffRepository staffRepository;
    private final DutyRepository dutyRepository;
    private final UserRepository userRepository;

    @Autowired
    public CreateSchedulingService(StaffRepository staffRepository, DutyRepository dutyRepository, UserRepository userRepository) {
        this.staffRepository = staffRepository;
        this.dutyRepository = dutyRepository;
        this.userRepository = userRepository;
    }

    public void addNursesToTheDuties(String sisterNic, String nurseNic, LocalDate date, String dutyTime){
        Staff staff = staffRepository.findById(sisterNic).orElseThrow(() ->
                (new EntityNotFoundException("Cannot find your details with nic: " + sisterNic + ". Please contact your admin!")));
        User user = userRepository.findById(sisterNic).orElseThrow(() ->
                (new EntityNotFoundException("Cannot find your details with nic: " + sisterNic + ". Please contact your admin!")));
        Ward ward = staff.getWardNo();
        if(ward == null){
            throw new IllegalArgumentException("You have not assigned to the any ward!");
        }

        if(user.getPosition().equals(UserRole.Sister)){
            if(getNoOfExistingDuties(ward.getWardNo(), date, getDutyTime(dutyTime)) < getMaxNurses(dutyTime, ward)){
                Duty duty = dutyRepository.findByDateAndDutyTime(date, getDutyTime(dutyTime));
                if(duty.getStaff().isEmpty()){
                    Set<Staff> staffSet = new HashSet<>();
                    Staff nurse = staffRepository.findByNic(nurseNic);
                    if(nurse == null){
                        throw  new IllegalArgumentException("cannot find Details of the selected Nurse");
                    }
                    staffSet.add(nurse);
                    dutyRepository.addStaffToDuty(duty.getId(), staffSet);
                }else{
                    Set<Staff> staffSet = duty.getStaff();
                    Staff nurse = staffRepository.findByNic(nurseNic);
                    if(nurse == null){
                        throw  new IllegalArgumentException("cannot find Details of the selected Nurse");
                    }
                    staffSet.add(nurse);
                    dutyRepository.addStaffToDuty(duty.getId(), staffSet);
                }
            } else if (getNoOfExistingDuties(ward.getWardNo(), date, getDutyTime(dutyTime)) < (getMaxNurses(dutyTime, ward) + 2) ) {

            }
        }else{
            throw new IllegalArgumentException("You cannot recognize as a sister!");
        }

    }

   // private

   private int getNoOfExistingDuties(String wardNo, LocalDate date, DutyTime dutyTime){
        return dutyRepository.countDutiesByWardNoDateAndDutyTime(wardNo, date, dutyTime);
   }

   private DutyTime getDutyTime(String dutyTime){
        if(dutyTime.equalsIgnoreCase("morning")){
            return DutyTime.Morning;
        } else if (dutyTime.equalsIgnoreCase("evening")) {
            return DutyTime.Evening;
        } else if (dutyTime.equalsIgnoreCase("night")) {
            return DutyTime.Night;
        }else {
            throw new IllegalArgumentException("Duty shift is invalid");
        }
   }

   private int getMaxNurses(String dutyTime, Ward ward){
       if(dutyTime.equalsIgnoreCase("morning")){
           return ward.getMorningShift();
       } else if (dutyTime.equalsIgnoreCase("evening")) {
           return ward.getEveningShift();
       } else if (dutyTime.equalsIgnoreCase("night")) {
           return ward.getNightShift();
       }else {
           throw new IllegalArgumentException("Duty shift is invalid");
       }
   }

   private Boolean isACasualityDay(Ward ward, LocalDate date){
        boolean isAcasualityDay = false;
        if(DateUtils.getDayOfWeekName(date).equalsIgnoreCase("monday")){

        } else if (DateUtils.getDayOfWeekName(date).equalsIgnoreCase("tuesday")) {

        } else if (DateUtils.getDayOfWeekName(date).equalsIgnoreCase("wednesday")) {

        } else if (DateUtils.getDayOfWeekName(date).equalsIgnoreCase("thursday")) {

        } else if (DateUtils.getDayOfWeekName(date).equalsIgnoreCase("friday")) {

        } else if (DateUtils.getDayOfWeekName(date).equalsIgnoreCase("saturday")) {

        }

        return isAcasualityDay;
   }
}
