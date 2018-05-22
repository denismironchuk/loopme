package com.mycompany.app.controllers;

import com.mycompany.app.dto.UserDto;
import com.mycompany.app.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operators")
@Secured("ROLE_ADMIN")
public class OperatorController {
    @Autowired
    private OperatorService operatorService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<UserDto> getAllOperators() {
        return operatorService.findAllOperators();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public UserDto getOperatorById(@PathVariable Integer id) {
        return operatorService.findOperatorById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveOperator(@RequestBody UserDto operator) {
        operatorService.saveOperator(operator);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteOperator(@PathVariable Integer id) {
        operatorService.deleteOperator(id);
    }
}
