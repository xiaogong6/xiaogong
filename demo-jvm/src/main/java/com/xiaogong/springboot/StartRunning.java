package com.xiaogong.springboot;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Program: xiaogong
 * @Description:
 * @Author: xiongke
 * @Create: 2024-06-04
 */
@Configuration
public class StartRunning implements
        ApplicationContextAware,
        BeanFactoryAware,
        InitializingBean,
        SmartLifecycle,
        BeanNameAware,
        ApplicationListener<ContextRefreshedEvent>,
        CommandLineRunner,
        SmartInitializingSingleton {

    private final Logger logger = LoggerFactory.getLogger(StartRunning.class);

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        logger.info("启动顺序: setBeanFactory,beanFactory:{}", "xiaogong");
    }

    @Override
    public void setBeanName(String name) {
        logger.info("启动顺序: name:{}", name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("启动顺序: afterPropertiesSet");
    }

    @Override
    public void afterSingletonsInstantiated() {
        logger.info("启动顺序: afterSingletonsInstantiated");
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("启动顺序: run");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("启动顺序: setApplicationContext");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("启动顺序: onApplicationEvent");
    }

    @Override
    public void start() {
        logger.info("启动顺序: start");
    }

    @Override
    public void stop() {
        logger.info("启动顺序: stop");
    }

    @Override
    public boolean isRunning() {
        logger.info("启动顺序: isRunning");
        return false;
    }

    @PostConstruct
    public void postConstruct() {
        logger.error("启动顺序:post-construct");
    }

    public void initMethod() {
        logger.error("启动顺序:init-method");
    }

}
