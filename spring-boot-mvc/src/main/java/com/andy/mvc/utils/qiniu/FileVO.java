package com.andy.mvc.utils.qiniu;

import java.util.List;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-02
 **/
public class FileVO {

    private List<String> urls;

    public FileVO() {
    }

    public FileVO(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
