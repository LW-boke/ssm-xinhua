package club.lw666.service;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.PageListRes;
import club.lw666.domain.QueryVo;
import club.lw666.domain.Teacher;

import java.util.List;

public interface TeacherService {
    /*获取所有的老师*/
    List<Teacher> getTeachers(String keyword);

    /*获取所有的老师进行分页*/
    PageListRes teachersPage(QueryVo queryVo);

    /*根据职业Id查询Teacher*/
    List<Teacher> getTeacherByProId(Integer proId);

    /*根据班级id和课程id查询老师*/
    List<Teacher> getTeacherWidthByClasIdAndCouId(Integer clasId, Integer couId);

    /*根据班级id和课程id设置老师id*/
    AjaxValueRes insertTeacherIdWidthClaAndCouId(Integer claId, Integer couId, Integer teaId);

    /*添加老师*/
    AjaxValueRes addTeacher(Teacher teacher);

    /*编辑老师*/
    AjaxValueRes editTeacher(Teacher teacher);

    /*删除课程*/
    AjaxValueRes delTeacher(Long teaId);
}
