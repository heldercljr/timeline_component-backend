package com.media4all.timeline.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media4all.timeline.dtos.EventTypeDTO;
import com.media4all.timeline.entities.EventType;
import com.media4all.timeline.repositories.EventTypeRepository;

@Service
public class EventTypeService implements IService<EventTypeDTO, EventType> {

	@Autowired
	private EventTypeRepository eventTypeRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Optional<EventTypeDTO> create(EventType eventType) {

		Optional<EventType> existingEventType = this.eventTypeRepository.findByName(eventType.getName());

		if (existingEventType.isPresent()) {
			return Optional.empty();
		}
		else {
			EventType newEventType = this.eventTypeRepository.save(eventType);

			return Optional.of(modelMapper.map(newEventType, EventTypeDTO.class));
		}
	}

	public Optional<EventTypeDTO> get(Long id) {

		Optional<EventType> eventType = this.eventTypeRepository.findById(id);

		if (eventType.isPresent()) {
			return Optional.of(modelMapper.map(eventType.get(), EventTypeDTO.class));
		}
		else {
			return Optional.empty();
		}
	}

	public Optional<List<EventTypeDTO>> list() {

		List<EventType> eventTypes = this.eventTypeRepository.findAll();

		if (eventTypes.size() > 0) {
			return Optional.of(eventTypes.stream().map(eventType -> modelMapper.map(eventType, EventTypeDTO.class)).toList());
		}
		else {
			return Optional.empty();
		}
	}

	public Optional<EventTypeDTO> update(Long id, EventType eventType) {

		Optional<EventType> existingEventType = this.eventTypeRepository.findById(id);

		if (existingEventType.isPresent()) {
			EventType updatedEventType = existingEventType.get();

			if (eventType.getName() != null && !eventType.getName().isEmpty()) {
				updatedEventType.setName(eventType.getName());
			}
			if (eventType.getDescription() != null && !eventType.getDescription().isEmpty()) {
				updatedEventType.setDescription(eventType.getDescription());
			}
			if (eventType.getColor() != null && !eventType.getColor().isEmpty()) {
				updatedEventType.setColor(eventType.getColor());
			}

			this.eventTypeRepository.save(updatedEventType);

			return Optional.of(modelMapper.map(updatedEventType, EventTypeDTO.class));
		}
		else {
			return Optional.empty();
		}
	}

	public boolean delete(Long id) {

		Optional<EventType> existingEventType = this.eventTypeRepository.findById(id);

		if (existingEventType.isPresent()) {
			this.eventTypeRepository.deleteById(id);

			return true;
		}
		else {
			return false;
		}
	}
	
}
