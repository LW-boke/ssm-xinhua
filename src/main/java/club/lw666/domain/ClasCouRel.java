package club.lw666.domain;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ClasCouRel {
    private List<Clazz> clazzes = new ArrayList<>();

    private List<Course> courses = new ArrayList<>();

    private List<Teacher> teachers = new ArrayList<>();
}
