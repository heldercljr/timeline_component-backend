package com.media4all.timeline.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.media4all.timeline.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	Optional<Event> findByTitle(String title);
}
