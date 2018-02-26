package com.binea.pay.dao.mapper;

import com.binea.pay.dao.model.PayMch;
import com.binea.pay.dao.model.PayMchExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayMchMapper {
    long countByExample(PayMchExample example);

    int deleteByExample(PayMchExample example);

    int deleteByPrimaryKey(Integer payMchId);

    int insert(PayMch record);

    int insertSelective(PayMch record);

    List<PayMch> selectByExample(PayMchExample example);

    PayMch selectByPrimaryKey(Integer payMchId);

    int updateByExampleSelective(@Param("record") PayMch record, @Param("example") PayMchExample example);

    int updateByExample(@Param("record") PayMch record, @Param("example") PayMchExample example);

    int updateByPrimaryKeySelective(PayMch record);

    int updateByPrimaryKey(PayMch record);
}