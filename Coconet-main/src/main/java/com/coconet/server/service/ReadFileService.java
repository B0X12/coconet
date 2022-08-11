package com.coconet.server.service;

import com.coconet.server.define.LogTag;
import com.coconet.server.define.Status;
import com.coconet.server.dto.AdminLogdataDto;
import com.coconet.server.dto.UserLogdataDto;
import com.coconet.server.dto.UserStatusNotificationDto;
import com.coconet.server.dto.UserStatusLogdataDto;
import com.coconet.server.exception.CustomException;
import com.google.gson.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadFileService
{
    private final String filePath = "./logs/";
    private final LogTag logTag;
    private final Status status;

    public List<AdminLogdataDto> findAdminLog(JsonArray jsonArray)
    {
        String strArr = "";
        String ip = "";
        String tag = "";
        String title = "";
        String email = "";
        String date = "";

        int index = 0;

        List<AdminLogdataDto> logDataList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) // jsonArray의 size는 {}단위로 구분
        {
            strArr = "";
            strArr += jsonArray.get(i).getAsString().replace("\"", ""); // 양옆 \" 제거

            // {ip:192.168.57.1, tag:Login, title:로그인 성공, email:admin, date:2022. 08. 05. 21:08:29}
            index = strArr.indexOf("ip:") + 3;              ip = strArr.substring(index, strArr.indexOf(", tag:"));
            index = strArr.indexOf("tag:") + 4;             tag = strArr.substring(index, strArr.indexOf(", title:"));
            index = strArr.indexOf("title:") + 6;           title = strArr.substring(index, strArr.indexOf(", email:"));
            index = strArr.indexOf("email:") + 6;           email = strArr.substring(index, strArr.indexOf(", date:"));
            index = strArr.indexOf("date:") + 5;            date = strArr.substring(index, strArr.length()-1);

            AdminLogdataDto logData = new AdminLogdataDto(ip, tag, title, email, date);
            logDataList.add(i, logData);
        }

        return logDataList;
    }

    public List<UserLogdataDto> findUserLog(JsonArray jsonArray, String findUserEmail)
    {
        String strArr = "";
        String tag = "";
        String title = "";
        String name = "";
        String email = "";
        String date = "";

        int index = 0;

        List<UserLogdataDto> logDataList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) // jsonArray의 size는 {}단위로 구분
        {
            // 루프를 돌면서 jsonArray 안의 object(중괄호)를 빼내어 값을 추출
            if (!(jsonArray.get(i).getAsString().contains("email:"+findUserEmail))) // 내가 원하는 사용자의 로그가 아니면
            {
                continue;
            }
            else
            {
                strArr = "";
                strArr += jsonArray.get(i).getAsString().replace("\"", ""); // 양옆 \" 제거

                // {tag:Login, title:로그인 성공, name:정재훈, email:jjh@naver.com, date:2022. 08. 05. 18:41:38}
                index = strArr.indexOf("tag:") + 4;             tag = strArr.substring(index, strArr.indexOf(", title:"));
                index = strArr.indexOf("title:") + 6;           title = strArr.substring(index, strArr.indexOf(", name:"));
                index = strArr.indexOf("name:") + 5;            name = strArr.substring(index, strArr.indexOf(", email:"));
                index = strArr.indexOf("email:") + 6;           email = strArr.substring(index, strArr.indexOf(", department:"));
                index = strArr.indexOf("date:") + 5;            date = strArr.substring(index, strArr.length() - 1);
                // 나중에 Gson 라이브러리 써서 수정합시다...

                UserLogdataDto logData = new UserLogdataDto(tag, title, name, email, date);
                logDataList.add(logData);
            }
        }

        return logDataList;
    }

    // 결재
    public List<UserStatusLogdataDto> findUserStatusWithAdminLog(JsonArray jsonArray)
    {
        String strArr = "";
        String userStatus = "";
        String name = "";
        String department = "";
        String date = "";

        int cnt = 0;
        int index = 0;

        List<UserStatusLogdataDto> logDataList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) // jsonArray의 size는 {}단위로 구분
        {
            if (cnt > 10) // 10개의 데이터만 반환
            {
                return logDataList;
            }
            else if (!(jsonArray.get(i).getAsString().contains("tag:" + logTag.TAG_USER_STATUS_WITH_ADMIN))) // status 관련 로그면
            {
                continue;
            }
            else
            {
                strArr = "";
                strArr += jsonArray.get(i).getAsString().replace("\"", ""); // 양옆 \" 제거

                // {tag:Login, title:로그인 성공, name:정재훈, email:jjh@naver.com, department:개발팀 date:2022. 08. 05. 18:41:38}
                index = strArr.indexOf("title:") + 6;           userStatus = strArr.substring(index, strArr.indexOf(", name:"));
                index = strArr.indexOf("name:") + 5;            name = strArr.substring(index, strArr.indexOf(", email:"));
                index = strArr.indexOf("department:") + 11;     department = strArr.substring(index, strArr.indexOf(", date:"));
                index = strArr.indexOf("date:") + 5;            date = strArr.substring(index, strArr.length() - 1);
                date = date.substring(0, date.length() - 10); // 날짜 정보만 반환되도록

                UserStatusLogdataDto logData = new UserStatusLogdataDto(
                        userStatus
                        , name
                        , department
                        , date
                        , status.getColor(userStatus) // 상태에 따른 컬러값 반환
                );
                logDataList.add(logData);

                cnt++;
            }
        }

        return logDataList;
    }

    // 실시간 기록
    public List<UserStatusLogdataDto> findUserStatusLog(JsonArray jsonArray)
    {
        String strArr = "";
        String userStatus = "";
        String name = "";
        String department = "";
        String date = "";

        int cnt = 0;
        int index = 0;

        List<UserStatusLogdataDto> logDataList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) // jsonArray의 size는 {}단위로 구분
        {
            if (cnt > 10) // 10개의 데이터만 반환
            {
                return logDataList;
            }
            else if (!(jsonArray.get(i).getAsString().contains("tag:" + logTag.TAG_USER_STATUS))) // status 관련 로그면
            {
                continue;
            }
            else
            {
                if (!(jsonArray.get(i).getAsString().contains("name"))) // name 항목이 없는 경우 == 출근 퍼센트를 나타낸 로그일 경우
                {
                    continue;
                }
                else
                {
                    strArr = "";
                    strArr += jsonArray.get(i).getAsString().replace("\"", ""); // 양옆 \" 제거

                    // {tag:Login, title:로그인 성공, name:정재훈, email:jjh@naver.com, department:개발팀 date:2022. 08. 05. 18:41:38}
                    index = strArr.indexOf("title:") + 6;           userStatus = strArr.substring(index, strArr.indexOf(", name:"));
                    index = strArr.indexOf("name:") + 5;            name = strArr.substring(index, strArr.indexOf(", email:"));
                    index = strArr.indexOf("department:") + 11;     department = strArr.substring(index, strArr.indexOf(", date:"));
                    index = strArr.indexOf("date:") + 5;            date = strArr.substring(index, strArr.length() - 1);
                    date = date.substring(14, date.length() - 3); // 시간 정보만 반환되도록

                    UserStatusLogdataDto logData = new UserStatusLogdataDto(
                            userStatus
                            , name
                            , department
                            , date
                            , status.getColor(userStatus) // 상태에 따른 컬러값 반환
                    );
                    logDataList.add(logData);

                    cnt++;
                }
            }
        }

        return logDataList;
    }

    // 메인화면 알림
    public List<UserStatusNotificationDto> findStatusNotification(JsonArray jsonArray)
    {
        String strArr = "";
        String title = "";
        String date = "";

        int cnt = 0;
        int index = 0;

        List<UserStatusNotificationDto> logDataList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) // jsonArray의 size는 {}단위로 구분
        {
            if (cnt > 10) // 10개의 데이터만 반환
            {
                return logDataList;
            }
            else if (!(jsonArray.get(i).getAsString().contains("tag:"+ logTag.TAG_USER_STATUS))) // 출퇴근 관련 로그만 반환
            {
                continue;
            }
            else
            {
                strArr = "";
                strArr += jsonArray.get(i).getAsString().replace("\"", ""); // 양옆 \" 제거

                if (!(jsonArray.get(i).getAsString().contains("name"))) // name 항목이 없는 경우 == 출근 퍼센트를 나타낸 로그일 경우
                {
                    // {tag:Status, title:직원 25.0% 출근 완료, date:2022. 08. 09. 15:58:04}
                    title = "";
                    index = strArr.indexOf("title:") + 6;           title = strArr.substring(index, strArr.indexOf(", date:"));
                    index = strArr.indexOf("date:") + 5;            date = strArr.substring(index, strArr.length() - 1);
                }
                else
                {
                    // {tag:Login, title:로그인 성공, name:정재훈, email:jjh@naver.com, department:개발팀 date:2022. 08. 05. 18:41:38}
                    title = "";
                    index = strArr.indexOf("department:") + 11;     title += strArr.substring(index, strArr.indexOf(", date:")) + " ";
                    index = strArr.indexOf("name:") + 5;            title += strArr.substring(index, strArr.indexOf(", email:")) + " ";
                    index = strArr.indexOf("title:") + 6;           title += strArr.substring(index, strArr.indexOf(", name:"));
                    index = strArr.indexOf("date:") + 5;            date = strArr.substring(index, strArr.length()-1);
                    date = date.substring(14, date.length() - 3); // 시간 정보만 반환되도록
                }

                UserStatusNotificationDto logData = new UserStatusNotificationDto(title, date);
                logDataList.add(logData);

                cnt++;
            }
        }

        return logDataList;
    }


    /**
     * @관리자페이지
     * 관리자 로그 조회
     */
    public List<AdminLogdataDto> getAdminLog(String fileName) throws IOException
    {
        List<String> readLines = Files.readAllLines(Paths.get(filePath + fileName));

        // List<String> -> json으로 변환
        String json = new Gson().toJson(readLines);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(json);

        return findAdminLog(jsonArray);
    }

    /**
     * @관리자페이지
     * 사용자 로그 조회 (email로 구분)
     */
    public List<UserLogdataDto> getUserLog(String fileName, String findUserEmail) throws IOException
    {
        List<String> readLines = Files.readAllLines(Paths.get(filePath + fileName));

        // List<String> -> json으로 변환
        String json = new Gson().toJson(readLines);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(json);

        return findUserLog(jsonArray, findUserEmail);
    }

    /**
     * @근무현황
     * 결재 현황 - 외근, 출장, 휴가 관련
     */
    public List<UserStatusLogdataDto> getUserStatusWithAdminLog(String fileName) throws IOException
    {
        List<String> readLines = Files.readAllLines(Paths.get(filePath + fileName));

        // List<String> -> json으로 변환
        String json = new Gson().toJson(readLines);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(json);

        return findUserStatusWithAdminLog(jsonArray);
    }

    /**
     * @근무현황
     * 실시간 기록 - 출근, 휴식, 퇴근 관련
     */
    public List<UserStatusLogdataDto> getUserStatusLog(String fileName) throws IOException
    {
        List<String> readLines = Files.readAllLines(Paths.get(filePath + fileName));

        // List<String> -> json으로 변환
        String json = new Gson().toJson(readLines);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(json);

        return findUserStatusLog(jsonArray);
    }

    /**
     * @메인화면
     * 알림 조회 (tag가 Status, @Status인 것)
     */
    public List<UserStatusNotificationDto> getStatusNotification(String fileName) throws IOException
    {
        List<String> readLines = Files.readAllLines(Paths.get(filePath + fileName));

        // List<String> -> json으로 변환
        String json = new Gson().toJson(readLines);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(json);

        return findStatusNotification(jsonArray);
    }

}