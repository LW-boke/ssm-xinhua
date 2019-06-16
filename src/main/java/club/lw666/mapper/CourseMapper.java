package club.lw666.mapper;

import club.lw666.domain.Course;
import club.lw666.domain.QueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    /*根据班级id 获取课程*/
    List<Course> getClassCourseById(@Param("id") int id);

    /*根据班级id 删除所有的课程*/
    void delCourseWidthByClsId(Long id);

    /*获取所有的课程  进行分页*/
    List<Course> getCourses(QueryVo queryVo);

    /*添加课程*/
    void addCourse(Course course);

    /*编辑课程*/
    void editCourse(Course course);

    /*根据课程id 删除课程*/
    void delCourse(int id);

    /*根据课程id 解除与班级课程的关系*/
    void delCourseWidthByCouId(int id);

    /*根据couId 先删除关系*/
    void delClaAndCouByWidCouId(Integer couId);

    /*在根据couid 建立和班级的关系*/
    void saveClaAndCouByWidCouId(@Param("clzId") Integer clzId, @Param("couId") Integer couId);

    /*根据班级id 获取课程*/
    List<Course> getCourseWidthByClsId(@Param("claId") Integer claId);

    /*根据老师的id查询课程 并且进行分页*/
    List<Course> getCourseWidthTeaId(QueryVo queryVo);
}