package group17.HospitalWardManagementSystem.Service.WardDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Matron;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.WardDto.ShowFullWardDto;
import group17.HospitalWardManagementSystem.Repository.MatronRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowFullWardService {
    @Autowired
    public WardRepository wardRepository;
    @Autowired
    public MatronRepository matronRepository;
    public ShowFullWardDto showFullWard(String wardNo){

        //map the domain to the dto
        ShowFullWardDto showFullWardDto=new ShowFullWardDto();

        Ward ward=findWard(wardNo);

        showFullWardDto.setWardNo(ward.getWardNo());
        showFullWardDto.setWardName(ward.getWardName());
        showFullWardDto.setMatron(findMatron(ward).getNic());
        showFullWardDto.setNumberOfNurses(ward.getNumberOfNurses());
        showFullWardDto.setMorningShift(ward.getMorningShift());
        showFullWardDto.setEveningShift(ward.getEveningShift());
        showFullWardDto.setNightShift(ward.getNightShift());

        return showFullWardDto;
    }

    public Ward findWard(String wardNo){
        return wardRepository.findByWardNo(wardNo);
    }

    public Matron findMatron(Ward ward){
        return matronRepository.findByNic(ward.getMatron().getNic());
    }
}
