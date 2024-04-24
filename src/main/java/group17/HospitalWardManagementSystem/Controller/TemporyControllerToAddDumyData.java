package group17.HospitalWardManagementSystem.Controller;

import group17.HospitalWardManagementSystem.Service.TemporyDumyDataAddingService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TemporyControllerToAddDumyData {
    private final TemporyDumyDataAddingService temporyDumyDataAddingService;

    @Autowired
    public TemporyControllerToAddDumyData(TemporyDumyDataAddingService temporyDumyDataAddingService) {
        this.temporyDumyDataAddingService = temporyDumyDataAddingService;
    }

    @PostConstruct
    public void initNurses(){
        temporyDumyDataAddingService.addNursesData();
        temporyDumyDataAddingService.addSisterData();
        temporyDumyDataAddingService.addMatronData();
        temporyDumyDataAddingService.addMatronsToMatronTable();
        temporyDumyDataAddingService.addWards();
        temporyDumyDataAddingService.addStaffDetails();
        temporyDumyDataAddingService.addSistersToTheStaffTable();
        temporyDumyDataAddingService.addRequestLeaves();
//        temporyDumyDataAddingService.addCasualityDays();
    }
}
