package com.binea.service;

/**
 * Created by binea
 * Date: 29/11/2017
 * TIME: 10:29 PM
 */
public interface BaseService<Mapper> {

    /**
     * 获取基本操作mapper
     *
     * @return
     */
    Mapper getMapper();
}
