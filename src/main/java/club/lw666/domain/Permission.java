package club.lw666.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Permission {
    private Long pId;

    private String pName;

    private String pResource;


}