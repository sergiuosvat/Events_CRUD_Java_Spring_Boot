package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Entity
@Data
public class Event extends AbstractEntity {

    @Setter
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private EventDetails eventDetails;

    @ManyToOne
    private EventCategory eventCategory;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public Event(String name, EventCategory eventCategory) {
        this.name = name;
        this.eventCategory = eventCategory;
    }

    public Event() {}

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString(){
        return name;
    }

}
