package ru.geekbrains.jsfControllers;

import ru.geekbrains.entity.RoleDto;
import ru.geekbrains.entity.UserDto;
import ru.geekbrains.services.Logger;
import ru.geekbrains.services.RoleService;
import ru.geekbrains.services.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class UserController implements Serializable {

    @EJB
    private UserService userService;

    @Inject
    private RoleService roleService;

    private UserDto user;

    private List<RoleDto> roles;

    private List<UserDto> users;

    public void preLoad() {
        this.roles = roleService.getAllRoles();
        this.users = userService.getAllUsers();
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Interceptors({Logger.class})
    public List<UserDto> getAllUsers() {
        return users;
    }

    @Interceptors({Logger.class})
    public String editUser(UserDto user) {
        this.user = user;
        return "/user.xhtml?faces-redirect=true";
    }

    @Interceptors({Logger.class})
    public void deleteUser(UserDto user) {
        userService.delete(user.getId());
    }

    @Interceptors({Logger.class})
    public String createUser() {
        this.user = new UserDto();
        return "/user.xhtml?faces-redirect=true";
    }

    @Interceptors({Logger.class})
    public String saveUser() {
        userService.merge(this.user);
        return "/users.xhtml?faces-redirect=true";
    }

    @Interceptors({Logger.class})
    public List<RoleDto> getAllRoles() {
        return roles;
    }
}
