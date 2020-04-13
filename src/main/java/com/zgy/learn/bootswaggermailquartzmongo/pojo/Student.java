package com.zgy.learn.bootswaggermailquartzmongo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "学生的实体类", description = "学生的实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @ApiModelProperty(value = "学生id", notes = "学生id")
    private int id;
    @ApiModelProperty(value = "学生姓名", notes = "学生姓名")
    private String name;
    @ApiModelProperty(value = "学生性别", notes = "学生性别")
    private String gender;
    @ApiModelProperty(value = "学生姓名", notes = "学生姓名")
    private String grade;
    @ApiModelProperty(value = "学生班级", notes = "学生班级")
    private String classes;
}
