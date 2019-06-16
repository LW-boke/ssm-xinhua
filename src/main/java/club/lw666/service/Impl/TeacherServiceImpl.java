package club.lw666.service.Impl;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.PageListRes;
import club.lw666.domain.QueryVo;
import club.lw666.domain.Teacher;
import club.lw666.mapper.ClazzMapper;
import club.lw666.mapper.TeacherMapper;
import club.lw666.service.TeacherService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "teacherService")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    /*获取所有的老师*/
    @Override
    public List<Teacher> getTeachers(String keyword) {
        return teacherMapper.selectAll(keyword);
    }

    /*获取所有的老师进行分页*/
    @Override
    public PageListRes teachersPage(QueryVo queryVo) {
        Page<Teacher> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        List<Teacher> teacherList = teacherMapper.getTeachersPage(queryVo);
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(teacherList);
        return pageListRes;
    }

    /*根据职业Id查询Teacher*/
    @Override
    public List<Teacher> getTeacherByProId(Integer proId) {
        return teacherMapper.getTeacherByProId(proId);
    }

    /*根据班级id和课程id查询老师*/
    @Override
    public List<Teacher> getTeacherWidthByClasIdAndCouId(Integer clasId, Integer couId) {
        return teacherMapper.getTeacherWidthByClasIdAndCouId(clasId, couId);
    }

    /*根据班级id和课程id设置老师id*/
    @Override
    public AjaxValueRes insertTeacherIdWidthClaAndCouId(Integer claId, Integer couId, Integer teaId) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            teacherMapper.insertTeacherIdWidthClaAndCouId(claId, couId, teaId);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("更新老师成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("更新老师失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*添加老师*/
    @Override
    public AjaxValueRes addTeacher(Teacher teacher) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            teacherMapper.insert(teacher);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("添加老师成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("添加老师失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    @Override
    public AjaxValueRes editTeacher(Teacher teacher) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            teacherMapper.updateByPrimaryKey(teacher);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("编辑老师成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("编辑老师失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*删除课程*/
    @Override
    public AjaxValueRes delTeacher(Long teaId) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            /*根据课程id 清空关联的班级*/
            clazzMapper.updateClazzWidthByTeaId(teaId);
            teacherMapper.updateClasAndCouWidthByTeaId(teaId);
            /*先把与课程关联的数设置成null*/
            teacherMapper.updateClasAndCouWidthByTeaId(teaId);
            teacherMapper.deleteByPrimaryKey(teaId);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("删除老师成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("删除老师失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }


}
