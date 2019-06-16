package club.lw666.service.Impl;

import club.lw666.domain.Profession;
import club.lw666.mapper.ProfessionMapper;
import club.lw666.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "professionService")
public class ProfessionServiceImpl implements ProfessionService {
    @Autowired
    private ProfessionMapper professionMapper;

    /*查询所有的职业*/
    @Override
    public List<Profession> getProfessions() {
        return professionMapper.selectAll();
    }
}
