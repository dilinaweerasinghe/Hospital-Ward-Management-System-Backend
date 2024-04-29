package group17.HospitalWardManagementSystem.Model.Dto.WardDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowWardMore {
    private String wardName;
    private String matronName;
    private String matronNic;
    private int numberOfNurses;
    private int morningShiftNurses;
    private int eveningShiftNurses;
    private int nightShiftNurses;
}
