package com.leone.boot.mvc.util;


import com.leone.boot.common.response.PageResponse;
import com.leone.boot.mvc.web.vo.MultiResult;

import static com.leone.boot.common.response.ResponseCode.SUCCESS;

/**
 * @author leone
 */
public class MultiResultConvertor {

    public static <T> MultiResult<T> convert(PageResponse<T> pageResponse) {
        return new MultiResult<>(true, SUCCESS.name(), SUCCESS.name(), pageResponse.getData(), pageResponse.getTotal(), pageResponse.getCurrentPage(), pageResponse.getPageSize());
    }

}
