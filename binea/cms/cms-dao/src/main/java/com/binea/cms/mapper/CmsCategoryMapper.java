package com.binea.cms.mapper;

import com.binea.cms.model.CmsCategory;
import com.binea.cms.model.CmsCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by binea
 * Date: 30/11/2017
 * TIME: 10:40 PM
 */
public interface CmsCategoryMapper {
    int countByExample(CmsCategoryExample example);

    int deleteByExample(CmsCategoryExample example);

    int deleteByPrimaryKey(Integer categoryId);

    int insert(CmsCategory record);

    int insertSelective(CmsCategory record);

    List<CmsCategory> selectByExample(CmsCategoryExample example);

    int updateByExampleSelective(@Param("record") CmsCategory record, @Param("example") CmsCategoryExample example);

    int updateByExample(@Param("record") CmsCategory record, @Param("example") CmsCategoryExample example);
}