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
        EmailSource source = (EmailSource) event.getSource();
        System.out.println("email address: " + source.getAddress() + " email text: " + source.getText());
    }

}
