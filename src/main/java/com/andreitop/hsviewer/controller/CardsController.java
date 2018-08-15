package com.andreitop.hsviewer.controller;

import com.andreitop.hsviewer.model.Card;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardsController {

    private List<Card> cards = new ArrayList<>();

    @GetMapping
    public List<Card> findAll() {
        return cards;
    }

    @GetMapping("/{id}")
    public Card findById(@PathVariable("id") Long id) {
        return cards.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public void add(@RequestBody Card card) {
        card.setId((long) (cards.size() + 1));
        cards.add(card);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        cards.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .ifPresent(c -> cards.remove(c));
    }

    @PutMapping
    public void update(@RequestBody Card newCard) {
        cards.stream()
                .filter(c -> c.getId().equals(newCard.getId()))
                .findFirst()
                .ifPresent(c -> cards.set(cards.indexOf(c), newCard));
    }
}
