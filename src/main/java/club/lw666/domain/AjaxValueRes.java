package club.lw666.domain;


import lombok.Data;

import java.io.Serializable;

@Data

/*
 *  @ClassName: AjaxValueRes.java
 *  @Author: Slayer
 *  @Date: 2019/9/9 15:00
 *  @Description: 统一返回结果
 */
public class AjaxValueRes implements Serializable {

    private static final long serialVersionUID = -1715938349835646928L;

    private Boolean success;
    private String mrg;
}
