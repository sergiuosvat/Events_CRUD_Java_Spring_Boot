package com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Tag extends AbstractEntity {
    private String name;

    public Tag(String name){
        this.name = name;
    }

    public Tag() {}

    public String getDisplayName(){
        return "#" + name + " ";
    }

}
