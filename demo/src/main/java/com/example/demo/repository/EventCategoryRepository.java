package com.example.demo.repository;

import com.example.demo.model.EventCategory;
import org.springframework.data.repository.CrudRepository;

public interface EventCategoryRepository extends CrudRepository<EventCategory, Integer> {
}
