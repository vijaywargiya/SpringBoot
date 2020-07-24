package com.example.restservice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;


@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    Map<Long, Greeting> greetings = new HashMap<Long, Greeting>();

    @GetMapping("/greeting")
    public Map<Long, Greeting> list(@RequestParam(value = "name", defaultValue = "World") String name) {
        return greetings;
    }

    @PostMapping("/greeting")
    public Greeting create(@RequestParam(value = "name", defaultValue = "World") String name) {
        Greeting greeting =  new Greeting(counter.incrementAndGet(), String.format(template, name));
        greetings.put(greeting.getId(), greeting);
        return greeting;
    }


    @GetMapping("/greeting/{id}")
    public Greeting get(@PathVariable Long id) {
        return greetings.get(id);
    }

    @PutMapping("/greeting/{id}")
    public Greeting get(@RequestParam(value = "name", defaultValue = "World") String name, @PathVariable Long id) {
        Greeting greeting =  new Greeting(id, String.format(template, name));
        greetings.put(id, greeting);
        return greeting;
    }

    @DeleteMapping("/greeting/{id}")
    public boolean delete(@PathVariable Long id) {
        greetings.remove(id);
        return true;
    }


}