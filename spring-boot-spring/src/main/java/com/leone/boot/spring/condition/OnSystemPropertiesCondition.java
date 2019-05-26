package com.leone.boot.spring.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-26
 **/
public class OnSystemPropertiesCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        Map<String, Object> attributes = annotatedTypeMetadata.getAnnotationAttributes(ConditionalOnSystemProperties.class.getName());

        String propertiesName = attributes.get("name").toString();

        String propertiesValue = attributes.get("value").toString();

        String systemValue = System.getProperty(propertiesName);

        return systemValue.equals(propertiesValue);
    }
}
