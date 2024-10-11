package com.media4all.timeline.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.media4all.timeline.dtos.EventTypeDTO;
import com.media4all.timeline.entities.EventType;
import com.media4all.timeline.services.EventTypeService;

@RestController
@RequestMapping("/eventtypes")
public class EventTypeController implements IController<EventTypeDTO, EventType> {

	@Autowired
	private EventTypeService eventTypeService;

	@PostMapping
	public ResponseEntity<EventTypeDTO> create(@RequestBody EventType event) {

		Optional<EventTypeDTO> newEventType = this.eventTypeService.create(event);

		if (newEventType.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(newEventType.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<EventTypeDTO> get(@PathVariable Long id) {

		Optional<EventTypeDTO> eventType = this.eventTypeService.get(id);

		if (eventType.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(eventType.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping
	public ResponseEntity<List<EventTypeDTO>> list() {

		Optional<List<EventTypeDTO>> eventTypes = this.eventTypeService.list();

		if (eventTypes.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(eventTypes.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<EventTypeDTO> update(@PathVariable Long id, @RequestBody EventType eventType) {

		Optional<EventTypeDTO> updatedEventType = this.eventTypeService.update(id, eventType);

		if (updatedEventType.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(updatedEventType.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(Long id) {

		boolean deleted = this.eventTypeService.delete(id);

		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}	
}
