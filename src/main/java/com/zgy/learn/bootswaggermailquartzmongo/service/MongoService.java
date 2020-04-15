package com.zgy.learn.bootswaggermailquartzmongo.service;

import com.zgy.learn.bootswaggermailquartzmongo.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Student> queryAll() {
        return mongoTemplate.findAll(Student.class);
    }

    public void queryById(Integer stId) {

    }

    public void insert() {

    }

    public void delete() {
    }

    public void update() {

    }




}
