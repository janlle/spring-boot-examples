package com.leone.boot.log.app.entity;

/**
 * <p> 应用上报的页面相关信息
 *
 * @author leone
 * @since 2019-03-20
 **/
public class AppPageLog extends AppBaseLog {

    private static final long serialVersionUID = 1L;

    // 一次启动中的页面访问次数(应保证每次启动的所有页面日志在一次上报中，即最后一条上报的页面记录的nextPage为空)
    private Integer pageViewCntInSession = 0;

    // 页面id
    private String pageId;

    // 访问顺序号，0为第一个页面
    private Integer visitIndex = 0;

    // 下一个访问页面，如为空则表示为退出应用的页面
    private String nextPage;

    // 当前页面停留时长
    private Integer standingTime = 0;

    public AppPageLog() {
    }

    public AppPageLog(Integer pageViewCntInSession, String pageId, Integer visitIndex, String nextPage, Integer standingTime) {
        this.pageViewCntInSession = pageViewCntInSession;
        this.pageId = pageId;
        this.visitIndex = visitIndex;
        this.nextPage = nextPage;
        this.standingTime = standingTime;
    }

    public Integer getPageViewCntInSession() {
        return pageViewCntInSession;
    }

    public void setPageViewCntInSession(Integer pageViewCntInSession) {
        this.pageViewCntInSession = pageViewCntInSession;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public Integer getVisitIndex() {
        return visitIndex;
    }

    public void setVisitIndex(Integer visitIndex) {
        this.visitIndex = visitIndex;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getStandingTime() {
        return standingTime;
    }

    public void setStandingTime(Integer standingTime) {
        this.standingTime = standingTime;
    }
}
