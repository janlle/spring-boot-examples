package com.leone.boot.learn.event;

import org.springframework.context.ApplicationListener;

/**
 * <p>
 *
 * @author leone
 * @since 2019-07-04
 **/
public class EmailListener implements ApplicationListener<EmailEvent> {

    @Override
    public void onApplicationEvent(EmailEvent event) {
        EmailInfo source = (EmailInfo) event.getSource();
        System.out.printf("email: %s content: %s%n", source.getAddress(), source.getText());
    }

}
