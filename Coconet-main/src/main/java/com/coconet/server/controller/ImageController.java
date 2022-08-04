package com.coconet.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/coconet")
@RequiredArgsConstructor
public class ImageController {

    /**
     소스 설명
     1. file 정보를 가져오고 그 값을 내 로컬 어딘가(여기서는 C://coconet/images/)에 저장함
     2. 이 때, 중복이 일어날 수 있으니 파일 속성별 위치저장소 + 현재시간 등을 이용하여 중복값을 없애줌
     3. DB에 정보 저장
     4. 프론트에서 DB에 있는 image 정보를 요청
        -> getMapping을 이용하여 원하는 이미지를 불러올 수 있음
     */

    @PostMapping("/image")
    public ResponseEntity<String> createFeed(@RequestParam("file") MultipartFile file) {

        // <시간 + FileName>으로 경로 생성
        Date date = new Date();
        StringBuilder sb = new StringBuilder();

        // 빈 파일일 경우
        if (file.isEmpty()) {
            sb.append("none");
        } else {
            sb.append(date.getTime());
            sb.append(file.getOriginalFilename());
        }

        // 정상 파일일 경우
        if (!file.isEmpty()) {
            File dest = new File("C://coconet/images/" + sb.toString());
            try {
                file.transferTo(dest);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 여기서 db에 저장

        }

        return new ResponseEntity<String>(HttpStatus.OK);
    }

}