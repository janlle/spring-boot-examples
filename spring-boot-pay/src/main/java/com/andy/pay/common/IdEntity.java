package com.andy.pay.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Leone
 * @since 2018-06-03 20:06
 **/
@Data
@MappedSuperclass
@ApiModel("所有实体的父类")
public class IdEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(columnDefinition = "timestamp not null default current_timestamp")
    protected Date createTime;

    @Column(columnDefinition = "timestamp not null default current_timestamp on update current_timestamp")
    protected Date updateTime;

    @Column(columnDefinition = "bigint not null default 0")
    @Version
    protected Long version = 0L;

    public IdEntity() {

    }

    public IdEntity(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        IdEntity idEntity = (IdEntity) obj;
        return id.equals(idEntity.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
