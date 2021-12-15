package com.charter.campaign.bulk.processor.step;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DBDataProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String data) throws Exception {
        return data;
    }
}
