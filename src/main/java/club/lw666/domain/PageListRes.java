package club.lw666.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class PageListRes {
    private Long total;
    private List<?> rows = new ArrayList<>();
}
