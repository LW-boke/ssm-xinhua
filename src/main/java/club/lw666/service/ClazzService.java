package club.lw666.service;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.Clazz;
import club.lw666.domain.PageListRes;
import club.lw666.domain.QueryVo;

import java.util.List;

public interface ClazzService {
    /*获取clazz数据并且进行分页*/
    PageListRes queryClazzPage(QueryVo queryVo);

    /*添加班级*/
    AjaxValueRes addClass(Clazz clazz);

    /*编辑班级*/
    AjaxValueRes editClass(Clazz clazz);

    /*删除班级*/
    AjaxValueRes delClass(Long id);

    /*根据年级编号获取班级*/
    List<Clazz> getClzzByGraNum(Integer num);

    /*根据课程的id获取班级*/
    List<Clazz> getClassByCourseId(Integer id);
    /*根据专业的id查询班级*/
    List<Clazz> getClassWidByProId(Integer id);
}
