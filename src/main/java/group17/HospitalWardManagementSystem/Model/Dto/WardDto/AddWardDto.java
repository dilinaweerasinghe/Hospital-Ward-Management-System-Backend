package group17.HospitalWardManagementSystem.Model.Dto.WardDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddWardDto {

    private String wardNo;

    private String wardName;

    private String matron;

    private int numberOfNurses;

    private int morningShift;

    private int eveningShift;

    private int nightShift;
}
