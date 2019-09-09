package club.lw666.domain;


import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable {
    private Long pId;

    private String pName;

    private String pResource;

}
