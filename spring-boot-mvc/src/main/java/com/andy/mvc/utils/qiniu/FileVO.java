package com.andy.mvc.utils.qiniu;

import java.util.List;

/**
 * <p>
 *
 * @author Leone
 * @since: 2018-09-02
 **/
public class FileVO {

    private List<String> urlList;

    public FileVO() {
    }

    public FileVO(List<String> urlList) {
        this.urlList = urlList;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }
}
