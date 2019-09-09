package club.lw666.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Menu {
    private Integer id;

    private String text;

    private String url;

    private List<Menu> children = new ArrayList<>();

    private Permission permission;

}
