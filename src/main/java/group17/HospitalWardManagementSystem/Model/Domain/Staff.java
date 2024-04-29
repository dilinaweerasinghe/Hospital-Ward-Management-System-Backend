package group17.HospitalWardManagementSystem.Model.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class  Staff {

    @Id
    private String nic;

    @Column(nullable = false)
    @Builder.Default
    private LocalDate serviceStartedDate = LocalDate.now();

    @Column(nullable = false)
    private String leaveNum;

    @OneToMany(mappedBy = "staff")
    private Set<RequestLeave> leaveRequests = new HashSet<>();

    @OneToMany(mappedBy = "staff")
    private Set<ApprovedLeaves> approvedLeaves = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "wardNo", referencedColumnName = "wardNo")
    private Ward wardNo;
    @Column(nullable = false)
    private int remainingCasualLeaves = 23;
    @Column(nullable = false)
    private int remainingVacationLeave = 23;
    @ManyToMany(mappedBy = "staff" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Duty> duties = new HashSet<>();
}
