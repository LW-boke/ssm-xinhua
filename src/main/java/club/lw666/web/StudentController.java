package club.lw666.web;

import club.lw666.domain.*;
import club.lw666.service.GradeService;
import club.lw666.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Controller

public class StudentController {
    @Resource(name = "studentService")
    private StudentService studentService;

    @Resource(name = "gradeService")
    private GradeService gradeService;

    @RequestMapping("/student")
    @RequiresPermissions(value = "/student")
    public String student(Model model) {
        /*获取所有的年级*/
        List<Grade> grades = gradeService.getGrades();
        model.addAttribute("grades", grades);
        return "/student";
    }

    /*根据班级的id查询学生*/
    @RequestMapping("/getStudentByClsId")
    @ResponseBody
    public PageListRes getStudentByClsId(QueryVo queryVo) {
        return studentService.getStudentByClsId(queryVo);
    }

    /*获取所有的学生 进行分页*/
    @RequestMapping("/getStudents")
    @ResponseBody
    public PageListRes getStudents(QueryVo queryVo) {
        return studentService.getStudents(queryVo);
    }

    /*添加学生*/
    @RequestMapping("/addStudent")
    @ResponseBody
    @RequiresPermissions(value = "addStudent")
    public AjaxValueRes addStudent(Student student) {
        return studentService.addStudent(student);
    }

    /*编辑学生*/
    @RequestMapping("/editStudent")
    @ResponseBody
    public AjaxValueRes editStudent(Student student) {
        return studentService.editStudent(student);
    }

    /*根据id删除学生*/
    @RequestMapping("/delStudent/{stuId}")
    @ResponseBody
    public AjaxValueRes delStudent(@PathVariable Long stuId) {
        return studentService.delStudent(stuId);
    }

    /*下载学生的Excel*/
    @RequestMapping("/downloadStudentExcel")
    public void downloadStudentExcel(HttpServletResponse response) throws IOException {
        /*设置文件名和响应头  以附件的形式下载*/
        String fileName = new String("学生信息.xls".getBytes("utf-8"), "iso8859-1");
        response.setHeader("content-Disposition", "attachment;filename=" + fileName);
        /*查询所有的学生*/
        List<Student> students = studentService.getStudents();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("学生信息");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("学生编号");
        row.createCell(1).setCellValue("学生名称");
        row.createCell(2).setCellValue("性别");
        row.createCell(3).setCellValue("年龄");
        row.createCell(4).setCellValue("入校时间");
        row.createCell(5).setCellValue("联系方式");
        row.createCell(6).setCellValue("家庭地址");
        row.createCell(7).setCellValue("专业名称");
        row.createCell(8).setCellValue("所在班级");
        row.createCell(9).setCellValue("是否在校");
        HSSFRow rows;
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            rows = sheet.createRow(i + 1);
            /*设置编号*/
            rows.createCell(0).setCellValue(student.getStuId());
            /*设置姓名*/
            rows.createCell(1).setCellValue(student.getStuName());
            /*设置性别*/
            if (student.getStuSex()) {
                rows.createCell(2).setCellValue("男");
            } else {
                rows.createCell(2).setCellValue("女");
            }
            /*出生日期*/
            rows.createCell(3).setCellValue(DateFormatString(student.getStuAge(), "yyyy-MM-dd"));
            /*入校日期*/
            rows.createCell(4).setCellValue(DateFormatString(student.getStuEnrol(), "yyyy-MM-dd"));
            /*联系方式*/
            rows.createCell(5).setCellValue(student.getStuPhone());
            /*家庭地址*/
            rows.createCell(6).setCellValue(student.getStuSite());
            /*专业名称*/
            if (student.getProfession() != null) {
                rows.createCell(7).setCellValue(student.getProfession().getProName());
            } else {
                rows.createCell(7).setCellValue("");
            }
            /*所在班级*/
            if (student.getClazz() != null) {
                rows.createCell(8).setCellValue(student.getClazz().getClaName());
            } else {
                rows.createCell(8).setCellValue("");
            }
            /*是否在校*/
            if (student.getStuProgress()) {
                rows.createCell(9).setCellValue("在校");
            } else {
                rows.createCell(9).setCellValue("离校");
            }
        }
        workbook.write(response.getOutputStream());
    }

    /*下载学生上传Excel的模板*/
    @RequestMapping("/downloadStudentExcelTml")
    public void downloadStudentExcelTml(HttpServletRequest request, HttpServletResponse response) {
        /*设置模板名称*/
        FileInputStream fileInputStream = null;
        try {
            String fileName = new String("StudentTemplate.xls".getBytes("utf-8"), "iso8859-1");
            /*设置以附件的形式下载*/
            response.setHeader("content-Disposition", "attachment;filename=" + fileName);
            /*获取文件的路径*/
            String realPath = request.getSession().getServletContext().getRealPath("/static/upload/学生信息.xls");
            fileInputStream = new FileInputStream(realPath);
            IOUtils.copy(fileInputStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*上传学生信息*/
    @RequestMapping("/uplodaStudentExcel")
    @ResponseBody
    public AjaxValueRes uplodaStudentExcel(MultipartFile excelFile) {
        AjaxValueRes ajaxValueRes = new AjaxValueRes();
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(excelFile.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            Row rows;
            for (int i = 1; i <= lastRowNum; i++) {
                rows = sheet.getRow(i);
                Student student = new Student();
                student.setStuName(rows.getCell(0).toString());
                String age = getCellValue(rows.getCell(1)).toString();
                if ("男".equals(age)) {
                    student.setStuSex(true);
                } else {
                    student.setStuSex(false);
                }
                student.setStuAge((Date) getCellValue(rows.getCell(2)));
                student.setStuEnrol((Date) getCellValue(rows.getCell(3)));
                String phone = object2Str(getCellValue(rows.getCell(4)));
                /*去掉格式的特殊字符*/
                phone = phone.replace(",", "");
                student.setStuPhone(phone);
                student.setStuSite(rows.getCell(5).getRichStringCellValue().getString());
                String pro = getCellValue(rows.getCell(8)).toString();
                if ("在校".equals(pro)) {
                    student.setStuProgress(true);
                } else {
                    student.setStuProgress(false);
                }
                studentService.addStudent(student);
            }
            ajaxValueRes.setMrg("上传成功");
            ajaxValueRes.setSuccess(true);
        } catch (IOException e) {
            e.printStackTrace();
            ajaxValueRes.setMrg("上传失败");
            ajaxValueRes.setSuccess(false);
        }
        return ajaxValueRes;
    }


    /**
     * 格式化一个Date的类型
     *
     * @param date    转化的时间
     * @param pattern 格式化的类型
     * @return
     */
    public String DateFormatString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
        }
        return cell;
    }

    /**
     * 把科学计数法显示出全部数字
     *
     * @param d
     */
    public String object2Str(Object d) {
        if (d == null) {
            return "";
        }
        NumberFormat nf = NumberFormat.getInstance();
        return nf.format(d);
    }


}