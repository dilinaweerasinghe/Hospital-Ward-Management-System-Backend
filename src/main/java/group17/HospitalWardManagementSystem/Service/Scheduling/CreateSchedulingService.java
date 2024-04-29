package group17.HospitalWardManagementSystem.Service.Scheduling;

import group17.HospitalWardManagementSystem.Model.Domain.*;
import group17.HospitalWardManagementSystem.Model.DutyTime;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.CasualityDaysRepository;
import group17.HospitalWardManagementSystem.Repository.DutyRepository;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CreateSchedulingService {
    private final StaffRepository staffRepository;
    private final DutyRepository dutyRepository;
    private final UserRepository userRepository;

    private final CasualityDaysRepository casualityDaysRepository;

    @Autowired
    public CreateSchedulingService(StaffRepository staffRepository, DutyRepository dutyRepository, UserRepository userRepository, CasualityDaysRepository casualityDaysRepository) {
        this.staffRepository = staffRepository;
        this.dutyRepository = dutyRepository;
        this.userRepository = userRepository;
        this.casualityDaysRepository = casualityDaysRepository;
    }

    @Transactional
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
                addStaffMemberToShift(nurseNic, date, dutyTime);
            } else if (getNoOfExistingDuties(ward.getWardNo(), date, getDutyTime(dutyTime)) < (getMaxNurses(dutyTime, ward) + 2) && isACasualityDay(ward, date) ) {
                addStaffMemberToShift(nurseNic, date, dutyTime);
            }else{
                throw new IllegalArgumentException("Duty list already full in date: + " + date.toString() + " shift: " + dutyTime);
            }
        }else{
            throw new IllegalArgumentException("You cannot recognize as a sister!");
        }

    }

    private void addStaffMemberToShift(String nurseNic, LocalDate date, String dutyTime) {
        Duty duty = dutyRepository.findByDateAndDutyTime(date, getDutyTime(dutyTime));
        Set<Staff> staffSet;
        if(duty == null){
            staffSet = new HashSet<>();
            Staff nurse = staffRepository.findByNic(nurseNic);
            staffSet.add(nurse);
            Duty newDuty = Duty.builder().date(date).dutyTime(getDutyTime(dutyTime)).staff(staffSet).build();
            dutyRepository.save(newDuty);
        }else if (duty.getId() != null){
            Staff nurse = staffRepository.findByNic(nurseNic);

            staffSet = duty.getStaff();
            if (staffSet == null) {
                staffSet = new HashSet<>();
            }

            if (!staffSet.contains(nurse)) {
                staffSet.add(nurse);
                for(Staff staff : staffSet){
                    System.out.println(staff.getNic() + duty.getId());
                }
            } else {
                throw new IllegalArgumentException("Nurse is already assigned to this duty.");
            }
        }else{
            throw  new IllegalArgumentException("cannot find Details of the selected Nurse");
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
            List<CasualityDays> casualityDays = casualityDaysRepository.findCasualityDaysByMonday(true, ward);
            if(!casualityDays.isEmpty()){
                isAcasualityDay = true;
            }
        } else if (DateUtils.getDayOfWeekName(date).equalsIgnoreCase("tuesday")) {
            List<CasualityDays> casualityDays = casualityDaysRepository.findCasualityDaysByTuesday(true, ward);
            if(!casualityDays.isEmpty()){
                isAcasualityDay = true;
            }
        } else if (DateUtils.getDayOfWeekName(date).equalsIgnoreCase("wednesday")) {
            List<CasualityDays> casualityDays = casualityDaysRepository.findCasualityDaysByWednesday(true, ward);
            if(!casualityDays.isEmpty()){
                isAcasualityDay = true;
            }
        } else if (DateUtils.getDayOfWeekName(date).equalsIgnoreCase("thursday")) {
            List<CasualityDays> casualityDays = casualityDaysRepository.findCasualityDaysByThursday(true, ward);
            if(!casualityDays.isEmpty()){
                isAcasualityDay = true;
            }
        } else if (DateUtils.getDayOfWeekName(date).equalsIgnoreCase("friday")) {
            List<CasualityDays> casualityDays = casualityDaysRepository.findCasualityDaysByFriday(true, ward);
            if(!casualityDays.isEmpty()){
                isAcasualityDay = true;
            }
        } else if (DateUtils.getDayOfWeekName(date).equalsIgnoreCase("saturday")) {
            List<CasualityDays> casualityDays = casualityDaysRepository.findCasualityDaysBySaturday(true, ward);
            if(!casualityDays.isEmpty()){
                isAcasualityDay = true;
            }
        }

        return isAcasualityDay;
   }
}
