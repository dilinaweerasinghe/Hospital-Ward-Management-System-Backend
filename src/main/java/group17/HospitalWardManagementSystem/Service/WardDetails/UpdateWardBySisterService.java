package group17.HospitalWardManagementSystem.Service.WardDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.WardDto.UpdateWardBySisterDto;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateWardBySisterService {

    @Autowired
    private WardRepository wardRepository;

    public String UpdateWardBySister(UpdateWardBySisterDto updateWardBySisterDto){

        //UpdateWardBySisterDto updateWardBySisterDto1=new UpdateWardBySisterDto();

        Ward targetWard=findWard(updateWardBySisterDto.getWardNo());

        targetWard.setNightShift(updateWardBySisterDto.getNightShift());
        targetWard.setEveningShift(updateWardBySisterDto.getEveningShift());
        targetWard.setMorningShift(updateWardBySisterDto.getMorningShift());

        wardRepository.save(targetWard);

        return "Successfully updated the ward details";
    }

    public Ward findWard(String wardNo){
        return wardRepository.findByWardNo(wardNo);
    }

}
