package com.coconet.server.controller;

import com.coconet.server.dto.AuthDto;
import com.coconet.server.entity.AdminWorkTime;
import com.coconet.server.entity.Users;
import com.coconet.server.repository.AdminWorkTimeRepository;
import com.coconet.server.repository.DepartmentRepository;
import com.coconet.server.repository.PositionRepository;
import com.coconet.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/coconet")
@RequiredArgsConstructor
public class AdminController {

    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final UserRepository userRepository;
    private final AdminWorkTimeRepository adminWorkTimeRepository;

    @Value("${coconet.worktime.crontext}") // application.xml에 정의
    private String cronTextSetting;


    /**
     * 최초 접근시
     * -> 전체 부서 정보 반환
     */
    @GetMapping("/user/department")
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 조회 가능
    public List<String> retrieveDepartment()
    {
        return departmentRepository.findDepartment();
    }


    /**
     * 부서 선택시
     * -> 해당 부서의 직급 정보 반환
     */
    @GetMapping("/user/position")
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 조회 가능
    public List<String> retrievePosition(@RequestParam("department") String department)
    {
        int departmentId = departmentRepository.findByDepartment(department);
        List<String> positions = positionRepository.findByDepartmentId(departmentId);

        return positions;
    }


    /**
     * 부서&직급 선택시
     * -> 부서&직급 정보가 일치하는 사용자 반환
     */
    @GetMapping("/user/username")
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 조회 가능
    public List<AuthDto> retrieveUsername(@RequestParam("department") String department
                    , @RequestParam("position") String position)
    {
        // 부서&직급 정보가 일치하는 유저를 반환
        List<Users> findUser = userRepository.findByDepartmentAndPosition(department, position);

        List<AuthDto> usernameList = new ArrayList<>();
        for (int i = 0; i < findUser.size(); i++)
        {
            AuthDto authDto = new AuthDto(findUser.get(i).getName(), findUser.get(i).getNum());
            usernameList.add(i, authDto);
        }

        return usernameList;
    }

    /**
     * 관리자페이지
     * 출퇴근시간 조회
     */
    @GetMapping("/admin/worktime")
    public List<AdminWorkTime> worktimeAll()
    {
        return adminWorkTimeRepository.findAll();
    }

    /**
     * 관리자페이지
     * 출퇴근시간 수정
     */
    @PostMapping("/admin/worktime/edit")
    public String worktimeEdit(@Valid @RequestBody AdminWorkTime adminWorkTime)
    {
        adminWorkTimeRepository.updateValueTitle(adminWorkTime.getValue(), adminWorkTime.getTitle());
        return spliceText();
    }

    public String spliceText()
    {
        String workDay = adminWorkTimeRepository.findValueByTitle("근무일");
        String workTime = adminWorkTimeRepository.findValueByTitle("출근시간");

        String[] workDaySplit = workDay.split("-");
        String workDayCast = ""; // 월-금 -> MON-FRI
        for (int i = 0; i < 2; i++)
        {
            if (i == 1)
            {
                workDayCast += "-"; // 월-()
            }
            switch (workDaySplit[i].toString())
            {
                case "일":
                    workDayCast += "SUN";
                    break;

                case "월":
                    workDayCast += "MON";
                    break;

                case "화":
                    workDayCast += "TUE";
                    break;

                case "수":
                    workDayCast += "WED";
                    break;

                case "목":
                    workDayCast += "THU";
                    break;

                case "금":
                    workDayCast += "FRI";
                    break;

                case "토":
                    workDayCast += "SAT";
                    break;
            }
        }

        String[] workTimeSplit = workTime.split(":");

        String cronText = "";
        cronText += "00 "; // sec
        cronText += workTimeSplit[1] + " "; // min
        cronText += workTimeSplit[0] + " "; // hour
        cronText += "* "; // day
        cronText += "* "; // month
        cronText += workDayCast; // day of week(요일, ex. MON-FRI)

        cronTextSetting = cronText;

        return cronTextSetting;
    }

}
