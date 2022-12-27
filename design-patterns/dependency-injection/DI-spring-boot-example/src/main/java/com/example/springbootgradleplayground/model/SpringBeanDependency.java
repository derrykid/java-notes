package com.example.springbootgradleplayground.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringBeanDependency {
    @Autowired
    private Writer writer;

    public void run() {
        writer.write();
    }
}
