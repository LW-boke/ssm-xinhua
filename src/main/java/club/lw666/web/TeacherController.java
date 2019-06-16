package club.lw666.web;

import club.lw666.domain.*;
import club.lw666.service.ProfessionService;
import club.lw666.service.TeacherService;
import javafx.scene.chart.ValueAxis;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TeacherController {
    @Resource(name = "teacherService")
    private TeacherService teacherService;

    @Resource(name = "professionService")
    private ProfessionService professionService;

    /*跳转到老师界面*/
    @RequestMapping("/teacher")
    @RequiresPermissions(value = "/teacher")
    public String getProfessions(Model model) {
        List<Profession> professions = professionService.getProfessions();
        model.addAttribute("professionList", professions);
        return "teacher";
    }

    /*获取所有的老师*/
    @RequestMapping("/getTeachers")
    @ResponseBody
    public List<Teacher> getTeachers(@RequestParam(value = "q", required = false) String keyword) {
        return teacherService.getTeachers(keyword);
    }

    /*获取所有的老师进行分页*/
    @RequestMapping("/teachersPage")
    @ResponseBody
    public PageListRes teachersPage(QueryVo queryVo) {
        System.out.println(queryVo);
        return teacherService.teachersPage(queryVo);
    }

    /*根据职业Id查询老师*/
    @RequestMapping("/getTeacherByProId")
    @ResponseBody
    public List<Teacher> getTeacherByProId(@RequestParam(value = "proId", required = false) Integer proId) {
        return teacherService.getTeacherByProId(proId);
    }

    /*根据班级id和课程id查询老师*/
    @RequestMapping("/getTeacherWidthByClasIdAndCouId")
    @ResponseBody
    public List<Teacher> getTeacherWidthByClasIdAndCouId(
            @RequestParam(value = "ClasId", required = false) Integer clasId,
            @RequestParam(value = "CouId", required = false) Integer couId) {
        return teacherService.getTeacherWidthByClasIdAndCouId(clasId, couId);
    }

    /*根据班级id和课程id设置老师id*/
    @RequestMapping("/insertTeacherIdWidthClaAndCouId")
    @ResponseBody
    public AjaxValueRes insertTeacherIdWidthClaAndCouId(Integer claId, Integer couId, Integer teaId) {
        return teacherService.insertTeacherIdWidthClaAndCouId(claId, couId, teaId);
    }

    /*添加老师*/
    @RequestMapping("/addTeacher")
    @ResponseBody
    public AjaxValueRes addTeacher(Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    /*编辑老师*/
    @RequestMapping("/editTeacher")
    @ResponseBody
    public AjaxValueRes editTeacher(Teacher teacher) {
        return teacherService.editTeacher(teacher);
    }

    /*删除课程*/
    @RequestMapping("/delTeacher/{teaId}")
    @ResponseBody
    public AjaxValueRes delTeacher(@PathVariable Long teaId) {
        return teacherService.delTeacher(teaId);
    }
}
