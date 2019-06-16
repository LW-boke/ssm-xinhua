package club.lw666.service.Impl;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.PageListRes;
import club.lw666.domain.QueryVo;
import club.lw666.domain.Student;
import club.lw666.mapper.ClazzMapper;
import club.lw666.mapper.StudentMapper;
import club.lw666.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    /*根据班级的id查询学生*/
    @Override
    public PageListRes getStudentByClsId(QueryVo queryVo) {
        /*进行分页*/
        Page<Student> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        /*获取数据*/
        List<Student> students = studentMapper.getStudentByClsId(queryVo);
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(students);
        return pageListRes;
    }

    /*获取所有的学生 进行分页*/
    @Override
    public PageListRes getStudents(QueryVo queryVo) {
        /*使用mybatis插件进行分页*/
        Page<Student> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        /*查找所有的学生*/
        List<Student> students = studentMapper.getStudents(queryVo);
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(students);
        return pageListRes;
    }

    /*添加学生*/
    @Override
    public AjaxValueRes addStudent(Student student) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            studentMapper.insert(student);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("添加学生成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("添加学生失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*编辑学生*/
    @Override
    public AjaxValueRes editStudent(Student student) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            /*获取班级的专业id */
            Long cla_proId = clazzMapper.getClzzProWidthByClzzId(student.getClazz().getClaId());
            if (cla_proId == student.getProfession().getProId()) {
                ajaxValueRes.setSuccess(true);
                ajaxValueRes.setMrg("编辑学生成功");
                studentMapper.updateByPrimaryKey(student);
            } else {
                ajaxValueRes.setSuccess(false);
                ajaxValueRes.setMrg("编辑失败，编辑的专业不在这个班级，请重新编辑");
            }
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("编辑学生失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*根据id删除学生*/
    @Override
    public AjaxValueRes delStudent(Long stuId) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            studentMapper.deleteByPrimaryKey(stuId);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("删除学生成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("删除学生失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*查询所有的学生*/
    @Override
    public List<Student> getStudents() {
        return studentMapper.selectAll();
    }
}
