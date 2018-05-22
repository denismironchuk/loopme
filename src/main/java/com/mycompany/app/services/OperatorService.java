package com.mycompany.app.services;

import com.mycompany.app.converters.UserConverter;
import com.mycompany.app.dao.UsersDao;
import com.mycompany.app.dto.UserDto;
import com.mycompany.app.model.User;
import com.mycompany.app.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperatorService {
    @Autowired
    private UsersDao usersDao;

    @Autowired
    UserConverter userConverter;

    public List<UserDto> findAllOperators() {
        return usersDao.findByRole(Role.ADOPS).stream().map(user -> userConverter.convertToDto(user)).
                collect(Collectors.toList());
    }

    public void saveOperator(UserDto publisherDto) {
        User publisher = userConverter.convertFromDto(publisherDto);
        publisher.setRole(Role.ADOPS);
        usersDao.save(publisher);
    }

    public void deleteOperator(Integer id) {
        Optional<User> publisherOpt = usersDao.findById(id);

        if (!publisherOpt.isPresent()) {
            throw new RuntimeException("No publisher found for id");
        }

        User publisher = publisherOpt.get();
        if (publisher.getRole() != Role.ADOPS) {
            throw new RuntimeException("User you wnat to delete is not an operator");
        }

        usersDao.deleteById(id);
    }

    public UserDto findOperatorById(Integer id) {
        Optional<User> publisherOpt = usersDao.findById(id);

        if (!publisherOpt.isPresent()) {
            throw new RuntimeException("No operator found for id");
        }

        User publisher = publisherOpt.get();
        if (publisher.getRole() != Role.ADOPS) {
            throw new RuntimeException("User you wnat to delete is not an operator");
        }

        return userConverter.convertToDto(publisher);
    }
}
