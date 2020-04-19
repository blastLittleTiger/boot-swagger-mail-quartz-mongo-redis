package com.zgy.learn.bootswaggermailquartzmongo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.learn.bootswaggermailquartzmongo.pojo.Student;
import com.zgy.learn.bootswaggermailquartzmongo.service.MongoService;
import com.zgy.learn.bootswaggermailquartzmongo.service.RedisService;
import com.zgy.learn.bootswaggermailquartzmongo.util.JSONUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Api(value = "学生Controller", tags = "学生管理的接口")
@Controller
public class StudentController {
    private static List<Student> students = new ArrayList<>();
    private static List<Integer> ids = new ArrayList<>();
    //Logger log = LoggerFactory.getLogger(StudentController.class); // @Slf4j作用相同
    @Autowired
    MongoService mongoService;

    @Autowired
    private RedisService redisService;

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
            log.error("学生信息有误！{}", stId);
            return "学生信息有误！";
        }
        String result = "";
        // 先从缓存之中查
        Object obj = redisService.get(String.valueOf(stId));
        if (null == obj) {
            log.info("缓存之中没有学生的数据！{}", stId);
        } else {
            // 如果没有，再从数据库之中查
            result = JSONUtils.getJsonFromObject(mongoService.queryById(stId));
            log.info("====>从mongo数据库之中查找学生的数据！{}", stId);
        }
        return result;
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
                log.error("没有学生信息！");
                return "没有学生信息";
            } else {
                List<Integer> stIds = new ArrayList<>();
                for (Student st : list) {
                    stIds.add(st.getStId());
                }
                if (stIds.contains(student.getStId())) {
                    log.warn("学生Id已经存在！{}", student.getStId());
                    return "学生Id已经存在！";
                } else {
                    // 先把数据写入到数据库
                    String str = JSONUtils.getJsonFromObject(mongoService.insert(student));
                    // 然后把数据写入到缓存
                    redisService.set(String.valueOf(student.getStId()), student, 1000);
                    return str;
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
            log.error("学生信息有误！");
            return "学生信息有误！";
        }
        long count = mongoService.delete(stId);
        if (count >= 1) {
            log.info("删除成功！{}", stId);
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
            log.warn("学生信息不允许为空！");
            return "学生信息不允许为空！";
        } else if (student.getStId() == null || null == student.getStName() ||
                null == student.getStGender() || null == student.getStGrade() ||
                null == student.getStClass()) {
            log.warn("学生信息不能为空！");
            return "学生信息不能为空！";
        } else if (student.getStId() <= 0) {
            log.warn("学生信息错误！");
            return "学生信息错误！";
        } else {
            return JSONUtils.getJsonFromObject(
                    mongoService.update(student) == 1L ? "更新成功" : "更新出错");
        }
    }
}
