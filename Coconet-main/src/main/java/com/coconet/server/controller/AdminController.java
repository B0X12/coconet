package com.coconet.server.controller;

import com.coconet.server.entity.Users;
import com.coconet.server.repository.DepartmentRepository;
import com.coconet.server.repository.PositionRepository;
import com.coconet.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/coconet")
@RequiredArgsConstructor
public class AdminController {

    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final UserRepository userRepository;


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
    public List<String> retrieveUsername(@RequestParam("department") String department
                    ,@RequestParam("position") String position)
    {
        // 부서&직급 정보가 일치하는 유저를 반환
        List<Users> findUser = userRepository.findByDepartmentAndPosition(department, position);

        List<String> usernameList = new ArrayList<>();
        for (int i = 0; i < findUser.size(); i++)
        {
            usernameList.add(findUser.get(i).getName());
        }

        return usernameList;
    }
}
