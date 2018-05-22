package com.mycompany.app.converters;

import java.util.List;
import java.util.Set;

public interface BaseConverter<S, D> {
    S convertFromDto(D dto);
    D convertToDto(S source);
}
