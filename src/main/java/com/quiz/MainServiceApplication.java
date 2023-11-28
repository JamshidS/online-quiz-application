package com.quiz;

import com.quiz.config.DBConnectorConfig;

public class MainServiceApplication {
    public static void main(String[] args) {
        DBConnectorConfig.connect();
    }
}