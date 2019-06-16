package club.lw666.web;

import club.lw666.domain.Menu;
import club.lw666.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MenuController {
    @Resource(name = "menuService")
    private MenuService menuService;

    /*查询出所有的菜单*/
    @RequestMapping("/getMenuTrees")
    @ResponseBody
    public List<Menu> getMenus() {
        return menuService.getMenus();
    }
}
