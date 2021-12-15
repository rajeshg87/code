package com.charter.campaign.bulk.processor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mapper implements Serializable {
    private String inputFileDataSpliter;
    private String outputDataDir;
    private String outputFileDataSpliter;
    private String sql;
    private CampaignDatasource campaignDatasource;
    private List<FieldMapper> fieldMapper = new ArrayList<>();
}
