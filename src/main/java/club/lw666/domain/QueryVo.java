package club.lw666.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Data

/*
 *  @ClassName: QueryVo.java
 *  @Author: Slayer
 *  @Date: 2019/9/9 15:01
 *  @Description:  数据传输模型
 */
public class QueryVo implements Serializable {
    private int id;
    private int page;
    private int rows;
    private String keyword;


}
