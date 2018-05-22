package com.mycompany.app.dao;

import com.mycompany.app.model.App;
import com.mycompany.app.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppsDao extends CrudRepository<App, Integer> {
    List<App> findByUserId(Integer userId);
    List<App> findByIdAndUserId(Integer id, Integer userId);
}
