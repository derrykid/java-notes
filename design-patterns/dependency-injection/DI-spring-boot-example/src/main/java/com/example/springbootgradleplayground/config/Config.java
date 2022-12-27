package com.example.springbootgradleplayground.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.example.springbootgradleplayground.model"})
public class Config {
}
