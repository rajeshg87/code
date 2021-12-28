package com.charter.dataprocessor;

public class TestConfig {
    public static void sleep(Integer sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
