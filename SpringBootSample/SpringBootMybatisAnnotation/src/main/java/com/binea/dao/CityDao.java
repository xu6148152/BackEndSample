package com.binea.dao;

import com.binea.domain.City;
import org.apache.ibatis.annotations.*;

/**
 * Created by binea
 * Date: 28/10/2017
 * TIME: 4:12 PM
 */
@Mapper
public interface CityDao {

    @Select("SELECT * FROM city")

    @Results({@Result(property = "id", column = "id"),
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "description", column = "description"),}
    )
    City findByName(@Param("cityName") String cityName);
}
