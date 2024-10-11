package com.media4all.timeline.services;

import java.util.List;
import java.util.Optional;

public interface IService<DTO, E> {

	Optional<DTO> create(E entity);
	Optional<DTO> get(Long id);
	Optional<List<DTO>> list();
	Optional<DTO> update(Long id, E entity);
	boolean delete(Long id);
}
