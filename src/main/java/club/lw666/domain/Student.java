package club.lw666.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Student implements Serializable {

    private static final long serialVersionUID = 200906697193473508L;

    private Long stuId;

    private String stuName;

    private Boolean stuSex;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stuAge;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stuEnrol;

    private String stuPhone;

    private String stuSite;

    private Boolean stuProgress;

    private Profession profession;

    private Clazz clazz;

}
