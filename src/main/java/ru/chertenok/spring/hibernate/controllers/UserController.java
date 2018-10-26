package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.chertenok.spring.hibernate.config.Config;
import ru.chertenok.spring.hibernate.entity.Permission;
import ru.chertenok.spring.hibernate.services.PermissionService;
import ru.chertenok.spring.hibernate.services.UserService;
import ru.chertenok.spring.hibernate.util.PageInfo;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static ru.chertenok.spring.hibernate.config.Config.*;
import static ru.chertenok.spring.hibernate.config.Config.PAGE_MAP;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private PermissionService permissionService;

    {
        PERMISSION_MAP.put(PermissionName.userViewList,new Permission("USERS_LIST_VIEW","USERS","Просмотр списка пользователей"));

        PAGE_MAP.put(PagesName.userList, new PageInfo("/user/list", "Список пользователей", "user_list",PERMISSION_MAP.get(PermissionName.userViewList).getName()));
        PAGE_MAP.put(PagesName.userLogin, new PageInfo("/user/login", "Авторизация", "user_login3"));
        PAGE_MAP.put(PagesName.userAccessDenied, new PageInfo("/denied", "Доступ запрещен", "access-denied"));

    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loginForm")
    public String login(Model model, HttpServletRequest request){
        model.addAttribute("loginPageURL",PAGE_MAP.get(PagesName.userLogin));
        model.addAttribute("contentPage", PAGE_MAP.get(PagesName.userLogin).getSHABLON());
        model.addAttribute(BREADCRUMB, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userLogin)});
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();

    }


    @Autowired
    public UserController(PermissionService permissionService) {
        this.permissionService =permissionService;
        permissionService.registerPermission(PERMISSION_MAP.get(PermissionName.userViewList));
    }

    @PostMapping("/login")
    public String login(
            Model model, HttpServletRequest request,
                        @RequestParam(name = "username") String username,
                        @RequestParam(name = "password")  String password,
                        @RequestParam(name = "new_user", defaultValue = "false") boolean newUser
                        ){

        if (newUser) {
            userService.newUser(username,password);

        }
        model.addAttribute("new_user",newUser);
        model.addAttribute("username",username);
        model.addAttribute("password",password);
        return "forward:/authenticateTheUser";

        }


    @RequestMapping("/accessDenied")
    public String showAccessDeniedPage(Model model) {

        model.addAttribute("contentPage", PAGE_MAP.get(PagesName.userAccessDenied).getSHABLON());
        model.addAttribute(BREADCRUMB, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userAccessDenied)});
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }



    @RequestMapping("/list")
    public String  userList(Model model, HttpServletRequest request, Authentication authentication){

        List<String> listRole = addUserToModel(model);
        if (!listRole.contains("ROLE_"+PAGE_MAP.get(PagesName.userList).getPERMISSION())) {
            model.addAttribute("contentPage", PAGE_MAP.get(PagesName.userAccessDenied).getSHABLON());
            model.addAttribute(BREADCRUMB, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userAccessDenied)});
        } else {
            model.addAttribute("userList", userService.getUsers());
            model.addAttribute("contentPage", PAGE_MAP.get(PagesName.userList).getSHABLON());
            model.addAttribute(BREADCRUMB, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userList)});
        }
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }

}
