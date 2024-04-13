package group17.HospitalWardManagementSystem.Model.Dto.WardDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllWardNamesDto {
    private Set<String> wardName;
}
