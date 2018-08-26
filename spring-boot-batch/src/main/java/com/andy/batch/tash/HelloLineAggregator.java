package com.andy.batch.tash;

import org.springframework.batch.item.file.transform.LineAggregator;

public class HelloLineAggregator implements LineAggregator<DeviceCommand> {

    @Override
    public String aggregate(DeviceCommand deviceCommand) {

        StringBuffer sb = new StringBuffer();
        sb.append(deviceCommand.getId());
        sb.append(",");
        sb.append(deviceCommand.getStatus());
        return sb.toString();

    }
}