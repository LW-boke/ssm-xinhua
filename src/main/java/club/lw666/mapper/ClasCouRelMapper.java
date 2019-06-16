package club.lw666.mapper;

import club.lw666.domain.ClasCouRel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClasCouRelMapper {
    int deleteByPrimaryKey(@Param("claId") Long claId, @Param("couId") Long couId);

    int insert(ClasCouRel record);

    ClasCouRel selectByPrimaryKey(@Param("claId") Long claId, @Param("couId") Long couId);

    List<ClasCouRel> selectAll();

    int updateByPrimaryKey(ClasCouRel record);
}