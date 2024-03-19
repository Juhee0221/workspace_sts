package com.green.shop.item.sevice;

import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ItemVO;
import jakarta.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private SqlSessionTemplate session;


    @Override
    public List<CategoryVO> selectCategory() {
        return session.selectList("itemMapper.selectCategory");
    }

    @Override
    /* 상품 조회 */
    public List<ItemVO> selectItem() {
        return session.selectList("itemMapper.selectItem");
    }

    @Override
    /* 상품 상세 목록 조회 */
    public ItemVO selectItemDetail(int itemCode) {
        return session.selectOne("itemMapper.selectItemDetail", itemCode);
    }


}
