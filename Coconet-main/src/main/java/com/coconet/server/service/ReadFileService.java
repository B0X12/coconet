package com.coconet.server.service;

import com.google.gson.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ReadFileService
{
    private final String filePath = "./logs/";


    public String spliceJsonString(JsonArray jsonArray)
    {
        String strArr = "";
        String replaceStrArr = "";

        for (int i = 0; i < jsonArray.size(); i++) // jsonArray의 size는 {}단위로 구분
        {
            strArr += jsonArray.get(i).getAsString().replace("\"", "");
            replaceStrArr += strArr.replace("&", "\"");
            replaceStrArr += ", ";
            strArr = "";
        }

        return replaceStrArr;

    }

    public String spliceJsonString(JsonArray jsonArray, String findUserEmail)
    {
        String strArr = "";
        String replaceStrArr = "";

        for (int i = 0; i < jsonArray.size(); i++) // jsonArray의 size는 {}단위로 구분
        {
            // 루프를 돌면서 jsonArray 안의 object(중괄호)를 빼내어 값을 추출
            if (!(jsonArray.get(i).getAsString().contains("&email&:"+"&"+findUserEmail+"&"))) // 내가 원하는 사용자의 로그가 아니면
            {
                continue;
            }
            else
            {
                strArr += jsonArray.get(i).getAsString().replace("\"", ""); // 양옆 \" 제거
                replaceStrArr += strArr.replace("&", "\""); // "key":"value"로 세팅
                replaceStrArr += ", ";
                strArr = "";
            }

        }

        return replaceStrArr;
    }

    /**
     * @관리자페이지
     * 관리자 로그 조회
     */
    public String getFileContents(String fileName) throws IOException
    {
        List<String> readLines = Files.readAllLines(Paths.get(filePath + fileName));

        // List<String> -> json으로 변환
        String json = new Gson().toJson(readLines);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(json);

        return spliceJsonString(jsonArray);
    }

    /**
     * @관리자페이지
     * 사용자 로그 조회 (email로 구분)
     */
    public String getFileContents(String fileName, String findUserEmail) throws IOException
    {
        List<String> readLines = Files.readAllLines(Paths.get(filePath + fileName));

        // List<String> -> json으로 변환
        String json = new Gson().toJson(readLines);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(json);

        return spliceJsonString(jsonArray, findUserEmail);
    }


    /**
     * 관리자페이지 - 사용자 로그 조회 (email로 구분)
     * - 기존의 방식이었던것 -> "{key:value}"로 반환되어 json으로 인식 못함 -> failed
     */
    /*
    public List<String> getFileContents(String fileName, String findUserEmail) throws IOException
    {
        List<String> readLines = Files.readAllLines(Paths.get(filePath + fileName));
        List<String> readLinesArr = new ArrayList<>(readLines);

        for (int i = 0; i < readLinesArr.size(); i++)
        {
            // 찾고있는 유저의 로그가 아니면
            if (!(readLinesArr.get(i).contains("\"email\":" + "\"" + findUserEmail + "\"")))
            {
                readLinesArr.remove(i);
                i--;
            }
        }

        return readLinesArr;
    }
    */


    /**
     * 관리자페이지 - 사용자 로그 조회 (email로 구분)
     * - JsonObject에서 Not a JSON Object 오류 지속적으로 발생
     * - JSONObject도 마찬가지 (json-simple lib)
     * - Jackson도 에러
     */
    /*
    public List<LogDataDto> getFileContents(String fileName, String findUserEmail) throws IOException, ParseException {
        List<String> readLines = Files.readAllLines(Paths.get(filePath + fileName));

        // List<String> -> json으로 변환
        String json = new Gson().toJson(readLines);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(json);

        String[] strArr = null;

        for (int i = 0; i < jsonArray.size(); i++) // jsonArray의 size는 {}단위로 구분
        {
            // 루프를 돌면서 jsonArray 안의 object(중괄호)를 빼내어 값을 추출
            strArr[i] = jsonArray.get(i).getAsString().replace("\"", "");

            String email = jsonObject.get("email").getAsString();
            System.out.println("coco:::::::::" + email);
            if (!(email.equals(findUserEmail))) // 찾고있는 유저의 로그가 아니면
            {
                break;
            }
            else
            {
                String tag = jsonObject.get("tag").toString();
                String title = jsonObject.get("title").toString();
                String name = jsonObject.get("name").toString();
                String date = jsonObject.get("date").toString();

                logData.setTag(tag);
                logData.setTitle(title);
                logData.setName(name);
                logData.setEmail(email);
                logData.setDate(date);

                logDataList.add(logData);
            }
        }

        return logDataList;
    }
    */

}