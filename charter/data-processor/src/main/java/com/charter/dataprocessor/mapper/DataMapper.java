package com.charter.dataprocessor.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class DataMapper {

    private Integer outputPosition;
    private String valueName;
    private String displayName;
    private Integer dataPosition;
    private boolean parameterValue;
}
