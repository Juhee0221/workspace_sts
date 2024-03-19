package com.example.vmSystem.service;

import com.example.vmSystem.vo.CarVO;
import com.example.vmSystem.vo.SaleVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carService")
public class CarServiceImpl implements CarService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertCarInfo(CarVO carVO) {
        sqlSession.insert("carMapper.insertCarInfo", carVO);
    }

    @Override
    public List<CarVO> selectCarInfo() {
        return sqlSession.selectList("carMapper.selectCarInfo");
    }

    @Override
    public List<CarVO> selectCar() {
        return sqlSession.selectList("carMapper.selectCar");
    }

    @Override
    public void insertSal(SaleVO saleVO) {
        sqlSession.insert("carMapper.insertSal", saleVO);
    }

    @Override
    public List<SaleVO> selectSar() {
        return sqlSession.selectList("carMapper.selectSar");
    }
}
