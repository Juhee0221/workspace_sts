package com.green.shop.buy.service;

import com.green.shop.buy.vo.BuyListVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.vo.CartVO;
import jakarta.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service("buyService")
public class BuyServiceImpl implements BuyService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    // insert 동시 실행
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBuy(BuyVO buyVO, CartVO cartVO) {
        sqlSession.insert("buyMapper.insertBuy", buyVO);
        sqlSession.insert("buyMapper.insertBuyDetail", buyVO);
        sqlSession.delete("cartMapper.deleteCarts",cartVO);
    }

    // 자동으로 buyCode ++ 실행?
    @Override
    public int selectNextBuyCode() {
        return sqlSession.selectOne("buyMapper.selectNextBuyCode");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertProductBuy(BuyVO buyVO, BuyListVO buyListVO) {
        sqlSession.insert("buyMapper.insertBuy", buyVO);
        sqlSession.insert("buyMapper.insertProductBuy", buyListVO);
    }

    @Override
    public List<BuyVO> selectBuyList(String memberId) {
        return sqlSession.selectList("buyMapper.selectBuyList", memberId);
    }
}
