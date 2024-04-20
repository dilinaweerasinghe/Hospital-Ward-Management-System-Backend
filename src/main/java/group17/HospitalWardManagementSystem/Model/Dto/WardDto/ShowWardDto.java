package group17.HospitalWardManagementSystem.Model.Dto.WardDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowWardDto {

    private String wardNo;
    private String wardName;
    private int numberOfNurses;
    private String sisterName;
    private String matronName;

}
