package club.lw666.web;

import club.lw666.domain.Profession;
import club.lw666.service.ProfessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ProfessionController {
    @Resource(name = "professionService")
    private ProfessionService professionService;

    /*   获取所有的职业类别*/
    @RequestMapping("/getProfessionList")
    @ResponseBody
    public List<Profession> getProfessions() {
        return professionService.getProfessions();
    }

}
