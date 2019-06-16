package club.lw666.mapper;

import club.lw666.domain.Grade;

import java.util.List;

public interface GradeMapper {
    int deleteByPrimaryKey(Integer graNum);

    int insert(Grade record);

    Grade selectByPrimaryKey(Integer graNum);

    int updateByPrimaryKey(Grade record);

    /*获取所有的年级*/
    List<Grade> selectAll();
}