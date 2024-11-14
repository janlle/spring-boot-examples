package com.leone.boot.common.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 响应
 */
@Setter
@Getter
public class MultiResponse<T> extends BaseResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<T> data;

    public static <T> MultiResponse<T> of(List<T> data) {
        MultiResponse<T> multiResponse = new MultiResponse<>();
        multiResponse.setSuccess(true);
        multiResponse.setData(data);
        return multiResponse;
    }

}
