package com.green.shop.admin.service;

import com.green.shop.admin.vo.SearchVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    //상품 등록
    //   +
    //상품 이미지 등록

    //rollbackFor : 언제 롤백을 실행 시키는지?
    //Exception.class : 무슨 예외던지 오류가 나면 롤백 시켜라.
    @Transactional(rollbackFor = Exception.class)
    // Transactional 어노테이션이 붙어 있는
    // 메소드 내부의 쿼리실행은
    // 모든 쿼리가 실행 성공 시 커밋!
    // 쿼리 중 하나라도 실패 시 롤백!
    public void itemInsert(ItemVO itemVO) {
        sqlSession.insert("adminMapper.itemInsert", itemVO);
        sqlSession.insert("adminMapper.insertImgs" , itemVO);
    }

    @Override
    public int selectNextItemCode() {
        return sqlSession.selectOne("adminMapper.selectNextItemCode");
    }

    @Override
    public List<BuyVO> selectAdminList(SearchVO searchVO) {
        return sqlSession.selectList("adminMapper.selectAdminList", searchVO);
    }

    @Override
    public List<BuyVO> selectBuyList() {
        return sqlSession.selectList("adminMapper.selectBuyList");
    }

    @Override
    public BuyVO selectDetailHistory(BuyVO buyVO) {
        return sqlSession.selectOne("adminMapper.selectDetailHistory", buyVO);
    }

    //삭제하기
    @Override
    public List<ItemVO> selectDetailItem() {
        return sqlSession.selectList("adminMapper.selectDetailItem");
    }

    //상세정보
    @Override
    public ItemVO selectAdmin(int itemCode) {
        return sqlSession.selectOne("adminMapper.selectAdmin", itemCode);
    }

    //업데이트
    @Override
    public int updateAdmin(ItemVO itemVO) {
        return sqlSession.update("adminMapper.updateAdmin", itemVO);
    }


}
