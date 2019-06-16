package club.lw666.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@ToString
public class Clazz {
    private Long claId;

    private String claName;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date claOpenDate;
    /*应有人数*/
    private Integer claNumber;

    /*已有人数*/
    private Integer refNumber;

    private Long proId;

    private Grade grade;

    private Teacher teacher;


}