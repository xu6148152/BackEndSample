package com.binea.cms.mapper;

import com.binea.cms.model.CmsArticle;
import com.binea.cms.model.CmsArticleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by binea
 * Date: 30/11/2017
 * TIME: 10:36 PM
 */

public interface CmsArticleMapper {
    int countByExample(CmsArticleExample example);

    int deleteByExample(CmsArticleExample example);

    int deleteByPrimaryKey(Integer articleId);

    int insert(CmsArticle record);

    int insertSelective(CmsArticle record);

    List<CmsArticle> selectByExampleWithBLOBs(CmsArticleExample example);

    List<CmsArticle> selectByExample(CmsArticleExample example);

    CmsArticle selectByPrimaryKey(Integer articleId);

    int updateByExampleSelective(@Param("record") CmsArticle record, @Param("example") CmsArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsArticle record, @Param("example") CmsArticleExample example);

    int updateByExample(@Param("record") CmsArticle record, @Param("example") CmsArticleExample example);

    int updateByPrimaryKeySelective(CmsArticle record);

    int updateByPrimaryKeyWithBLOBs(CmsArticle record);

    int updateByPrimaryKey(CmsArticle record);
}
