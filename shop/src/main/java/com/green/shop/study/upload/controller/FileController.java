package com.green.shop.study.upload.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring6.processor.SpringOptionInSelectFieldTagProcessor;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//파일 업로드, 다운로드 학습용 컨트롤러
@Controller
@RequestMapping("/file")
public class FileController {

    //파일 첨부 페이지로 이동
    @GetMapping("/uploadForm")
    public String uploadForm(){

        //파일 확장자 추출
        String file1 = "abc.jpg";
        //1번 부터 5번째 전까지.
        System.out.println(file1.substring(1,5));
        System.out.println(file1.substring(1));

        System.out.println(file1.indexOf("."));
        //가장 마지막
        System.out.println(file1.lastIndexOf((".")));


        return "test/upload/upload_form";
    }

    //파일 업로드
    @PostMapping("/upload")
    //똑같은 매개변수의 이름으로 정보를 들고 오는것 처럼
    // img 또한 똑같은 이름의 매개변수로 들고오면 됨.

    public String upload(@RequestParam(name = "img1") MultipartFile img1
                        ,@RequestParam(name = "img2") MultipartFile img2) throws IOException {
        //getOriginal...~ 첨부한 파일명을 가져오겠다.
        String originFileName = img1.getOriginalFilename();
        System.out.println(originFileName);

        //업로드 경로
        //업로드한 파일을 저장할 파일경로를 가지고 오기.
        String uploadPath = "D:\\01-STUDY\\dev\\workspace_sts\\shop\\src\\main\\resources\\static\\upload\\";
        //특수문자를 문자열로 취급하기 위해서는
        // 특수문자 앞에 \를 붙여주면 됨. ex) "\"안녕\"";


        //업로드 할 경로 및 파일명을 하나의 문자열로 나열
        String fileName = uploadPath + originFileName;

        //파일 업로드
        //java.io.File

        //파일을 업로드 하다가 오류가 나면 catch문 실행
        try{
            File file = new File(fileName);
            img1.transferTo(file);
        } catch (IOException e){
            System.out.println("!! 파일 첨부 중 오류 발생 !!");
            throw new RuntimeException(e);
        }

        //2번째 파일을 업로드.
        //원본 파일의 확장자만 추출
        String secondOriginFileName = img2.getOriginalFilename();
        String extension = secondOriginFileName.substring(secondOriginFileName.lastIndexOf("."));

        //1) 서버에 저장할 파일명 생성

        //랜덤으로 문자열 이름을 생성
        String uuid = UUID.randomUUID().toString();
        String attachedFileName = uuid + extension;

        try {
            File file1 = new File(uploadPath + attachedFileName);
            img2.transferTo(file1);
        }
        catch (Exception e){
            System.out.println("파일 첨부 중 오류발생!!");
            //어디서 오류가 나는지 알려줌
            e.printStackTrace();
        }


        return "";
    }

    //다중 첨부 페이지로 이동
    @GetMapping("/multiForm")
    public String multiForm(){

        return "test/upload/multi_form";
    }
    //다중 첨부 실행
    @PostMapping("/multi")
    //다중으로 이미지가 첨부될때는 배열 사용
    public String multi(@RequestParam(name = "imgs") MultipartFile[] imgs){
        String uploadPath = "D:\\01-STUDY\\dev\\workspace_sts\\shop\\src\\main\\resources\\static\\upload\\";

        for(MultipartFile img : imgs){

            System.out.println(img.getOriginalFilename());

            //확장자 추출
            String extension = img.getOriginalFilename().substring
                    (img.getOriginalFilename().lastIndexOf("."));

            //첨부될 파일명
            String attachedFileName
                    = UUID.randomUUID().toString() + extension;

            //파일 첨부
            try {
                //파일 경로 + 첨부될 파일명 삽입
                File file = new File(uploadPath + attachedFileName);
                img.transferTo(file);
            }
            catch (Exception e){
                System.out.println("!!!다중 첨부 중 오류 발생!!!");
                e.printStackTrace();
            }
        }

        return "";
    }
}
