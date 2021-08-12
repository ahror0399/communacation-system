package uz.developer.communication_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.SimCardForSearchDto;
import uz.developer.communication_system.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @PreAuthorize(value = "hasAnyRole('ROLL_DIRECTOR','ROLL_FILIAL_LEADER')")
    @GetMapping("/getAllByBranchIdAndCreatedAt_YearAndCreatedAt_Month")
    public ApiResponse getAllByBranchIdAndCreatedAt_YearAndCreatedAt_Month(
            @RequestParam Integer branchId,@RequestParam Integer year, @RequestParam Integer month){
        return dashboardService.findAllByBranchIdAndCreatedAt_YearAndCreatedAt_Month(branchId,year,month);
    }
    @PreAuthorize(value = "hasAnyRole('ROLL_DIRECTOR','ROLL_FILIAL_LEADER')")
    @GetMapping("/getAllByBranchIdAndCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_Day")
    public ApiResponse getAllByBranchIdAndCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_Day(
            @RequestParam Integer branchId,@RequestParam Integer year, @RequestParam Integer month,@RequestParam Integer day){
        return dashboardService.findAllByBranchIdAndCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_Day(branchId,year,month,day);
    }
    @PreAuthorize(value = "hasAnyRole('ROLL_DIRECTOR','ROLL_FILIAL_LEADER')")
    @GetMapping("/getAllByBranchIdAndCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_DayBetween")
    public ApiResponse getAllByBranchIdAndCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_Day(
    @RequestParam Integer branchId,  @RequestParam Integer year, @RequestParam Integer month,@RequestParam Integer day, @RequestParam Integer day2){
        return dashboardService.findAllByBranchIdAndCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_DayBetween(branchId,year,month,day,day2);
    }
    
    @PreAuthorize(value = "hasAnyRole('ROLL_DIRECTOR')")
    @GetMapping("/getAllByCreatedAt_YearAndCreatedAt_Month")
    public ApiResponse getAllByCreatedAt_YearAndCreatedAt_Month(
            @RequestParam Integer year, @RequestParam Integer month){
        return dashboardService.findAllByCreatedAt_YearAndCreatedAt_Month(year,month);
    }
    @PreAuthorize(value = "hasAnyRole('ROLL_DIRECTOR')")
    @GetMapping("/getAllByCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_Day")
    public ApiResponse getAllByCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_Day(
            @RequestParam Integer year, @RequestParam Integer month,@RequestParam Integer day){
        return dashboardService.findAllByCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_Day(year,month,day);
    }
    @PreAuthorize(value = "hasAnyRole('ROLL_DIRECTOR')")
    @GetMapping("/getAllByCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_DayBetween")
    public ApiResponse getAllByCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_DayBetween(
            @RequestParam Integer year, @RequestParam Integer month,@RequestParam Integer day,@RequestParam Integer day2){
        return dashboardService.findAllByCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_DayBetween(year,month,day,day2);
    }

}
