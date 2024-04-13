package group17.HospitalWardManagementSystem.ServiceInterfaces;

import group17.HospitalWardManagementSystem.Model.Dto.Matron.GetMatronDto;
import group17.HospitalWardManagementSystem.Model.Dto.Matron.MatronDto;

import java.sql.SQLException;
import java.util.List;

public interface IMatronService {
    Boolean AddMatron(MatronDto matronDto);
    List<GetMatronDto> getMatronDetailService();
    String deleteMatronService(String nic) throws SQLException;
}
