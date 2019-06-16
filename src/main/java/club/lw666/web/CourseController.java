package club.lw666.web;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.Course;
import club.lw666.domain.PageListRes;
import club.lw666.domain.QueryVo;
import club.lw666.service.CourseService;
import club.lw666.service.GradeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CourseController {

    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "gradeService")
    private GradeService gradeService;

    /*跳转到课程页面*/
    @RequestMapping("/course")
    @RequiresPermissions(value = "/course")
    public String course() {
        return "course";
    }

    /*获取所有的课程  进行分页*/
    @RequestMapping("/getCourses")
    @ResponseBody
    public PageListRes getCourses(QueryVo queryVo) {
        System.out.println(queryVo);
        return courseService.getCourses(queryVo);
    }

    /*根据班级id 获取课程   -1 表示获取所有的课程*/
    @RequestMapping("/getClassCourseById")
    @ResponseBody
    public List<Course> getClassCourseById(@RequestParam(value = "id", defaultValue = "-1", required = false) int id) {
        return courseService.getClassCourseById(id);
    }

    /*添加课程*/
    @RequestMapping("/addCourse")
    @ResponseBody
    public AjaxValueRes addCourse(Course course) {
        return courseService.addCourse(course);
    }

    /*编辑课程*/
    @RequestMapping("/editCourse")
    @ResponseBody
    public AjaxValueRes editCourse(Course course) {
        return courseService.editCourse(course);
    }

    /*根据课程id 删除课程*/
    @RequestMapping("/delCourse/{id}")
    @ResponseBody
    public AjaxValueRes delCourse(@PathVariable int id) {
        return courseService.delCourse(id);
    }

    /*获取课程的id 和班级的id*/
    @RequestMapping("/saveCouIdAndClaId")
    @ResponseBody
    public AjaxValueRes saveCouIdAndClaId(@RequestParam(value = "ClsId[]", required = false) Integer[] ClsId, Integer CouId) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            /*根据couId 先删除关系*/
            courseService.delClaAndCouByWidCouId(CouId);
            /*在根据couid 建立和班级的关系*/
            courseService.saveClaAndCouByWidCouId(ClsId, CouId);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("为班级建立课程成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("为班级建立课程失败");
        }
        return ajaxValueRes;
    }

    /*根据班级id 获取课程*/
    @RequestMapping("/getCourseWidthByClsId")
    @ResponseBody
    public List<Course> getCourseWidthByClsId(@RequestParam(value = "claId", required = false) Integer claId) {
        return courseService.getCourseWidthByClsId(claId);
    }

    /*根据老师的id查询课程 并且进行分页*/
    @RequestMapping("/getCourseWidthTeaId")
    @ResponseBody
    public PageListRes getCourseWidthTeaId(QueryVo queryVo) {
        return courseService.getCourseWidthTeaId(queryVo);
    }

}
