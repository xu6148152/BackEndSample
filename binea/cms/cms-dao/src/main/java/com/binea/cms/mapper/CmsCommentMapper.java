package com.binea.cms.mapper;

import com.binea.cms.model.CmsComment;
import com.binea.cms.model.CmsCommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by binea
 * Date: 6/12/2017
 * TIME: 10:25 PM
 */
public interface CmsCommentMapper {
    int countByExample(CmsCommentExample example);

    int deleteByExample(CmsCommentExample example);

    int deleteByPrimaryKey(Integer commentId);

    int insert(CmsComment record);

    int insertSelective(CmsComment record);

    List<CmsComment> selectByExampleWithBLOBs(CmsCommentExample example);

    List<CmsComment> selectByExample(CmsCommentExample example);

    CmsComment selectByPrimaryKey(Integer commentId);

    int updateByExampleSelective(@Param("record") CmsComment record, @Param("example") CmsCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsComment record, @Param("example") CmsCommentExample example);

    int updateByExample(@Param("record") CmsComment record, @Param("example") CmsCommentExample example);

    int updateByPrimaryKeySelective(CmsComment record);

    int updateByPrimaryKeyWithBLOBs(CmsComment record);

    int updateByPrimaryKey(CmsComment record);
}
