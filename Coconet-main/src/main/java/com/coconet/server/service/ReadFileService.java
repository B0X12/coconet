package com.coconet.server.service;

import com.coconet.server.define.LogTag;
import com.coconet.server.define.Status;
import com.coconet.server.dto.AdminLogdataDto;
import com.coconet.server.dto.UserLogdataDto;
import com.coconet.server.dto.UserStatusLogdataDto;
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

        List<AdminLogdataDto> logDataList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) // jsonArray의 size는 {}단위로 구분
        {
            strArr = "";
            strArr += jsonArray.get(i).getAsString().replace("\"", ""); // 양옆 \" 제거

            // {ip:192.168.57.1, tag:Login, title:로그인 성공, email:admin, date:2022. 08. 05. 21:08:29}
            String ip = strArr.substring(strArr.indexOf("ip:")+3, strArr.indexOf(", tag:"));
            String tag = strArr.substring(strArr.indexOf("tag:")+4, strArr.indexOf(", title:"));
            String title = strArr.substring(strArr.indexOf("title:")+6, strArr.indexOf(", email:"));
            String email = strArr.substring(strArr.indexOf("email:")+6, strArr.indexOf(", date:"));
            String date = strArr.substring(strArr.indexOf("date:")+5, strArr.length()-1);
            // 나중에 Gson 라이브러리 써서 수정합시다...

            AdminLogdataDto logData = new AdminLogdataDto(ip, tag, title, email, date);
            logDataList.add(i, logData);
        }

        return logDataList;
    }

    public List<UserLogdataDto> findUserLog(JsonArray jsonArray, String findUserEmail)
    {
        String strArr = "";

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
                String tag = strArr.substring(strArr.indexOf("tag:")+4, strArr.indexOf(", title:"));
                String title = strArr.substring(strArr.indexOf("title:")+6, strArr.indexOf(", name:"));
                String name = strArr.substring(strArr.indexOf("name:")+5, strArr.indexOf(", email:"));
                String email = strArr.substring(strArr.indexOf("email:")+6, strArr.indexOf(", date:"));
                String date = strArr.substring(strArr.indexOf("date:")+5, strArr.length()-1);
                // 나중에 Gson 라이브러리 써서 수정합시다...

                UserLogdataDto logData = new UserLogdataDto(tag, title, name, email, date);
                logDataList.add(logData);
            }
        }

        return logDataList;
    }

    public List<UserStatusLogdataDto> findUserStatusWithAdminLog(JsonArray jsonArray)
    {
        String strArr = "";
        String userStatus = "";
        String name = "";
        String department = "";
        String date = "";

        List<UserStatusLogdataDto> logDataList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) // jsonArray의 size는 {}단위로 구분
        {
            // 루프를 돌면서 jsonArray 안의 object(중괄호)를 빼내어 값을 추출
            if (jsonArray.get(i).getAsString().contains("tag:" + logTag.TAG_USER_STATUS_WITH_ADMIN)) // status 관련 로그면
            {
                strArr = "";
                strArr += jsonArray.get(i).getAsString().replace("\"", ""); // 양옆 \" 제거

                // {tag:Login, title:로그인 성공, name:정재훈, email:jjh@naver.com, department:개발팀 date:2022. 08. 05. 18:41:38}
                userStatus = strArr.substring(strArr.indexOf("title:")+6, strArr.indexOf(", name:"));
                name = strArr.substring(strArr.indexOf("name:")+5, strArr.indexOf(", email:"));
                department = strArr.substring(strArr.indexOf("department:")+11, strArr.indexOf(", date:"));
                date = strArr.substring(strArr.indexOf("date:")+5, strArr.length()-1);

                UserStatusLogdataDto logData = new UserStatusLogdataDto(
                        userStatus
                        , name
                        , department
                        , date
                        , status.getColor(userStatus) // 상태에 따른 컬러값 반환
                );
                logDataList.add(logData);
            }
            else
            {
                continue;
            }
        }

        return logDataList;
    }

    public List<UserStatusLogdataDto> findUserStatusLog(JsonArray jsonArray)
    {
        String strArr = "";
        String userStatus = "";
        String name = "";
        String department = "";
        String date = "";

        List<UserStatusLogdataDto> logDataList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) // jsonArray의 size는 {}단위로 구분
        {
            if (i > 10) // 10개의 데이터만 반환
            {
                return logDataList;
            }
            else if (!(jsonArray.get(i).getAsString().contains("tag:" + logTag.TAG_USER_STATUS))) // status 관련 로그가 아니면
            {
                continue;
            }
            else
            {
                strArr = "";
                strArr += jsonArray.get(i).getAsString().replace("\"", ""); // 양옆 \" 제거

                if (!(jsonArray.get(i).getAsString().contains("name"))) // name이 없는 경우 == 출근 퍼센트를 나타낸 로그일 경우
                {
                    // {tag:Status, title:직원 25.0% 출근 완료, date:2022. 08. 09. 15:58:04}
                    userStatus = strArr.substring(strArr.indexOf("title:")+6, strArr.indexOf(", date:"));
                    date = strArr.substring(strArr.indexOf("date:")+5, strArr.length()-1);
                    name = "System";
                    department = "System";
                }
                else
                {
                    // {tag:Login, title:로그인 성공, name:정재훈, email:jjh@naver.com, department:개발팀 date:2022. 08. 05. 18:41:38}
                    userStatus = strArr.substring(strArr.indexOf("title:")+6, strArr.indexOf(", name:"));
                    name = strArr.substring(strArr.indexOf("name:")+5, strArr.indexOf(", email:"));
                    department = strArr.substring(strArr.indexOf("department:")+11, strArr.indexOf(", date:"));
                    date = strArr.substring(strArr.indexOf("date:")+5, strArr.length()-1);
                    date = date.substring(14, date.length() - 3); // 시간 정보만 반환되도록
                }

                UserStatusLogdataDto logData = new UserStatusLogdataDto(
                        userStatus
                        , name
                        , department
                        , date
                        , status.getColor(userStatus) // 상태에 따른 컬러값 반환
                );
                logDataList.add(logData);
            }
        }

        return logDataList;
    }

    public List<UserStatusLogdataDto> findStatusNotification(JsonArray jsonArray)
    {
        String strArr = "";
        String userStatus = "";
        String name = "";
        String department = "";
        String date = "";

        List<UserStatusLogdataDto> logDataList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) // jsonArray의 size는 {}단위로 구분
        {
            // 루프를 돌면서 jsonArray 안의 object(중괄호)를 빼내어 값을 추출
            if (jsonArray.get(i).getAsString().contains("tag:"+ logTag.TAG_USER_STATUS)) // 출퇴근 관련 로그만 반환
            {
                strArr = "";
                strArr += jsonArray.get(i).getAsString().replace("\"", ""); // 양옆 \" 제거

                if (!(jsonArray.get(i).getAsString().contains("name"))) // name이 없는 경우 == 출근 퍼센트를 나타낸 로그일 경우
                {
                    // {tag:Status, title:직원 25.0% 출근 완료, date:2022. 08. 09. 15:58:04}
                    userStatus = strArr.substring(strArr.indexOf("title:")+6, strArr.indexOf(", date:"));
                    date = strArr.substring(strArr.indexOf("date:")+5, strArr.length()-1);
                    name = "System";
                    department = "System";
                }
                else
                {
                    // {tag:Login, title:로그인 성공, name:정재훈, email:jjh@naver.com, department:개발팀 date:2022. 08. 05. 18:41:38}
                    userStatus = strArr.substring(strArr.indexOf("title:")+6, strArr.indexOf(", name:"));
                    name = strArr.substring(strArr.indexOf("name:")+5, strArr.indexOf(", email:"));
                    department = strArr.substring(strArr.indexOf("department:")+11, strArr.indexOf(", date:"));
                    date = strArr.substring(strArr.indexOf("date:")+5, strArr.length()-1);
                }

                UserStatusLogdataDto logData = new UserStatusLogdataDto(userStatus, name, department, date);
                logDataList.add(logData);
            }
            else
            {
                continue;
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
    public List<UserStatusLogdataDto> getStatusNotification(String fileName) throws IOException
    {
        List<String> readLines = Files.readAllLines(Paths.get(filePath + fileName));

        // List<String> -> json으로 변환
        String json = new Gson().toJson(readLines);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(json);

        return findStatusNotification(jsonArray);
    }

}