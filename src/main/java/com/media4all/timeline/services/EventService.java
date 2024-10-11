package com.media4all.timeline.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media4all.timeline.dtos.EventDTO;
import com.media4all.timeline.entities.Event;
import com.media4all.timeline.repositories.EventRepository;

@Service
public class EventService implements IService<EventDTO, Event> {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Optional<EventDTO> create(Event event) {

		Optional<Event> existingEvent = this.eventRepository.findByTitle(event.getTitle());

		if (existingEvent.isPresent()) {
			return Optional.empty();
		}
		else {
			Event newEvent = this.eventRepository.save(event);

			return Optional.of(modelMapper.map(newEvent, EventDTO.class));
		}
	}

	public Optional<EventDTO> get(Long id) {

		Optional<Event> event = this.eventRepository.findById(id);

		if (event.isPresent()) {
			return Optional.of(modelMapper.map(event.get(), EventDTO.class));
		}
		else {
			return Optional.empty();
		}
	}

	public Optional<List<EventDTO>> list() {

		List<Event> events = this.eventRepository.findAll();

		if (events.size() > 0) {
			return Optional.of(events.stream().map(event -> modelMapper.map(event, EventDTO.class)).toList());
		}
		else {
			return Optional.empty();
		}
	}

	public Optional<EventDTO> update(Long id, Event event) {

		Optional<Event> existingEvent = this.eventRepository.findById(id);

		if (existingEvent.isPresent()) {
			Event updatedEvent = existingEvent.get();

			if (event.getTitle() != null && !event.getTitle().isEmpty()) {
				updatedEvent.setTitle(event.getTitle());
			}
			if (event.getDescription() != null && !event.getDescription().isEmpty()) {
				updatedEvent.setDescription(event.getDescription());
			}
			if (event.getTimestamp() != null) {
				updatedEvent.setTimestamp(event.getTimestamp());
			}
			if (
				 event.getType() != null &&
				!event.getType().getName().isEmpty() &&
				!event.getType().getDescription().isEmpty() &&
				!event.getType().getColor().isEmpty()
			) {
				updatedEvent.setType(event.getType());
			}
			if (event.getIcon() != null && !event.getIcon().isEmpty()) {
				updatedEvent.setIcon(event.getIcon());
			}
			if (event.getUser() != null && !event.getUser().getName().isEmpty()) {
				updatedEvent.setUser(event.getUser());
			}

			this.eventRepository.save(updatedEvent);

			return Optional.of(modelMapper.map(updatedEvent, EventDTO.class));
		}
		else {
			return Optional.empty();
		}
	}

	public boolean delete(Long id) {

		Optional<Event> existingEvent = this.eventRepository.findById(id);

		if (existingEvent.isPresent()) {
			this.eventRepository.delete(existingEvent.get());

			return true;
		}
		else {
			return false;
		}
	}


}
