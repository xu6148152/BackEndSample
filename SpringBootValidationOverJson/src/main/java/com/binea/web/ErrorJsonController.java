package com.binea.web;

import com.binea.constant.CityErrorInfoEnum;
import com.binea.result.GlobalErrorInfoException;
import com.binea.result.ResultBody;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by binea
 * Date: 29/10/2017
 * TIME: 4:52 PM
 */
@RestController
public class ErrorJsonController {

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public ResultBody findOneCity(@RequestParam("cityName") String cityName) throws GlobalErrorInfoException {
        if(StringUtils.isEmpty(cityName)) {
            throw new GlobalErrorInfoException(CityErrorInfoEnum.PARAMS_NO_COMPLETE);
        }

        return new ResultBody(new City(1L, 2L, "南安", "是我的家乡"));
    }
}
