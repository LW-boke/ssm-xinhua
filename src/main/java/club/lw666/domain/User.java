package club.lw666.domain;


import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {


    private static final long serialVersionUID = 8780098752276312137L;

    private Long id;

    private String username;

    private String password;

    private String name;

    private Boolean admin;

    public Role roles;

}
