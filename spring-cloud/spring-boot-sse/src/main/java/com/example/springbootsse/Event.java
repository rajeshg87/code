package com.example.springbootsse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    private String name;
    private String text;
    private Instant timestamp;
}
