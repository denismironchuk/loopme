package com.mycompany.app.converters;

import com.mycompany.app.dto.AppDto;
import com.mycompany.app.model.App;
import com.mycompany.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AppConverter implements BaseConverter<App, AppDto> {
    @Override
    public App convertFromDto(final AppDto dto) {
        if (null == dto) {
            return null;
        }

        App app = new App();
        app.setId(dto.getId());
        app.setName(dto.getName());
        app.setContentTypes(dto.getContentTypes());
        app.setType(dto.getType());
        app.setUserId(dto.getUserId());

        return app;
    }

    @Override
    public AppDto convertToDto(final App app) {
        if (null == app) {
            return null;
        }

        AppDto dto = new AppDto();
        dto.setId(app.getId());
        dto.setName(app.getName());
        dto.setContentTypes(app.getContentTypes());
        dto.setType(app.getType());
        dto.setUserId(app.getUserId());

        return dto;
    }
}
