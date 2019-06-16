package club.lw666.mapper;

import club.lw666.domain.Profession;
import java.util.List;

public interface ProfessionMapper {
    int deleteByPrimaryKey(Long proId);

    int insert(Profession record);

    Profession selectByPrimaryKey(Long proId);

    List<Profession> selectAll();

    int updateByPrimaryKey(Profession record);
}