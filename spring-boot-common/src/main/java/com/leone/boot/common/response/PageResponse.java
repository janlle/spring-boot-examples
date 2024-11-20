package com.leone.boot.common.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.List;

/**
 * 分页响应
 */
@Setter
@Getter
public class PageResponse<T> extends MultiResponse<T> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 每页结果数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 总数
     */
    private int total;

    public static <T> PageResponse<T> of(List<T> data, int total, int pageSize, int currentPage) {
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setSuccess(true);
        pageResponse.setData(data);
        pageResponse.setTotal(total);
        pageResponse.setPageSize(pageSize);
        pageResponse.setCurrentPage(currentPage);
        pageResponse.setTotalPage((pageSize + total - 1) / pageSize);
        return pageResponse;
    }
}
