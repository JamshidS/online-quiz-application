package com.quiz;

import com.quiz.config.DBConnectorConfig;
import com.quiz.model.Person;
import com.quiz.repository.CreateTable;
import com.quiz.repository.PersonRepository;
import com.quiz.repository.UserRepository;

public class MainServiceApplication {
    public static void main(String[] args) {
        DBConnectorConfig.connect();

        Person person = new Person();
        person.setName("Emre Yuce");
        // you can use this to try how to fetch data from database
        PersonRepository personRepository = new PersonRepository();
        personRepository.update(person,1);
    }
}