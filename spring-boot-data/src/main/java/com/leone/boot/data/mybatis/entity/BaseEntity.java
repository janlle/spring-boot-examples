package com.leone.boot.data.mybatis.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

/**
 * <p>
 *
 * @author leone
 * @since 2024-11-19
 **/
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 乐观锁版本号
     */
    private Integer lockVersion;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BaseEntity.class.getSimpleName() + "[", "]")
          .add("id=" + id)
          .add("deleted=" + deleted)
          .add("lockVersion=" + lockVersion)
          .toString();
    }
}