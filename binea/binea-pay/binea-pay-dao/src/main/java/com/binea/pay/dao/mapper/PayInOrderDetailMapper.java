package com.binea.pay.dao.mapper;

import com.binea.pay.dao.model.PayInOrderDetail;
import com.binea.pay.dao.model.PayInOrderDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayInOrderDetailMapper {
    long countByExample(PayInOrderDetailExample example);

    int deleteByExample(PayInOrderDetailExample example);

    int deleteByPrimaryKey(Integer payInOrderDetailId);

    int insert(PayInOrderDetail record);

    int insertSelective(PayInOrderDetail record);

    List<PayInOrderDetail> selectByExample(PayInOrderDetailExample example);

    PayInOrderDetail selectByPrimaryKey(Integer payInOrderDetailId);

    int updateByExampleSelective(@Param("record") PayInOrderDetail record, @Param("example") PayInOrderDetailExample example);

    int updateByExample(@Param("record") PayInOrderDetail record, @Param("example") PayInOrderDetailExample example);

    int updateByPrimaryKeySelective(PayInOrderDetail record);

    int updateByPrimaryKey(PayInOrderDetail record);
}