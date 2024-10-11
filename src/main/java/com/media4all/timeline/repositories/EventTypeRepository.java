package com.media4all.timeline.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.media4all.timeline.entities.EventType;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {

	Optional<EventType> findByName(String name);
}
