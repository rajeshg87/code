package com.charter.campaign.bulk.processor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CampaignDatasource implements Serializable {
    private String driver;
    private String url;
    private String username;
    private String password;
}
