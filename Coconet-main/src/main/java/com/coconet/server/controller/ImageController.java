package com.coconet.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping(value = "/image"
            , produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType(HttpServletRequest request) throws IOException {

        String fileName = request.getParameter("fileName");
        Map<String, Object> param = new HashMap<String, Object>();

        String res = "Coconet-main\\Coconet-main\\src\\main\\resources\\images\\" + fileName + ".png";
        InputStream in = new FileInputStream(res);

        return IOUtils.toByteArray(in);
    }

}