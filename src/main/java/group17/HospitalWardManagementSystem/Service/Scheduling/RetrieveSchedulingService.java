package group17.HospitalWardManagementSystem.Service.Scheduling;

import group17.HospitalWardManagementSystem.Model.Domain.*;
import group17.HospitalWardManagementSystem.Model.Dto.Scheduling.CandidateListDto;
import group17.HospitalWardManagementSystem.Model.DutyTime;
import group17.HospitalWardManagementSystem.Model.LeaveStatus;
import group17.HospitalWardManagementSystem.Repository.ApprovedLeavesRepository;
import group17.HospitalWardManagementSystem.Repository.DutyRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RetrieveSchedulingService {

    private final ApprovedLeavesRepository approvedLeavesRepository;
    private final DutyRepository dutyRepository;
    private final WardRepository wardRepository;
    private final UserRepository userRepository;

    @Autowired
    public RetrieveSchedulingService(ApprovedLeavesRepository approvedLeavesRepository, DutyRepository dutyRepository, WardRepository wardRepository, UserRepository userRepository) {
        this.approvedLeavesRepository = approvedLeavesRepository;
        this.dutyRepository = dutyRepository;
        this.wardRepository = wardRepository;
        this.userRepository = userRepository;
    }

    public List<CandidateListDto> getCandidateNurseList(String wardNo, String shift, String date){
        Ward ward = wardRepository.findById(wardNo).orElseThrow(() ->
                new EntityNotFoundException("Cannot find ward with ward No: " + wardNo));

        List<Staff> newCandidateStaffMembers = new ArrayList<>();

        Set<Staff> staff = ward.getStaff();

        if(staff.isEmpty()){
            throw new IllegalArgumentException("Staff members are not assigned yet");
        }

        for(Staff selectedStaff : staff){

            if(isAvailable(selectedStaff, LocalDate.parse(date))){
                 if(shift.equals("Morning")){
                    if(checkAvailabilityByPreviousShift(selectedStaff,LocalDate.parse(date).minusDays(1), DutyTime.Evening)
                            && checkAvailabilityByPreviousShift(selectedStaff,LocalDate.parse(date).minusDays(1), DutyTime.Night)){

                        newCandidateStaffMembers.add(selectedStaff);
                    }
                } else if (shift.equals("Evening")) {
                     if(checkAvailabilityByPreviousShift(selectedStaff,LocalDate.parse(date), DutyTime.Morning)
                             && checkAvailabilityByPreviousShift(selectedStaff,LocalDate.parse(date).minusDays(1), DutyTime.Night)){

                         newCandidateStaffMembers.add(selectedStaff);
                     }
                 } else if (shift.equals("Night")) {
                     if(checkAvailabilityByPreviousShift(selectedStaff,LocalDate.parse(date), DutyTime.Morning)
                             && checkAvailabilityByPreviousShift(selectedStaff,LocalDate.parse(date), DutyTime.Evening)){

                         newCandidateStaffMembers.add(selectedStaff);
                     }
                 }
            }
        }


        return null;
    }

    public Boolean isAvailable(Staff staff, LocalDate date){
        List<ApprovedLeaves> approvedLeaves = approvedLeavesRepository.findLeavesByStaffAndDate(staff,date, LeaveStatus.Approved);
        return approvedLeaves.isEmpty();
    }

    public Boolean checkAvailabilityByPreviousShift(Staff staff, LocalDate date, DutyTime dutyTime){
        List<Duty> duties = dutyRepository.findDutiesByStaffAndDate(staff.getNic(), date, dutyTime);
        return duties.isEmpty();
    }

//    public List<CandidateListDto> mapToCandidateListDto(List<Staff> staff){
//        List<CandidateListDto> candidateListDtos = new ArrayList<>();
//        for(Staff staff1: staff){
//            Optional<User> user = userRepository.findById(staff1.getNic());
//            String fullName = user.isPresent() ? user.get().getFullName() : "Full name unknown";
//            LocalDate serviceStartedDate =
//            candidateListDtos.add(CandidateListDto.builder().nic(staff1.getNic()).fullName(fullName).serviceStartedDate(user.).build());
//        }
//    }
}


