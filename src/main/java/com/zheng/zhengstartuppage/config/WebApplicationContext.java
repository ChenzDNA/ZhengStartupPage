package com.zheng.zhengstartuppage.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author : 陈征
 * @date : 2021-10-01 20:09
 */

@Component
public class WebApplicationContext implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebApplicationContext.applicationContext = applicationContext;
    }
}
