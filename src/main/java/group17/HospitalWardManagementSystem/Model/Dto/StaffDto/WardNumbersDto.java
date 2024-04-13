package group17.HospitalWardManagementSystem.Model.Dto.StaffDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WardNumbersDto {
    private List<String> wardNumbers;
}
