package com.example.vmSystem.controller;

import com.example.vmSystem.service.CarServiceImpl;
import com.example.vmSystem.vo.CarVO;
import com.example.vmSystem.vo.SaleVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.crypto.KeySelector;
import java.util.List;

@Controller
public class CarController {
    @Resource(name = "carService")
    private CarServiceImpl carService;

    @GetMapping("/")
    public String mainPage (){

        return "fragment/firstPage";
    }

    // 차량 등록 페이지로 이동.
    @GetMapping("/insertCar")
    public String insertCar(Model model){

        List<CarVO> carList = carService.selectCarInfo();

        model.addAttribute("carList", carList);

        return "fragment/insertCar";
    }
    @PostMapping("/insertCar")
    public String insert(CarVO carVO){

        carService.insertCarInfo(carVO);
        return "redirect:/insertCar";
    }
    
    // 판매 페이지 이동
    @GetMapping("/salesInformation")
    public String salesInformation(Model model){

        model.addAttribute("makerList", carService.selectCar());

        return "fragment/salesInformation";
    }

    //판매 정보 등록

    @GetMapping("/salesSelectCar")
    public  String selectCar(Model model){

        List<SaleVO> saleList = carService.selectSar();

        model.addAttribute("saleList", saleList);

        return "fragment/selectCar";
    }

    @PostMapping ("/salesSelectCar")
    public String salesInfo(SaleVO saleVO,Model model){
        System.out.println(saleVO);

        carService.insertSal(saleVO);

        List<SaleVO> saleList = carService.selectSar();

        model.addAttribute("saleList", saleList);
        return "fragment/selectCar";
    }

}
