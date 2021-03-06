package club.lw666.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Course {
    private Long couId;

    private String couName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date couOpenDate;

    private String couContent;

    private Long proId;

    private Teacher teacher;

    private Profession profession;


}
