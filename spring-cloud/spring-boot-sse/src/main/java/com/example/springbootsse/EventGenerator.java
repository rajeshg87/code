package com.example.springbootsse;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class EventGenerator {

    private List<String> events = Arrays.asList(
            "Rajesh",
            "Gowsi",
            "Ragav",
            "Lenin",
            "Vasanth",
            "Aravind"
    );

    private final Random random = new Random(events.size());

    public String generate(){
        return events.get(random.nextInt(events.size()));
    }
}
