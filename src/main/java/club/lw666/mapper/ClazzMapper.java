package club.lw666.mapper;

import club.lw666.domain.Clazz;
import club.lw666.domain.QueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClazzMapper {
    int deleteByPrimaryKey(Long claId);

    int insert(Clazz record);

    Clazz selectByPrimaryKey(Long claId);

    List<Clazz> selectAll();

    int updateByPrimaryKey(Clazz record);

    /**
     * 获取clazz数据并且进行分页
     *
     * @param queryVo {@link QueryVo}
     * @return {@code List<Clazz>}
     */
    List<Clazz> queryClazzPage(QueryVo queryVo);

    /**
     * 根据年级编号获取班级
     *
     * @param num 班号
     * @return
     */
    List<Clazz> getClzzByGraNum(Integer num);

    /*根据课程的id获取班级*/
    List<Clazz> getClassByCourseId(@Param("id") Integer id);

    /*根据专业的id查询班级*/
    List<Clazz> getClassWidByProId(@Param("id") Integer id);

    /*根据班级id获取班级的专业id */
    Long getClzzProWidthByClzzId(Long claId);

    /*根据课程id 清空关联的班级*/
    void updateClazzWidthByTeaId(Long teaId);
}
