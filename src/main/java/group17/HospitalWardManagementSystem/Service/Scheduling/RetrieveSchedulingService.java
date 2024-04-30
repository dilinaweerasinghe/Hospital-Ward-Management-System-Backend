package group17.HospitalWardManagementSystem.Service.Scheduling;

import group17.HospitalWardManagementSystem.Model.Domain.*;
import group17.HospitalWardManagementSystem.Model.Dto.Scheduling.CandidateListDto;
import group17.HospitalWardManagementSystem.Model.Dto.Scheduling.ViewScheduleDto;
import group17.HospitalWardManagementSystem.Model.DutyTime;
import group17.HospitalWardManagementSystem.Model.LeaveStatus;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class RetrieveSchedulingService {

    private final ApprovedLeavesRepository approvedLeavesRepository;
    private final DutyRepository dutyRepository;
    private final WardRepository wardRepository;
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;

    @Autowired
    public RetrieveSchedulingService(ApprovedLeavesRepository approvedLeavesRepository, DutyRepository dutyRepository, WardRepository wardRepository, UserRepository userRepository, StaffRepository staffRepository) {
        this.approvedLeavesRepository = approvedLeavesRepository;
        this.dutyRepository = dutyRepository;
        this.wardRepository = wardRepository;
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
    }

    public List<CandidateListDto> getCandidateNurseList(String nic, String shift, String date){
        Staff sister = staffRepository.findById(nic).orElseThrow(() ->
                new EntityNotFoundException("You are not recognize as a sister with " + nic + " , Please contact admin!"));
        User sisterU = userRepository.findByNic(nic).orElseThrow(() ->
                new EntityNotFoundException("Cannot find user details with user nic: " + nic));
        if(sisterU.getPosition().equals(UserRole.Sister)){
            Ward ward = sister.getWardNo();

            List<Staff> newCandidateStaffMembers = new ArrayList<>();

            Set<Staff> staff = ward.getStaff();

            if(staff.isEmpty()){
                throw new IllegalArgumentException("Staff members are not assigned to the ward yet");
            }

            for(Staff selectedStaff : staff){

                if(isAvailable(selectedStaff, LocalDate.parse(date))){
                    if(checkAvailabilityByShiftRule(selectedStaff, LocalDate.parse(date), getDutyTime(shift))){
                       if(checkPositionCorrect(selectedStaff)){
                          if(!getExistenceOfPreviousDuty(selectedStaff, LocalDate.parse(date), getDutyTime(shift))){
                              newCandidateStaffMembers.add(selectedStaff);
                          }
                       }
                    }
                }
            }


            return mapToCandidateListDto(newCandidateStaffMembers, LocalDate.parse(date));
        }else {
            throw new IllegalArgumentException("You are not recognize as a sister, Please contact admin!");
        }

    }

    public List<ViewScheduleDto> getDutyDetailsForDate(String sisterNic, String date){
        Staff staff = staffRepository.findById(sisterNic).orElseThrow(()-> (new EntityNotFoundException("Cannot found your details in System")));
        List<String> morningShiftNames = dutyRepository.findDutyStaffName(staff.getWardNo(), getDutyTime("Morning"), LocalDate.parse(date));
        List<String> eveningShiftNames = dutyRepository.findDutyStaffName(staff.getWardNo(), getDutyTime("Evening"), LocalDate.parse(date));
        List<String> nightShiftNames = dutyRepository.findDutyStaffName(staff.getWardNo(), getDutyTime("Night"), LocalDate.parse(date));

        if(morningShiftNames.isEmpty() && eveningShiftNames.isEmpty() && nightShiftNames.isEmpty()){
            throw new IllegalArgumentException("Scheduling is not created yet");

        }
        List<ViewScheduleDto> viewScheduleDtos = new ArrayList<>();

        for (int i = 0; i <getLargestSize(morningShiftNames,eveningShiftNames,nightShiftNames); i++){

            String morning, evening, night;
            morning = morningShiftNames.size() > i ? morningShiftNames.get(i) : " ";
            evening = eveningShiftNames.size() > i ? eveningShiftNames.get(i) : " ";
            night = nightShiftNames.size() > i ? nightShiftNames.get(i) : " ";

            ViewScheduleDto viewScheduleDto = new ViewScheduleDto(morning,evening, night);
            viewScheduleDtos.add(viewScheduleDto);
        }

        return viewScheduleDtos;


    }



    public List<ViewScheduleDto> getDutyDetailsForDateForMatron(String wardNo, String date){
        //Staff staff = staffRepository.findById(sisterNic).orElseThrow(()-> (new EntityNotFoundException("Cannot found your details in System")));
        Ward wardGet = wardRepository.findById(wardNo).orElseThrow(() -> new EntityNotFoundException("Cannot find ward details with ward No: " + wardNo));
        List<String> morningShiftNames = dutyRepository.findDutyStaffName(wardGet, getDutyTime("Morning"), LocalDate.parse(date));
        List<String> eveningShiftNames = dutyRepository.findDutyStaffName(wardGet, getDutyTime("Evening"), LocalDate.parse(date));
        List<String> nightShiftNames = dutyRepository.findDutyStaffName(wardGet, getDutyTime("Night"), LocalDate.parse(date));

        if(morningShiftNames.isEmpty() && eveningShiftNames.isEmpty() && nightShiftNames.isEmpty()){
            throw new IllegalArgumentException("Scheduling is not created yet");

        }
        List<ViewScheduleDto> viewScheduleDtos = new ArrayList<>();

        for (int i = 0; i <getLargestSize(morningShiftNames,eveningShiftNames,nightShiftNames); i++){

            String morning, evening, night;
            morning = morningShiftNames.size() > i ? morningShiftNames.get(i) : " ";
            evening = eveningShiftNames.size() > i ? eveningShiftNames.get(i) : " ";
            night = nightShiftNames.size() > i ? nightShiftNames.get(i) : " ";

            ViewScheduleDto viewScheduleDto = new ViewScheduleDto(morning,evening, night);
            viewScheduleDtos.add(viewScheduleDto);
        }

        return viewScheduleDtos;


    }

    private Boolean isAvailable(Staff staff, LocalDate date){

        List<ApprovedLeaves> approvedLeaves = approvedLeavesRepository.findLeavesByStaffAndDate(staff,date, LeaveStatus.Approved);
        return approvedLeaves.isEmpty();
    }

    private boolean getExistenceOfPreviousDuty(Staff staff, LocalDate date, DutyTime dutyTime){
        List<Duty> duties = dutyRepository.findDutiesByStaffAndDate(staff.getNic(), date, dutyTime);
        return !duties.isEmpty();
    }


    public boolean checkAvailabilityByShiftRule(Staff staff, LocalDate date, DutyTime dutyTime){
        boolean availability = true;
        switch (dutyTime){
            case DutyTime.Morning -> {
                if(getExistenceOfPreviousDuty(staff,date.minusDays(1), DutyTime.Night) &&
                        (getExistenceOfPreviousDuty(staff, date.minusDays(1), DutyTime.Evening) ||
                        getExistenceOfPreviousDuty(staff, date, DutyTime.Evening)) || (getExistenceOfPreviousDuty(staff, date, DutyTime.Evening) && getExistenceOfPreviousDuty(staff, date, DutyTime.Night))){

                    availability = false;
                }
            }
            case DutyTime.Evening -> {
                if(getExistenceOfPreviousDuty(staff,date, DutyTime.Morning) &&
                        (getExistenceOfPreviousDuty(staff, date.minusDays(1), DutyTime.Night) ||
                                getExistenceOfPreviousDuty(staff, date, DutyTime.Night)) || (getExistenceOfPreviousDuty(staff, date, DutyTime.Night) && getExistenceOfPreviousDuty(staff, date.plusDays(1), DutyTime.Morning))){

                    availability = false;
                }
            }
            case DutyTime.Night -> {
                if(getExistenceOfPreviousDuty(staff,date, DutyTime.Evening) &&
                        (getExistenceOfPreviousDuty(staff, date, DutyTime.Morning) ||
                                getExistenceOfPreviousDuty(staff, date.plusDays(1), DutyTime.Morning)) || (getExistenceOfPreviousDuty(staff, date.plusDays(1), DutyTime.Morning) && getExistenceOfPreviousDuty(staff, date.plusDays(1), DutyTime.Evening))){

                    availability = false;
                }
            }
        }

        return availability;
    }

    private boolean checkPositionCorrect(Staff staff){
        Optional<User> user = userRepository.findById(staff.getNic());
        return user.filter(value -> value.getPosition() != UserRole.Sister).isPresent();

    }

    private List<CandidateListDto> mapToCandidateListDto(List<Staff> staff, LocalDate dutuDate){
        List<CandidateListDto> candidateListDtos = new ArrayList<>();
        for(Staff staff1: staff){
            Optional<User> user = userRepository.findById(staff1.getNic());
            String fullName = user.isPresent() ? user.get().getFullName() : "Full name unknown";
            LocalDate date = user.map(User::getCareerStatedDate).orElse(null);
            List <Duty> duties = dutyRepository.findDutiesByStaffAndWeek(staff1.getNic(), DateUtils.getStartOfWeekForDate(dutuDate), dutuDate);
            int totalWorkHours = duties.isEmpty() ? 0 : workingHours(duties);
            candidateListDtos.add(CandidateListDto.builder().nic(staff1.getNic()).fullName(fullName).serviceStartedDate(date).workingHours(totalWorkHours).build());
        }
        return candidateListDtos;
    }

    private int workingHours(List<Duty> duties){
        int totalHourse = 0;
        for (Duty duty: duties){
            if(duty.getDutyTime().equals(DutyTime.Night)){
                totalHourse += 12;
            }else {
                totalHourse += 6;
            }
        }

        return totalHourse;
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

    public int getLargestSize(List<?> list1, List<?> list2, List<?> list3) {
        int size1 = list1 == null ? 0 : list1.size();
        int size2 = list2 == null ? 0 : list2.size();
        int size3 = list3 == null ? 0 : list3.size();

        return Math.max(Math.max(size1, size2), size3);
    }
}



