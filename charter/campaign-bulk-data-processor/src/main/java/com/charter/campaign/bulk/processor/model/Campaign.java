package com.charter.campaign.bulk.processor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Campaign implements Serializable {

    private String name;
    private String mapping;
    private String trigger;
    private String cron;
    private String sourceDirectory;
}
