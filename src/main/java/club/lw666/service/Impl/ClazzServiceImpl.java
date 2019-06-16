package club.lw666.service.Impl;

import club.lw666.domain.AjaxValueRes;
import club.lw666.domain.Clazz;
import club.lw666.domain.PageListRes;
import club.lw666.domain.QueryVo;
import club.lw666.mapper.ClazzMapper;
import club.lw666.mapper.CourseMapper;
import club.lw666.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "clazzService")
@Transactional
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private CourseMapper courseMapper;

    /*获取clazz数据并且进行分页*/
    @Override
    public PageListRes queryClazzPage(QueryVo queryVo) {
        Page<Clazz> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        List<Clazz> clazzList = clazzMapper.queryClazzPage(queryVo);
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(clazzList);
        return pageListRes;
    }

    /*添加班级*/
    @Override
    public AjaxValueRes addClass(Clazz clazz) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            clazzMapper.insert(clazz);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("保存成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("保存失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*编辑班级*/
    @Override
    public AjaxValueRes editClass(Clazz clazz) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            clazzMapper.updateByPrimaryKey(clazz);
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("编辑成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("班级失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    @Override
    public AjaxValueRes delClass(Long id) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        try {
            /*删除关系*/
            courseMapper.delCourseWidthByClsId(id);
            /*删除班级*/
            clazzMapper.deleteByPrimaryKey(id);
            /*删除课程*/
            ajaxValueRes.setSuccess(true);
            ajaxValueRes.setMrg("删除成功");
        } catch (Exception e) {
            ajaxValueRes.setSuccess(false);
            ajaxValueRes.setMrg("删除失败");
            e.printStackTrace();
        }
        return ajaxValueRes;
    }

    /*根据年级编号获取班级*/
    @Override
    public List<Clazz> getClzzByGraNum(Integer num) {
        return clazzMapper.getClzzByGraNum(num);
    }

    /*根据课程的id获取班级*/
    @Override
    public List<Clazz> getClassByCourseId(Integer id) {
        return clazzMapper.getClassByCourseId(id);
    }
    /*根据专业的id查询班级*/
    @Override
    public List<Clazz> getClassWidByProId(Integer id) {
        return clazzMapper.getClassWidByProId(id);
    }
}
