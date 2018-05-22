package com.mycompany.app.dao;

import com.mycompany.app.model.enums.Role;
import com.mycompany.app.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDao extends CrudRepository<User, Integer> {
    List<User> findByName(String name);
    List<User> findByRole(Role role);
}
