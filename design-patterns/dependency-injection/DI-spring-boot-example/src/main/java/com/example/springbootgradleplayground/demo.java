package com.example.springbootgradleplayground;

import com.example.springbootgradleplayground.config.Config;
import com.example.springbootgradleplayground.model.SpringBeanDependency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class demo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        SpringBeanDependency beanDependency =
                context.getBean(SpringBeanDependency.class);
        beanDependency.run();

        context.close();
    }
}
