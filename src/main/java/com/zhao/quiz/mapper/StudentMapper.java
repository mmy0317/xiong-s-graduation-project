package com.zhao.quiz.mapper;

import com.zhao.quiz.domain.Student;
import com.zhao.quiz.excel.StudentExcel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentMapper {
    List<Student> queryAll();

    Student check(Student student);

    @Select("select count(*) from student")
    int queryCOuntALlstu();

    void AddStudent(Student student);

    Student getStudentById(Integer id);

    void EditStudent(Student student);

    @Delete("delete from student where student_id=#{id}")
    void deleteById(Integer id);

    List<StudentExcel> getExcelALl();

    void saveExcelData(@Param("studentExcel") List<StudentExcel> studentExcel);
}
