package com.binea.ucenter.dao.mapper;

import com.binea.ucenter.dao.model.UcenterUser;
import com.binea.ucenter.dao.model.UcenterUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UcenterUserMapper {
    long countByExample(UcenterUserExample example);

    int deleteByExample(UcenterUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UcenterUser record);

    int insertSelective(UcenterUser record);

    List<UcenterUser> selectByExample(UcenterUserExample example);

    UcenterUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UcenterUser record, @Param("example") UcenterUserExample example);

    int updateByExample(@Param("record") UcenterUser record, @Param("example") UcenterUserExample example);

    int updateByPrimaryKeySelective(UcenterUser record);

    int updateByPrimaryKey(UcenterUser record);
}