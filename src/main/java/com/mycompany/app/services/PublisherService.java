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
public class PublisherService {
    @Autowired
    private UsersDao usersDao;

    @Autowired
    UserConverter userConverter;

    public List<UserDto> findAllPublishers() {
        return usersDao.findByRole(Role.PUBLISHER).stream().map(user -> userConverter.convertToDto(user)).
                collect(Collectors.toList());
    }

    public UserDto finPublisherById(Integer id) {
        Optional<User> publisherOpt = usersDao.findById(id);

        if (!publisherOpt.isPresent()) {
            throw new RuntimeException("No publisher found for id");
        }

        User publisher = publisherOpt.get();
        if (publisher.getRole() != Role.PUBLISHER) {
            throw new RuntimeException("User you wnat to delete is not a publisher");
        }

        return userConverter.convertToDto(publisher);
    }

    public void savePublisher(UserDto publisherDto) {
        User publisher = userConverter.convertFromDto(publisherDto);
        publisher.setRole(Role.PUBLISHER);
        usersDao.save(publisher);
    }

    public void deletePublisher(Integer id) {
        Optional<User> publisherOpt = usersDao.findById(id);

        if (!publisherOpt.isPresent()) {
            throw new RuntimeException("No publisher found for id");
        }

        User publisher = publisherOpt.get();
        if (publisher.getRole() != Role.PUBLISHER) {
            throw new RuntimeException("User you wnat to delete is not a publisher");
        }

        usersDao.deleteById(id);
    }
}
