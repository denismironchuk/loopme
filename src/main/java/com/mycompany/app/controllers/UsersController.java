package com.mycompany.app.controllers;

import com.mycompany.app.dto.UserDto;
import com.mycompany.app.model.enums.Role;
import com.mycompany.app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@Secured("ROLE_ADMIN")
public class UsersController {
    @Autowired
    UsersService usersService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<UserDto> getAllUsers() {
        return usersService.getAllUsers();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public UserDto getUserById(@PathVariable Integer id) {
        return usersService.findUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveUser(@RequestBody UserDto user) {
        usersService.saveUser(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Integer id) {
        usersService.deleteUser(id);
    }

    @RequestMapping(value="role/{role}", method = RequestMethod.GET)
    public Iterable<UserDto> getUsersWithRole(@PathVariable Role role) {
        return usersService.getUsersWithRole(role);
    }

}
