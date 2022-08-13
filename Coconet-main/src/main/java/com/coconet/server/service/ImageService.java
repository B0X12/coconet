package com.coconet.server.service;

import com.coconet.server.entity.ImageData;
import com.coconet.server.entity.Users;
import com.coconet.server.jwt.JwtTokenProvider;
import com.coconet.server.repository.ImageDataRepository;
import com.coconet.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final ImageDataRepository imageDataRepository;

    public void fileUpload(MultipartFile file, String accessToken) {
        Users user =  userRepository.findByEmail(jwtTokenProvider.getUserEmail(accessToken));
        String userProfil = user.getNum() + "_" + user.getName();

        String filename = file.getOriginalFilename();
        //파일 명을 가져옴
        long fileSize = file.getSize();
        //파일 사이즈를 가져옴

        System.out.println("파일명 : "  + filename);
        System.out.println("용량크기(byte) : " + fileSize);

        String fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());
        //파일의 확장자를 가져옴
        String uploadFolder = "D:\\Users\\JJH\\Desktop\\Coconet\\Coconet-main\\Coconet-main\\src\\main\\resources\\images\\";
        //파일 저장경로

        ImageData imageData = new ImageData(user.getNum(), userProfil, fileSize, uploadFolder, fileExtension);
        File saveFile = new File(uploadFolder + userProfil + fileExtension);
        try {
            file.transferTo(saveFile);              //경로에 파일 저장
            imageDataRepository.save(imageData);    //DB에 파일 정보 저장
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity<byte[]> fileInput(int num){
        Users user =  userRepository.findByNum(num);
        ImageData imageData = imageDataRepository.findByNum(user.getNum());

        ResponseEntity<byte[]> result = null;

        String fileName = imageData.getFileName();
        String fileExtension = imageData.getFileExtension();
        String uploadFolder = imageData.getFilePath();

        System.out.println("파일명: " + fileName);
        System.out.println("확장자: " + fileExtension);
        System.out.println("경로: " + uploadFolder);

        File file = new File(uploadFolder + fileName + fileExtension);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}