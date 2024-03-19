package com.green.shop.admin.controller;

import com.green.shop.admin.service.AdminService;
import com.green.shop.item.sevice.ItemService;
import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;
import com.green.shop.util.ConstantVariable;
import com.green.shop.util.UploadUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource(name = "adminService")
    private AdminService adminService;
    @Resource(name = "itemService")
    private ItemService itemService;


    //상품 등록 페이지로 이동
    @GetMapping("/regItemForm")
    public String regItemForm(Model model){
        List<CategoryVO> category = itemService.selectCategory();

        model.addAttribute("category", category);

        return "content/admin/reg_item_form";
    }


    //상품등록 하기
    @PostMapping("/regItemForm")
    public String insertItem(ItemVO itemVO,
                             @RequestParam(name = "mainImg") MultipartFile mainImg ,
                             @RequestParam(name= "detailImg") MultipartFile[] detailImg){
        //-------------------------------------- 파일 첨부 기능 --------------------------------//
        //파일 업로드 하는 메소드.
        //메인 이미지 하나 업로드
        ImgVO mainImgVO = UploadUtil.uploadFile(mainImg);

        //상세 이미지들 업로드
        //들고 오는 타입에 맞게 설정.
        List<ImgVO> imgList = UploadUtil.multiUploadFile(detailImg);

        //-----------------------------다음에 들어갈 ITEM-CODE 조회 ----------------------------//
        int itemCode = adminService.selectNextItemCode();

        //--------------------------------- 상품 등록 기능 -------------------------------------//

        //itemVO 안에 itemCode 값을 넣어줌
        itemVO.setItemCode(itemCode);

        //---------------------------- 상품 이미지 정보 등록 쿼리 실행 --------------------------//

        //현재 itemVO는 상품명, 가격, 상품소개, cateCode

        //이미지 등록 시 채워줘야하는 모든 데이터를 갖는 리스트 생성

        // mainImg VO 안에 원본이미지명, 상세이미지명, 메인여부 ,상품코드 내용이 다 들어감.
        mainImgVO.setItemCode(itemCode);

        //리스트에 담긴 내용을 반복문을 돌려 각 데이터를 ImgVO에 저장
        for( ImgVO img: imgList){
            img.setItemCode(itemCode);
        }
        //모든 정보를 다 들고 가야 되기 때문에 list에 그 값을 더 해줌.
        imgList.add (mainImgVO);

        //itemVO가 정보값을 들고간 후 저장하는 역할
        itemVO.setImgList(imgList);

        System.out.println(itemVO);
        //이미지등록 겸 상품등록이기에 마지막으로.
        adminService.itemInsert(itemVO); //itemVO


        return "redirect:/admin/regItemForm";
    }
}
