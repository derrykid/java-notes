package com.example.springbootgradleplayground.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CoolWriter implements Writer{
    @Override
    public void write() {
        log.info("Hi, I'm cool writer");
    }
}
