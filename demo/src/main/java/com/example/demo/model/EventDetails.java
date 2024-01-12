package com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class EventDetails extends AbstractEntity {

    private String description;
    private String contactEmail;

    public EventDetails(String description, String contactEmail) {
        this.description = description;
        this.contactEmail = contactEmail;
    }

    public EventDetails() {}

}
