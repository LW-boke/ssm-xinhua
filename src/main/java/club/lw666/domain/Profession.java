package club.lw666.domain;


import lombok.Data;

import java.io.Serializable;

@Data
public class Profession implements Serializable {
    private Long proId;

    private String proName;

}
