package club.lw666.mapper;

import club.lw666.domain.QueryVo;
import club.lw666.domain.Student;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Long stuId);

    /*添加学生*/
    int insert(Student record);

    Student selectByPrimaryKey(Long stuId);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);

    /*根据班级的id查询学生*/
    List<Student> getStudentByClsId(QueryVo queryVo);

    /*根据班级id查出学生有多少人*/
    Integer getStudentByClsNum(Long id);

    /*获取所有的学生 进行分页*/
    List<Student> getStudents(QueryVo queryVo);

    /*查询所有的学生*/
    List<Student> getStudents();
}