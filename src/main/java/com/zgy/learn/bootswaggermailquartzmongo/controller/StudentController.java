package com.zgy.learn.bootswaggermailquartzmongo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.learn.bootswaggermailquartzmongo.pojo.Student;
import com.zgy.learn.bootswaggermailquartzmongo.service.MongoService;
import com.zgy.learn.bootswaggermailquartzmongo.util.JSONUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Api(value = "学生Controller", tags = "学生管理的接口")
@Controller
public class StudentController {
    private static List<Student> students = new ArrayList<>();
    private static List<Integer> ids = new ArrayList<>();
    @Autowired
    MongoService mongoService;

    // 按照所有的学生
    @ApiOperation(value = "查询所有的学生", notes = "查询所有的学生", httpMethod = "GET")
    @ApiImplicitParam(name = "")
    @ResponseBody
    @GetMapping("allstudent")
    public String getAllStudents() throws JsonProcessingException {
        return JSONUtils.getJsonFromObject(mongoService.queryAll());

    }

    // 按照ID查询学生
    @ApiOperation(value = "按照stId查询学生的信息", notes = "按照id查询学生", httpMethod = "GET")
    @ApiImplicitParam(name = "stId", dataType = "Integer", required = true)
    @ResponseBody
    @GetMapping("getstudentbyid")
    public String getStudentById(@RequestParam("stId") Integer stId) throws JsonProcessingException {
        if (null == stId) {
            return "学生信息有误！";
        }
        return JSONUtils.getJsonFromObject(mongoService.queryById(stId));
    }

    // 添加一个新的学生
    @ApiOperation(value = "添加一个学生", notes = "添加一个学生", httpMethod = "POST")
    @ApiImplicitParam(name = "student", dataTypeClass = Student.class, required = true)
    @PostMapping("addstudent")
    @ResponseBody
    public String addStudent(Student student) throws JsonProcessingException {
        if (null == student || null == student.getStId()) {
            return "学生信息有误！";
        } else {
            List<Student> list = mongoService.queryAll();
            if (list.size() <= 0) {
                return "没有学生信息";
            } else {
                List<Integer> stIds = new ArrayList<>();
                for (Student st : list) {
                    stIds.add(st.getStId());
                }
                if (stIds.contains(student.getStId())) {
                    return "学生Id已经存在！";
                } else {
                    return JSONUtils.getJsonFromObject(mongoService.insert(student));
                }
            }
        }
    }

    // 删除一个学生
    @ApiOperation(value = "删除一个学生", notes = "删除一个学生", httpMethod = "POST")
    @ApiImplicitParam(name = "stId", dataTypeClass = Integer.class, required = true)
    @PostMapping("deletestudentbyid")
    @ResponseBody
    public String deleteStudentById(@RequestParam("stId") Integer stId) throws JsonProcessingException {
        if (null == stId) {
            return "学生信息有误！";
        }
        long count = mongoService.delete(stId);
        if (count >= 1) {
            return JSONUtils.getJsonFromObject("删除成功！");
        }
        return JSONUtils.getJsonFromObject("学生不存在！");

    }

    // 更新一个学生
    @ApiOperation(value = "更新学生信息", notes = "更新学生信息", httpMethod = "POST")
    @ApiImplicitParam(name = "student", dataTypeClass = Student.class, required = true)
    @ResponseBody
    @RequestMapping("updateStudentById")
    public String updateStudentById(Student student) throws JsonProcessingException {
        if (null == student) {
            return "信息不允许为空！";
        } else if (student.getStId() == null || null == student.getStName() ||
                null == student.getStGender() || null == student.getStGrade() ||
                null == student.getStClass()) {
            return "学生信息不能为空！";
        } else if (student.getStId() <= 0) {
            return "学生信息错误！";
        } else {
            return JSONUtils.getJsonFromObject(
                    mongoService.update(student) == 1L ? "更新成功" : "更新出错");
        }
    }
}
