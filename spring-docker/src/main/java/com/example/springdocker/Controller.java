package com.example.springdocker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/")
    public String hi() {
        return "Hello World";
    }

    @PostMapping("/create/{name}")
    public String create(@PathVariable("name") String name) {
        Card card = new Card();
        card.setName(name);
        return cardRepository.save(card).toString();
    }

    @GetMapping("/get")
    public List<Card> get() {
        return cardRepository.findAll();
    }
}
