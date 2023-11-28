package com.quiz;

import com.quiz.config.DBConnectorConfig;
import com.quiz.repository.CreateTable;

public class MainServiceApplication {
    public static void main(String[] args) {
        DBConnectorConfig.connect();
        CreateTable.createPersonTable();
    }
}