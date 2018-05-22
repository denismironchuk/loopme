package com.mycompany.app.controllers;

import com.mycompany.app.dto.AppDto;
import com.mycompany.app.dto.UserDto;
import com.mycompany.app.services.AppService;
import com.mycompany.app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apps")
@Secured({"ROLE_ADOPS", "ROLE_PUBLISHER"})
public class AppsController {
    @Autowired
    AppService appService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<AppDto> getAllApps() {
        return appService.getAllApps();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public AppDto getAppById(@PathVariable Integer id) {
        return appService.findAppById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveApp(@RequestBody AppDto user) {
        appService.saveApp(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteApp(@PathVariable Integer id) {
        appService.deleteApp(id);
    }
}
