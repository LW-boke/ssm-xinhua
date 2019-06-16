package club.lw666.mapper;

import club.lw666.domain.QueryVo;
import club.lw666.domain.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(Long teaId);

    int insert(Teacher record);

    Teacher selectByPrimaryKey(Long teaId);

    List<Teacher> selectAll(@Param("keyword") String keyword);

    int updateByPrimaryKey(Teacher record);

    /*获取所有的老师进行分页*/
    List<Teacher> getTeachersPage(QueryVo queryVo);

    /*根据班级id和课程id查询老师*/
    List<Teacher> getTeacherWidthByClasIdAndCouId(@Param("clasId") Integer clasId, @Param("couId") Integer couId);

    /*根据职业Id查询Teacher*/
    List<Teacher> getTeacherByProId(@Param("proId") Integer proId);

    /*根据班级id和课程id设置老师id*/
    void insertTeacherIdWidthClaAndCouId(@Param("claId") Integer claId, @Param("couId") Integer couId, @Param("teaId") Integer teaId);

    /*先把与课程关联的数设置成null*/
    void updateClasAndCouWidthByTeaId(Long teaId);
}