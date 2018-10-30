package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.chertenok.spring.hibernate.entity.Permission;
import ru.chertenok.spring.hibernate.entity.User;
import ru.chertenok.spring.hibernate.services.PermissionService;
import ru.chertenok.spring.hibernate.services.UserService;
import ru.chertenok.spring.hibernate.util.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static ru.chertenok.spring.hibernate.config.Config.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final String MESSAGE_USER_NOT_FOUND = "Пользователь c таким ID не найден!";

    static {
        // Добавляем права контроллера
        PERMISSION_MAP.put(PermissionName.userViewList, new Permission("USERS_VIEW_DETAIL", "USERS", "Просмотр списка пользователей"));
        PERMISSION_MAP.put(PermissionName.userViewDetail, new Permission("USERS_VIEW_DETAIL", "USERS", "Просмотр списка пользователей"));
        // добавляем страницы контроллера
        PAGE_MAP.put(PagesName.userList, new PageInfo("/user/list", "Список пользователей", "user_list",
                PERMISSION_MAP.get(PermissionName.userViewList).getName()));
        // добавляем страницы контроллера
        PAGE_MAP.put(PagesName.userProfile, new PageInfo("/user/profile", "Профиль пользователя", "user_detail"));

        PAGE_MAP.put(PagesName.userDetail, new PageInfo("/user/{id}", "Информация о пользователях", "user_detail", true,
                PERMISSION_MAP.get(PermissionName.userViewDetail).getName()));
        PAGE_MAP.put(PagesName.userLogin, new PageInfo("/user/login", "Авторизация", "user_login3"));
        PAGE_MAP.put(PagesName.userLoginForm, new PageInfo("/user/loginForm", "Авторизация", "user_login3"));
        //формируем  пути от страницы до корня, нормальные + ошибки, если есть обработка ошибок, чтоб вернуться на страницу назад
        BREADCRUMB_MAP.put(PagesName.userList, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userList)});
        BREADCRUMB_MAP.put(PagesName.userDetail, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userList),
                PAGE_MAP.get(PagesName.userDetail)});
        BREADCRUMB_MAP.put(PagesName.userProfile, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userProfile)});
        BREADCRUMB_MAP.put(PagesName.userLogin, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userLogin)});
        BREADCRUMB_MAP.put(PagesName.userAccessDenied, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userAccessDenied)});
        BREADCRUMB_MAP.put(PagesName.userLoginForm, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userLoginForm)});
        //для ошибок типа курс не существует, чтоб можно было вернуться к списку
        BREADCRUMB_ERROR_MAP.put(PagesName.userDetail, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userList),
                PAGE_MAP.get(PagesName.page404)});

    }

    private UserService userService;
    private PermissionService permissionService;


    @Autowired
    public UserController(PermissionService permissionService) {
        this.permissionService = permissionService;
        // проверяем наличие в бд и если нет, то заносим и даём права админу
        this.permissionService.registerPermission(PERMISSION_MAP.get(PermissionName.userViewList));
        this.permissionService.registerPermission(PERMISSION_MAP.get(PermissionName.userViewDetail));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loginForm")
    public String login(Model model, HttpServletRequest request) {
        model.addAttribute("loginPageURL", PAGE_MAP.get(PagesName.userLogin));
        PageInfo.makeAllPlease(model, PagesName.userLoginForm);
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();

    }

    @PostMapping("/login")
    public String login(
            Model model, HttpServletRequest request,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "new_user", defaultValue = "false") boolean newUser
    ) {

        if (newUser) {
            userService.newUser(username, password);

        }
        model.addAttribute("new_user", newUser);
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "forward:/authenticateTheUser";

    }


    @RequestMapping("/accessDenied")
    public String showAccessDeniedPage(Model model) {
        PageInfo.makeAllPlease(model, PagesName.userAccessDenied);
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }


    @RequestMapping("/list")
    public String userList(Model model) {
        if (PageInfo.makeAllPlease(model, PagesName.userList)) {
            model.addAttribute("userList", userService.getUsers());
            model.addAttribute("userDetailLink", PAGE_MAP.get(PagesName.userDetail));
        }
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }

    @RequestMapping("/{id}")
    public String userList(Model model, @PathVariable("id") int id) {
        if (PageInfo.makeAllPlease(model, PagesName.userDetail)) {
            Optional<User> user = userService.getUserById(id);
            if (user.isPresent()) {
                model.addAttribute("user", user.get());
                model.addAttribute("userRole", userService.getUserRolesById(id));

            } else {
                model.addAttribute(MESSAGE404, MESSAGE_USER_NOT_FOUND);
                model.addAttribute(BREADCRUMB, BREADCRUMB_ERROR_MAP.get(PagesName.userDetail));
            }
        }
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }

    @RequestMapping("/profile")
    public String userProfile(Model model) {
        if (PageInfo.makeAllPlease(model, PagesName.userProfile)) {
            org.springframework.security.core.userdetails.User userSpring =
                    (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
             Optional < User > user = userService.getUserByName(userSpring.getUsername());
            if (user.isPresent()) {
                model.addAttribute("user", user.get());
                model.addAttribute("userRole", userService.getUserRolesById(user.get().getId()));

            } else {
                model.addAttribute(MESSAGE404, MESSAGE_USER_NOT_FOUND);
                model.addAttribute(BREADCRUMB, BREADCRUMB_ERROR_MAP.get(PagesName.userDetail));
            }
        }
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }

}
