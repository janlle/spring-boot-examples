package com.andy.jms.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-29
 **/
@Data
@Entity
@Table(name = "t_broker_message_log")
public class BrokerMessageLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brokerMessageLog;


}
