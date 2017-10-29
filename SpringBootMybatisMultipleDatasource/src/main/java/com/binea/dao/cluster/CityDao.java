package com.binea.dao.cluster;

import com.binea.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by binea
 * Date: 29/10/2017
 * TIME: 6:00 PM
 */
@Mapper
public interface CityDao {
    City findByName(@Param("cityName") String cityName);
}
