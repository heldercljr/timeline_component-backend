package com.media4all.timeline.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.media4all.timeline.entities.Event;
import com.media4all.timeline.dtos.EventDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Event, EventDTO>() {

            protected void configure() {
                map().setTitle(source.getTitle());
                map().setDescription(source.getDescription());
                map().setTimestamp(source.getTimestamp());
                map().setType(source.getType().getName());
                map().setIcon(source.getIcon());
                map().setUser(source.getUser().getName());
            }
        });

        return modelMapper;
	}
}
