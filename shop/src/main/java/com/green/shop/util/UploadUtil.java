package com.green.shop.util;

import com.green.shop.item.vo.ImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//파일 첨부와 관련된 기능 모음집
public class UploadUtil {
    //파일의 확장자를 문자열로 리턴 하는 메소드.
    public static String getExtension(String fileName){
       return fileName.substring(fileName.lastIndexOf("."));
    }

    //uuid를 통한 파일명 생성
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    //단일 파일 업로드 메소드
    //첨부된 내용을 리턴하는 메소드.
    public static ImgVO uploadFile(MultipartFile uploadFile){
        ImgVO imgVO = null;

        if(!uploadFile.isEmpty()){
            imgVO = new ImgVO();
            //확장자 추출
            String extension = getExtension(uploadFile.getOriginalFilename());

            //중복되지 않는 파일명 생성
            //메소드 결과를 리턴

            String fileName = getUUID() + extension;

            try {
                File file1 = new File(ConstantVariable.UPLOAD_PATH + fileName);
                uploadFile.transferTo(file1);

                imgVO.setAttachedFileName(fileName);
                imgVO.setOriginFileName(uploadFile.getOriginalFilename());

                //단일 이미지이니 기본적으로 setter에 'Y'를 넣어줌.
                imgVO.setIsMain("Y");

            }catch (Exception e){
                System.out.println("!!파일 첨부 중 예외 발생!!");
                e.printStackTrace();
            }
        }
        return imgVO;
    }

    //다중 첨부 메소드
    //ImgVO의 여러개 데이터를 가지고 와야 되기 때문에 return 값을 List로 사용
    public static List<ImgVO> multiUploadFile(MultipartFile[] uploadFiles){
        List<ImgVO> imgList = new ArrayList<>();

        //위에서 가지고 오는 메소드의 기본 리턴타입이 ImgVO
        for (MultipartFile uploadFile : uploadFiles){
           ImgVO vo = uploadFile(uploadFile);

           //다중 첨부의 메인여부 설정
           if(vo != null){
               vo.setIsMain("N");
               imgList.add(vo);
           }
        }
       //모든 정보를 가지고 있는 imgList를 리턴
        return imgList;
    }
}
