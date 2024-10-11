package com.media4all.timeline.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IController<DTO, E> {

	ResponseEntity<DTO> create(E entity);
	ResponseEntity<DTO> get(Long id);
	ResponseEntity<List<DTO>> list();
	ResponseEntity<DTO> update(Long id, E entity);
	ResponseEntity<Void> delete(Long id);
}
