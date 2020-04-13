package com.zgy.learn.bootswaggermailquartzmongo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgy.learn.bootswaggermailquartzmongo.pojo.Student;
import com.zgy.learn.bootswaggermailquartzmongo.util.JSONUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Api(value = "学生Controller", tags = "学生管理的接口")
@Controller
public class StudentController {
    private static List<Student> students = new ArrayList<>();
    private static List<Integer> ids = new ArrayList<>();

    static {
        students.add(new Student(1, "张三", "男", "1年级", "2班"));
        students.add(new Student(2, "李小香", "女", "2年级", "4班"));
        students.add(new Student(3, "王大锤", "男", "3年级", "4班"));
        students.add(new Student(4, "Jason", "男", "1年级", "2班"));
        students.add(new Student(5, "May", "女", "5年级", "1班"));
        students.add(new Student(6, "zou !", "女", "3年级", "3班"));
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);
        ids.add(6);
    }

    @ApiOperation(value = "查询所有的学生", notes = "查询所有的学生", httpMethod = "GET")
    @ApiImplicitParam(name = "")
    @ResponseBody
    @GetMapping("allstudent")
    public String getAllStudents() throws JsonProcessingException {
        return JSONUtils.getJsonFromObject(students);

    }

    @ApiOperation(value = "按照id查询学生的信息", notes = "按照id查询学生", httpMethod = "GET")
    @ApiImplicitParam(name = "id", dataType = "Integer", required = true)
    @ResponseBody
    @GetMapping("getstudentbyid")
    public String getStudentById(@RequestParam("id") Integer id) throws JsonProcessingException {
        Student st = new Student();
        if (null == id || id < 0) {
            return "id is not correct!";
        } else if (ids.contains(id)) {
            for (int i = 0; i < ids.size(); i++) {
                if (students.get(i).getId() == id) {
                    st = students.get(i);
                }
            }
        } else {
            return "there isn't have a student use this id!";
        }
        return JSONUtils.getJsonFromObject(st);
    }
}
