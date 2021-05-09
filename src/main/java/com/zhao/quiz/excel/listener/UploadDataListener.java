package com.zhao.quiz.excel.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zhao.quiz.excel.StudentExcel;
import com.zhao.quiz.mapper.ClasseMapper;
import com.zhao.quiz.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UploadDataListener extends AnalysisEventListener<StudentExcel> {

    private static final int BATCH_COUNT = 10;

    List<StudentExcel> excelList = new ArrayList<>();

    private StudentMapper studentMapper;

    private ClasseMapper classeMapper;

    public UploadDataListener(StudentMapper studentMapper, ClasseMapper classeMapper) {
        this.classeMapper = classeMapper;
        this.studentMapper = studentMapper;
    }


    @Override
    public void invoke(StudentExcel studentExcel, AnalysisContext analysisContext) {
        log.info("解析了一条数据:{}", studentExcel);
        if ("女".equals(studentExcel.getStudentGender().trim())) {
            studentExcel.setStudentGenderConvert(0);
        } else if ("男".equals(studentExcel.getStudentGender().trim())) {
            studentExcel.setStudentGenderConvert(1);
        }
        if (studentExcel.getClasseName() != null && studentExcel.getClasseName().trim() != "") {
            Integer classeId = classeMapper.getClasseIdByclasseName(studentExcel.getClasseName());
            studentExcel.setClasseId(classeId);
        }
        excelList.add(studentExcel);
        if (excelList.size() >= BATCH_COUNT) {
            this.saveData();
            excelList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        this.saveData();
        log.info("所有数据组装完成");
    }

    public void saveData() {
        studentMapper.saveExcelData(excelList);
    }
}
