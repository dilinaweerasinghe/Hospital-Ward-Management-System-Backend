package group17.HospitalWardManagementSystem.Service.AdminServices;

import group17.HospitalWardManagementSystem.Model.Domain.CasualityDays;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.WardDto.CasualityDayDto;
import group17.HospitalWardManagementSystem.Repository.CasualityDaysRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasualtyService {
    private final CasualityDaysRepository casualityDaysRepository;
    private final WardRepository wardRepository;

    @Autowired
    public CasualtyService(CasualityDaysRepository casualityDaysRepository, WardRepository wardRepository) {
        this.casualityDaysRepository = casualityDaysRepository;
        this.wardRepository = wardRepository;
    }

    public CasualityDayDto getCasualityDayByWard(String wardNo){
        Ward ward = wardRepository.findById(wardNo).orElseThrow(() -> (new EntityNotFoundException("Cannot found ward with ward No: " + wardNo)));

        CasualityDays casualityDays = casualityDaysRepository.findByWardNo(ward);
        CasualityDayDto casualityDayDto = new CasualityDayDto();
        if(casualityDays != null){
            casualityDayDto.setMonday(casualityDays.isMonday());
            casualityDayDto.setTuesday(casualityDays.isTuesday());
            casualityDayDto.setWednesday(casualityDays.isWednesday());
            casualityDayDto.setThursday(casualityDays.isThursday());
            casualityDayDto.setFriday(casualityDays.isFriday());
            casualityDayDto.setSaturday(casualityDays.isSaturday());
        }

        return casualityDayDto;
    }
}
