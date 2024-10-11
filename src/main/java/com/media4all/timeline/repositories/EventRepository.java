package com.media4all.timeline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.media4all.timeline.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
