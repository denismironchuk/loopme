package com.mycompany.app.services;

import com.mycompany.app.converters.AppConverter;
import com.mycompany.app.dao.AppsDao;
import com.mycompany.app.dto.AppDto;
import com.mycompany.app.model.App;
import com.mycompany.app.model.User;
import com.mycompany.app.model.enums.Role;
import com.mycompany.app.security.LoggedInUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppService {
    @Autowired
    AppsDao appsDao;

    @Autowired
    AppConverter appConverter;

    @Autowired
    private LoggedInUserService loggedInUserService;

    public Iterable<AppDto> getAllApps() {
        User currentUser = loggedInUserService.getLoggedInUser();
        Iterable<App> result;
        if (currentUser.getRole() == Role.ADOPS) {
            result = appsDao.findAll();
        } else {
            result = appsDao.findByUserId(currentUser.getId());
        }

        return StreamSupport.stream(result.spliterator(), false).
                map(app -> appConverter.convertToDto(app)).collect(Collectors.toList());
    }

    public void saveApp(AppDto dto) {
        User currentUser = loggedInUserService.getLoggedInUser();
        App app = appConverter.convertFromDto(dto);
        app.setUserId(currentUser.getId());
        appsDao.save(app);
    }

    public AppDto findAppById(Integer id) {
        User currentUser = loggedInUserService.getLoggedInUser();
        App result = appsDao.findById(id).orElse(null);

        if (null == result) {
            throw new RuntimeException("No app for id found");
        }

        if (currentUser.getRole() == Role.PUBLISHER) {
            if (result.getUserId() != currentUser.getId()) {
                throw new RuntimeException("Publisher is not allowed to get this app");
            }
        }

        return appConverter.convertToDto(result);
    }

    public void deleteApp(Integer id) {
        User currentUser = loggedInUserService.getLoggedInUser();
        if (currentUser.getRole() == Role.ADOPS) {
            appsDao.deleteById(id);
        } else {
            Optional<App> appOpt = appsDao.findById(id);
            if (appOpt.isPresent()) {
                App app = appOpt.get();
                if (app.getUserId() == currentUser.getId()) {
                    appsDao.deleteById(id);
                } else {
                    throw new RuntimeException("Publisher cant delete others publisher app");
                }
            } else {
                throw new RuntimeException("No app for id found");
            }
        }
    }
}
