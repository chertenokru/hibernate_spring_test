package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.chertenok.spring.hibernate.services.UserService;
import ru.chertenok.spring.hibernate.util.Config;
import ru.chertenok.spring.hibernate.util.PageInfo;

import static ru.chertenok.spring.hibernate.util.Config.*;
import static ru.chertenok.spring.hibernate.util.Config.PAGE_MAP;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    {
        PAGE_MAP.put(PagesName.userList, new PageInfo("/user/list", "Список пользователей", "user_list"));
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    @RequestMapping("/list")
    public String  userList(Model model){

        model.addAttribute("userList",userService.getUsers());

        model.addAttribute("contentPage", PAGE_MAP.get(PagesName.userList).getSHABLON());
        model.addAttribute(BREADCRUMB, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userList)});
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }

}
