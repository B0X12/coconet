package com.coconet.server.controller;


import com.coconet.server.define.Status;
import com.coconet.server.dto.NoticeDto;
import com.coconet.server.dto.TodoResultDto;
import com.coconet.server.dto.UserStatusLogdataDto;
import com.coconet.server.dto.UserStatusNotificationDto;
import com.coconet.server.entity.*;
import com.coconet.server.exception.UserNotFoundException;
import com.coconet.server.repository.*;
import com.coconet.server.service.BoardService;
import com.coconet.server.service.ReadFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.Collator;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/coconet")
@RequiredArgsConstructor
public class BoardJpaController {

    private final BoardService boardService;

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final BoardRepository boardRepository;

    // 로그 관련
    private final ReadFileService readFileService;

    private int i = 0;


    /**
     * 공지사항 조회
     */
    @GetMapping("/board/notice")//공지사항 전체 조회
    public List<NoticeDto> noticeAll() {
        List<NoticeDto> noticeDtoList = new ArrayList<>();

        int size = boardRepository.findAllByTitle().size();
        for (int i=0; i<size; i++) {
            NoticeDto noticeDto = new NoticeDto();
            noticeDto.setId(boardRepository.findAllById().get(i));
            noticeDto.setTitle(boardRepository.findAllByTitle().get(i));
            noticeDto.setDay(boardRepository.findAllByDay().get(i));
            noticeDtoList.add(i, noticeDto);
        }

        List<NoticeDto> noticeDtoListResult = new ArrayList<>();
        for (int i=0; i<16; i++) {
            NoticeDto noticeDto = new NoticeDto();
            noticeDto.setId(boardRepository.findAllById().get(i));
            noticeDto.setTitle(boardRepository.findAllByTitle().get(i));
            noticeDto.setDay(boardRepository.findAllByDay().get(i));
            noticeDtoListResult.add(i, noticeDto);
        }

        return noticeDtoListResult;
    }

    /**
     * 공지사항 연도별 조회
     */
    @GetMapping("/board/notice/year")
    public List<NoticeDto> noticeYear(@RequestParam("day") String day) {
        List<NoticeDto> noticeDtoList = new ArrayList<>();

        int size = boardRepository.findAllByTitleStartingWith(day).size();
        for (int i=0; i<size; i++) {
            NoticeDto noticeDto = new NoticeDto();
            noticeDto.setId(boardRepository.findAllByIdStartingWith(day).get(i));
            noticeDto.setTitle(boardRepository.findAllByTitleStartingWith(day).get(i));
            noticeDto.setDay(boardRepository.findAllByDayStartingWith(day).get(i));
            noticeDtoList.add(i, noticeDto);
        }

        return noticeDtoList;
    }

    /**
     * 공지사항 단건 조회
     */
    @GetMapping("/board/notice/one")
    public Optional<Notice> noticeOne(@RequestParam("title") String title,
                                      @RequestParam("id") int id,
                                      @RequestParam("day") String day) {
        return boardRepository.findById(id);
    }

    /**
     * 알림 목록 조회
     */
    @GetMapping("/board/notification")
    public List<UserStatusNotificationDto> getNoticeList() {
        try {
            return readFileService.getStatusNotification("user_log.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 결재 조회
     */
    @GetMapping("/board/approval")//approvalData
    public List<UserStatusLogdataDto> approvalAll() {
        try {
            return readFileService.getUserStatusWithAdminLog("user_log.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 차트 조회
     */
    @GetMapping("/board/chart")//chartData
    public List<ChartData> chartAll() {
        return boardService.findUserStatus();
    }

    /**
     * 실시간 기록 조회
     */
    @GetMapping("/board/log")//logData
    public List<UserStatusLogdataDto> logAll() {
        try {
            return readFileService.getUserStatusLog("user_log.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * todolist 조회
     */
    @GetMapping("/board/todo")//todoData
    public List<TodoResultDto> todoAll(@RequestParam("userNum") int userNum) {

        int size = todoRepository.findByUserNum(userNum).size();
        List<TodoData> listTodo = todoRepository.findByUserNum(userNum);
        List<TodoResultDto> todoResultDto = new ArrayList<>(size);
        if (!listTodo.isEmpty()) {
            for (int i=0; i<size; i++) {
                TodoResultDto resultDto = new TodoResultDto(listTodo.get(i).getTodo(), listTodo.get(i).getTodoCheck());
                todoResultDto.add(i, resultDto);
            }
            return todoResultDto;
        }
        else {
            throw new UserNotFoundException(String.format("조회할 리스트가 없습니다."));
        }
    }

    /**
     * todolist 추가
     */
    @PostMapping("/board/todo/add")
    public void todoAdd(@RequestBody TodoData todoData) {
        if(todoData.getTodo() != null) {

            Users findUser = userRepository.findByNum(todoData.getUserNum());

            TodoData todoAddData = new TodoData();
            todoAddData.setUserNum(findUser.getNum());
            todoAddData.setUserName(todoData.getUserName());
            todoAddData.setTodo(todoData.getTodo());
            todoAddData.setTodoCheck("false");

            TodoData todoResultData = todoRepository.save(todoAddData);
        }
        else{
            throw new UserNotFoundException(String.format("내용을 입력하세요"));
        }
    }

    /**
     * todolist 삭제
     */
    @DeleteMapping("/board/todo/delete")
    public void todoDelete(@RequestBody TodoData todoData) {
        todoRepository.deleteTodoNum(todoData.getUserNum(), todoData.getTodo());
    }

    /**
     * todolist 선택
     */
    @PostMapping("/board/todo/check")
    public void todoCheck(@RequestBody TodoData todoData) {

        TodoData setTodoData = todoRepository.findByUserNumTodo(todoData.getUserNum(), todoData.getTodo());

        if (setTodoData.getTodoCheck().equals("false")) {
            todoRepository.updateCheck("true", todoData.getUserNum(), todoData.getTodo());
        } else if (setTodoData.getTodoCheck().equals("true")) {
            todoRepository.updateCheck("false", todoData.getUserNum(), todoData.getTodo());
        }
    }

}