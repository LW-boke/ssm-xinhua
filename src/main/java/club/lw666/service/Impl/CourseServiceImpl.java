package club.lw666.service.Impl;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.Course;
import club.lw666.domain.PageListRes;
import club.lw666.domain.QueryVo;
import club.lw666.mapper.CourseMapper;
import club.lw666.service.CourseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "courseService")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    /*获取所有的课程  进行分页*/
    @Override
    public PageListRes getCourses(QueryVo queryVo) {
        Page<Course> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        List<Course> courses = courseMapper.getCourses(queryVo);
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(courses);

        //TODO 代办事项

        return pageListRes;
    }

    /*根据班级id 获取课程*/
    @Override
    public List<Course> getClassCourseById(int id) {
        return courseMapper.getClassCourseById(id);
    }

    /*添加课程*/
    @Override
    public AjaxValueRes addCourse(Course course) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            courseMapper.addCourse(course);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("添加课程成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("添加课程失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*编辑课程*/
    @Override
    public AjaxValueRes editCourse(Course course) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            courseMapper.editCourse(course);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("编辑课程成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("编辑课程失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*根据课程id 删除课程*/
    @Override
    public AjaxValueRes delCourse(int id) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            /*根据课程id 解除与班级课程的关系*/
            courseMapper.delCourseWidthByCouId(id);
            courseMapper.delCourse(id);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("删除课程成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("删除课程失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*根据couId 先删除关系*/
    @Override
    public void delClaAndCouByWidCouId(Integer couId) {
        courseMapper.delClaAndCouByWidCouId(couId);
    }

    /*在根据couid 建立和班级的关系*/
    @Override
    public void saveClaAndCouByWidCouId(Integer[] clsId, Integer couId) {
        for (int i = 0; i < clsId.length; i++) {
            int clzId = clsId[i];
            courseMapper.saveClaAndCouByWidCouId(clzId, couId);
        }
    }

    /*根据班级id 获取课程*/
    @Override
    public List<Course> getCourseWidthByClsId(Integer claId) {
        return courseMapper.getCourseWidthByClsId(claId);
    }

    /*根据老师的id查询课程 并且进行分页*/
    @Override
    public PageListRes getCourseWidthTeaId(QueryVo queryVo) {
        Page<Course> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        List<Course> courses = courseMapper.getCourseWidthTeaId(queryVo);
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(courses);
        return pageListRes;
    }


}
