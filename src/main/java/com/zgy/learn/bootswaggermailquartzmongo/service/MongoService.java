package com.zgy.learn.bootswaggermailquartzmongo.service;

import com.zgy.learn.bootswaggermailquartzmongo.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    // 查询所有的学生
    public List<Student> queryAll() {
        return mongoTemplate.findAll(Student.class);
    }

    // 按照ID查询
    public Student queryById(Integer stId) {
        if (stId <=0 || stId==null){
            throw new RuntimeException("stId有异常！");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("stId").is(stId));
        return mongoTemplate.findOne(query, Student.class);

    }

    public void insert() {

    }

    public void delete() {
    }

    public void update() {

    }


}
