package com.binea.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by binea
 * Date: 30/11/2017
 * TIME: 10:41 PM
 */

public interface CmsCategoryTagMapper {
    int countByExample(CmsCategoryTagExample example);

    int deleteByExample(CmsCategoryTagExample example);

    int deleteByPrimaryKey(Integer categoryTagId);

    int insert(CmsCategoryTag record);

    int insertSelective(CmsCategoryTag record);

    List<CmsCategoryTag> selectByExample(CmsCategoryTagExample example);

    CmsCategoryTag selectByPrimaryKey(Integer categoryTagId);

    int updateByExampleSelective(@Param("record") CmsCategoryTag record, @Param("example") CmsCategoryTagExample example);

    int updateByExample(@Param("record") CmsCategoryTag record, @Param("example") CmsCategoryTagExample example);

    int updateByPrimaryKeySelective(CmsCategoryTag record);

    int updateByPrimaryKey(CmsCategoryTag record);
}