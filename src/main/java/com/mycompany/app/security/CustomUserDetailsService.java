package com.mycompany.app.security;

import com.mycompany.app.dao.UsersDao;
import com.mycompany.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersDao usersDao;

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        List<User> users = usersDao.findByName(userName);

        if (CollectionUtils.isEmpty(users)) {
            throw new UsernameNotFoundException(String.format("User %s not found", userName));
        }

        User user = users.get(0);

        return org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder().
                username(user.getName()).password(user.getPassword()).roles(user.getRole().toString()).build();
    }
}
