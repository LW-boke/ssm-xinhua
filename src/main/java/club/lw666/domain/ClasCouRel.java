package club.lw666.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class ClasCouRel {
    private List<Clazz> clazzes = new ArrayList<>();

    private List<Course> courses = new ArrayList<>();

    private List<Teacher> teachers = new ArrayList<>();
}