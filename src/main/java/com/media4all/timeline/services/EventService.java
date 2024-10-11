package com.media4all.timeline.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media4all.timeline.dtos.EventDTO;
import com.media4all.timeline.entities.Event;
import com.media4all.timeline.repositories.EventRepository;
import com.media4all.timeline.entities.EventType;
import com.media4all.timeline.repositories.EventTypeRepository;
import com.media4all.timeline.entities.User;
import com.media4all.timeline.repositories.UserRepository;


@Service
public class EventService implements IService<EventDTO> {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private EventTypeRepository eventTypeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Optional<EventDTO> create(EventDTO eventDTO) {

		Optional<Event> existingEvent = this.eventRepository.findByTitle(eventDTO.getTitle());

		if (existingEvent.isPresent()) {
			return Optional.empty();
		}
		else {
			Optional<EventType> eventType = this.eventTypeRepository.findByName(eventDTO.getType());

			if (eventType.isPresent()) {
				Optional<User> user = this.userRepository.findByName(eventDTO.getUser());

				if (user.isPresent()) {
					Event newEvent = this.eventRepository.save(
						new Event(
							eventDTO.getTitle(),
							eventDTO.getDescription(),
							eventDTO.getTimestamp(),
							eventType.get(),
							eventDTO.getIcon(),
							user.get()
						)
					);

					return Optional.of(modelMapper.map(newEvent, EventDTO.class));
				}
				else {
					return Optional.empty();
				}
			}
			else {
				return Optional.empty();
			}			
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

	public Optional<EventDTO> update(Long id, EventDTO eventDTO) {

		Optional<Event> existingEvent = this.eventRepository.findById(id);

		if (existingEvent.isPresent()) {
			Event updatedEvent = existingEvent.get();

			if (eventDTO.getTitle() != null && !eventDTO.getTitle().isEmpty()) {
				updatedEvent.setTitle(eventDTO.getTitle());
			}
			if (eventDTO.getDescription() != null && !eventDTO.getDescription().isEmpty()) {
				updatedEvent.setDescription(eventDTO.getDescription());
			}
			if (eventDTO.getTimestamp() != null) {
				updatedEvent.setTimestamp(eventDTO.getTimestamp());
			}
			if  (eventDTO.getType() != null) {
				Optional<EventType> eventType = this.eventTypeRepository.findByName(eventDTO.getType());

				if (eventType.isPresent()) {
					updatedEvent.setType(eventType.get());
				}
				else {
					return Optional.empty();
				}
			}
			if (eventDTO.getIcon() != null && !eventDTO.getIcon().isEmpty()) {
				updatedEvent.setIcon(eventDTO.getIcon());
			}
			if (eventDTO.getUser() != null) {
				Optional<User> user = this.userRepository.findByName(eventDTO.getUser());
				
				if (user.isPresent()) {
					updatedEvent.setUser(user.get());
				}
				else {
					return Optional.empty();
				}
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
