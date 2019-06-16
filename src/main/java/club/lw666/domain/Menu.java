package club.lw666.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Setter@Getter@ToString
public class Menu {
    private Integer id;

    private String text;

    private String url;

    private List<Menu> children = new ArrayList<>();

    private Permission permission;

}