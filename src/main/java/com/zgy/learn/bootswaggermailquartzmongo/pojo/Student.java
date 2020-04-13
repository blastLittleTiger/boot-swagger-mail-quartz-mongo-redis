package com.zgy.learn.bootswaggermailquartzmongo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private int id;
    private String name;
    private String gender;
    // 年级
    private String grade;
    // 班级
    private String classes;
}
