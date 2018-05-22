package com.mycompany.app.services;

import com.mycompany.app.converters.UserConverter;
import com.mycompany.app.dao.UsersDao;
import com.mycompany.app.dto.UserDto;
import com.mycompany.app.model.enums.Role;
import com.mycompany.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UsersService {
    @Autowired
    UsersDao usersDao;

    @Autowired
    UserConverter userConverter;

    public Iterable<UserDto> getAllUsers() {
        return StreamSupport.stream(usersDao.findAll().spliterator(), false).
                map(user -> userConverter.convertToDto(user)).collect(Collectors.toList());
    }

    public void saveUser(UserDto dto) {
        User user = userConverter.convertFromDto(dto);
        usersDao.save(user);
    }

    public UserDto findUserById(Integer id) {
        return userConverter.convertToDto(usersDao.findById(id).orElse(null));
    }

    public void deleteUser(Integer id) {
        usersDao.deleteById(id);
    }

    public Iterable<UserDto> getUsersWithRole(Role role) {
        return StreamSupport.stream(usersDao.findByRole(role).spliterator(), false).
                map(user -> userConverter.convertToDto(user)).collect(Collectors.toList());
    }
}
