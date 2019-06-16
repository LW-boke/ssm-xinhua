package club.lw666.service;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.PageListRes;
import club.lw666.domain.QueryVo;
import club.lw666.domain.Student;

import java.util.List;

public interface StudentService {
    /*根据班级的id查询学生*/
    PageListRes getStudentByClsId(QueryVo queryVo);

    /*获取所有的学生 进行分页*/
    PageListRes getStudents(QueryVo queryVo);

    /*添加学生*/
    AjaxValueRes addStudent(Student student);

    /*编辑学生*/
    AjaxValueRes editStudent(Student student);

    /*根据id删除学生*/
    AjaxValueRes delStudent(Long stuId);

    /*查询所有的学生*/
    List<Student> getStudents();
}
