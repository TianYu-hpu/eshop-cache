package com.roncoo.eshop.cache.mapper;

import com.roncoo.eshop.cache.model.ShopInfo;
import com.roncoo.eshop.cache.model.ShopInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopInfoMapper {
    long countByExample(ShopInfoExample example);

    int deleteByExample(ShopInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopInfo record);

    int insertSelective(ShopInfo record);

    List<ShopInfo> selectByExample(ShopInfoExample example);

    ShopInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopInfo record, @Param("example") ShopInfoExample example);

    int updateByExample(@Param("record") ShopInfo record, @Param("example") ShopInfoExample example);

    int updateByPrimaryKeySelective(ShopInfo record);

    int updateByPrimaryKey(ShopInfo record);
}