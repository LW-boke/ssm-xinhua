package club.lw666.service;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.Course;
import club.lw666.domain.PageListRes;
import club.lw666.domain.QueryVo;

import java.util.List;

public interface CourseService {
    /*获取所有的课程  进行分页*/
    PageListRes getCourses(QueryVo queryVo);

    /*根据班级id 获取课程*/
    List<Course> getClassCourseById(int id);

    /*添加课程*/
    AjaxValueRes addCourse(Course course);

    /*编辑课程*/
    AjaxValueRes editCourse(Course course);

    /*根据课程id 删除课程*/
    AjaxValueRes delCourse(int id);

    /*根据couId 先删除关系*/
    void delClaAndCouByWidCouId(Integer couId);

    /*在根据couid 建立和班级的关系*/
    void saveClaAndCouByWidCouId(Integer[] clsId, Integer couId);

    /*根据班级id 获取课程*/
    List<Course> getCourseWidthByClsId(Integer claId);
    /*根据老师的id查询课程 并且进行分页*/

    PageListRes getCourseWidthTeaId(QueryVo queryVo);
}
