package club.lw666.web;

import club.lw666.domain.Grade;
import club.lw666.service.GradeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class GradeController {
    @Resource(name = "gradeService")
    private GradeService gradeService;

    /*跳转到年级页面*/
    @RequestMapping("/grade")
    @RequiresPermissions(value = "/grade")
    public String grade(Model model) {
        /*获取所有的年级*/
        List<Grade> grades = gradeService.getGrades();
        model.addAttribute("grades", grades);
        return "grade";
    }



    /*获取所有的年级*/
    @RequestMapping("/getGrades")
    @ResponseBody
    public List<Grade> getGrades() {
        return gradeService.getGrades();
    }
}
