package com.charter.campaign.bulk.processor.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class FieldMapper implements Serializable {
    private Integer outputPosition;
    private String valueName;
    private String displayName;
    private Integer dataPosition;
    private boolean parameterValue;
}
