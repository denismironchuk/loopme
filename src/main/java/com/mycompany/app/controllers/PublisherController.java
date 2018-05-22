package com.mycompany.app.controllers;

import com.mycompany.app.dto.UserDto;
import com.mycompany.app.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
@Secured("ROLE_ADOPS")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<UserDto> getAllPublishers() {
        return publisherService.findAllPublishers();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public UserDto getPublisherById(@PathVariable Integer id) {
        return publisherService.finPublisherById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void savePublisher(@RequestBody UserDto publisher) {
        publisherService.savePublisher(publisher);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deletePublisher(@PathVariable Integer id) {
        publisherService.deletePublisher(id);
    }
}
