package com.me.challenge.infra;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Component
public class DefaultApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * This method will recovery a Spring {@link Bean} of a given class as long as there is only 1 {@link Bean} of class type
     *
     * @param clazz class of implementation to recovery Spring Context
     * @param <T> type of class to recovery from Spring Context
     *
     * @return bean recovered from Spring Context
     */
    public static <T> T getBean(final Class<T> clazz) {
        return getBean(null, clazz);
    }

    /**
     * This method will recovery a Spring {@link Bean} of a given class and name
     *
     * @param beanName name of specific bean to recovery from Spring Context
     * @param clazz class of implementation to recovery Spring Context
     * @param <T> type of class to recovery from Spring Context
     *
     * @return bean recovered from Spring Context
     */
    public static <T> T getBean(final String beanName, final Class<T> clazz) {
        return ObjectUtils.isEmpty(beanName) ? applicationContext.getBean(clazz) : applicationContext.getBean(beanName, clazz);
    }

    @Override
    public synchronized void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
