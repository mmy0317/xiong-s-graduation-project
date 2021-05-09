package com.zhao.quiz.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class StudentExcel {
    @ExcelProperty("学生姓名")
    private String studentName;

    @ExcelProperty("学号")
    private String studentAccount;

    @ExcelProperty("性别")
    private String studentGender;

    @ExcelProperty("邮箱")
    private String studentEmail;

    @ExcelProperty("密码")
    private String studentPwd;

    @ExcelProperty("班级")
    private String classeName;

    @ExcelIgnore
    private Integer classeId;

    @ExcelIgnore
    private Integer studentGenderConvert;
}
