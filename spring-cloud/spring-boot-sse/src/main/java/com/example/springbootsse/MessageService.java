package com.example.springbootsse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final EventGenerator eventGenerator;

    public ParallelFlux<Event> stream(){
        return Flux.fromArray("abcdefghijklmnopqrstuvwxyz".split(""))
                .parallel(5)
                .runOn(Schedulers.parallel())
                .map(name -> createEvent(name.toUpperCase()));
    }

    private Event createEvent(String name)  {
        int sleep = sleep();
        return new Event(name, sleep+"", Instant.now());
    }

    private int sleep() {
        int sleep = 0;
        try{
            Random r = new Random();
            int min = 3000;
            int max = 10000;
            sleep = r.nextInt((max - min) + 1) + min;
            Thread.sleep((long) sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sleep;
    }
}

