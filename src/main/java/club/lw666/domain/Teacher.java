package club.lw666.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Teacher implements Serializable {


    private static final long serialVersionUID = -32569677457015687L;

    private Long teaId;

    private String teaName;

    private Boolean teaSex;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date teaAge;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date teaEnrol;

    private String teaAbout;

    private String teaPhone;

    private String teaSite;

    private Profession profession;


}
