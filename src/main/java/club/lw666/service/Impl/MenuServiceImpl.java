package club.lw666.service.Impl;

import club.lw666.domain.Menu;
import club.lw666.domain.User;
import club.lw666.mapper.MenuMapper;
import club.lw666.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service(value = "menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    /*查询出所有的菜单*/
    @Override
    public List<Menu> getMenus() {
        List<Menu> menuList = menuMapper.getMenuTrees();
        /*获取当前主体*/
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (user.getAdmin()) {
            return menuList;
        }
        return checkPermission(menuList);
    }

    public List<Menu> checkPermission(List<Menu> menus) {
        /*获取当前主体*/
        Subject subject = SecurityUtils.getSubject();
        /*迭代遍历所有的菜单*/
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            /*获取每个菜单*/
            Menu menu = iterator.next();
            /*判断地址不为null*/
            if (menu.getUrl() != null) {
                /*没有这个权限则删除*/
                if (!subject.isPermitted(menu.getUrl())) {
                    iterator.remove();
                }
                continue;
            }
            if (menu.getChildren().size() > 0) {
                checkPermission(menu.getChildren());
            }
        }
        return menus;
    }
}
