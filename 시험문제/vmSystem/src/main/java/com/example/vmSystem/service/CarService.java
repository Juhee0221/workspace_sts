package com.example.vmSystem.service;

import com.example.vmSystem.vo.CarVO;
import com.example.vmSystem.vo.SaleVO;

import java.util.List;

public interface CarService {
    void insertCarInfo (CarVO carVO);

    List<CarVO> selectCarInfo();

    List<CarVO> selectCar();

    void insertSal(SaleVO saleVO);

    List<SaleVO> selectSar();
}
