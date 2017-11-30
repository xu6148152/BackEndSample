package com.binea.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by binea
 * Date: 30/11/2017
 * TIME: 10:42 PM
 */
public interface CmsTagMapper {
    int countByExample(CmsTagExample example);

    int deleteByExample(CmsTagExample example);

    int deleteByPrimaryKey(Integer tagId);

    int insert(CmsTag record);

    int insertSelective(CmsTag record);

    List<CmsTag> selectByExample(CmsTagExample example);

    int updateByExampleSelective(@Param("record") CmsTag record, @Param("example") CmsTagExample example);

    int updateByExample(@Param("record") CmsTag record, @Param("example") CmsTagExample example);
}
