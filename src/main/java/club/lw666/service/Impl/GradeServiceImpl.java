package club.lw666.service.Impl;

import club.lw666.domain.Grade;
import club.lw666.mapper.GradeMapper;
import club.lw666.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "gradeService")
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    /*获取所有的年级*/
    @Override
    public List<Grade> getGrades() {
        return gradeMapper.selectAll();
    }
}
