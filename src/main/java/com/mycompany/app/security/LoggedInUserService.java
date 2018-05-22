package com.mycompany.app.security;

import com.mycompany.app.dao.UsersDao;
import com.mycompany.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoggedInUserService {
    @Autowired
    private UsersDao userDao;

    public User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userDao.findByName(auth.getName()).stream().findFirst().orElse(null);
    }
}
