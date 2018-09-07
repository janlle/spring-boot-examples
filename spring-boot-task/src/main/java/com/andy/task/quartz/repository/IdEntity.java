package com.andy.task.quartz.repository;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * <P> 所有实体的超类
 *
 * @author Leone
 **/
@MappedSuperclass
public class IdEntity implements Serializable {

    @Column(columnDefinition = "timestamp not null default current_timestamp comment '创建时间'")
    protected Date createTime;

    @Column(columnDefinition = "timestamp not null default current_timestamp on update current_timestamp comment '修改时间'")
    protected Date updateTime;

    @Column(columnDefinition = "tinyint not null default 0 comment '是否删除'")
    protected Boolean deleted = false;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
