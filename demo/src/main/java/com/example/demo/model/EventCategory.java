package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class EventCategory extends AbstractEntity {

    @Setter
    private String name;

    @OneToMany(mappedBy = "eventCategory")
    private final List<Event> events = new ArrayList<>();

    public EventCategory(String name)
    {
        this.name = name;
    }

    public EventCategory() {}

    @Override
    public String toString() {
        return name;
    }
}
