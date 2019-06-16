package club.lw666.web;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.Clazz;
import club.lw666.domain.PageListRes;
import club.lw666.domain.QueryVo;
import club.lw666.service.ClazzService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ClassController {
    @Resource(name = "clazzService")
    private ClazzService clazzService;

    /*跳转到clazz页面*/
    @RequestMapping("/clazz")
    @RequiresPermissions(value = "/clazz")
    public String clazz() {
        return "class";
    }

    /*获取clazz数据并且进行分页*/
    @RequestMapping("/clazzPage")
    @ResponseBody
    public PageListRes clazzPage(QueryVo queryVo) {
        return clazzService.queryClazzPage(queryVo);
    }

    /*添加班级*/
    @RequestMapping("/addClass")
    @ResponseBody
    public AjaxValueRes addClass(Clazz clazz) {
        return clazzService.addClass(clazz);
    }


    /*编辑班级*/
    @RequestMapping("/editClass")
    @ResponseBody
    public AjaxValueRes editClass(Clazz clazz) {
        return clazzService.editClass(clazz);
    }

    /*删除班级*/
    @RequestMapping("/delClass/{id}")
    @ResponseBody
    public AjaxValueRes delClass(@PathVariable Long id) {
        return clazzService.delClass(id);
    }

    /*根据年级编号获取班级*/
    @RequestMapping("/getClzzByGraNum")
    @ResponseBody
    public List<Clazz> getClzzByGraNum(Integer num) {
        return clazzService.getClzzByGraNum(num);
    }

    /*根据课程的id获取班级*/
    @RequestMapping("/getClassByCourseId")
    @ResponseBody
    public List<Clazz> getClassByCourseId(@RequestParam(value = "id", required = false) Integer id) {
        return clazzService.getClassByCourseId(id);
    }

    /*根据专业的id查询班级*/
    @RequestMapping("/getClassWidByProId")
    @ResponseBody
    public List<Clazz> getClassWidByProId(@RequestParam(value = "id", required = false) Integer id) {
        return clazzService.getClassWidByProId(id);
    }
}
