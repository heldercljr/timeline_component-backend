package com.media4all.timeline.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.media4all.timeline.dtos.EventDTO;
import com.media4all.timeline.entities.Event;
import com.media4all.timeline.services.EventService;

@RestController
@RequestMapping("/events")
public class EventController implements IController<EventDTO, Event> {

	@Autowired
	private EventService eventService;

	@PostMapping
	public ResponseEntity<EventDTO> create(@RequestBody Event event) {

		Optional<EventDTO> newEvent = this.eventService.create(event);

		if (newEvent.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(newEvent.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<EventDTO> get(@PathVariable Long id) {

		Optional<EventDTO> event = this.eventService.get(id);

		if (event.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(event.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping
	public ResponseEntity<List<EventDTO>> list() {

		Optional<List<EventDTO>> events = this.eventService.list();

		if (events.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(events.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<EventDTO> update(Long id, Event event) {

		Optional<EventDTO> updatedEvent = this.eventService.update(id, event);

		if (updatedEvent.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(updatedEvent.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(Long id) {

		boolean deleted = this.eventService.delete(id);

		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
}
