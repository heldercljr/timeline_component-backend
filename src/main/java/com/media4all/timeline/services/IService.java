package com.media4all.timeline.services;

import java.util.List;
import java.util.Optional;

public interface IService<DTO> {

	Optional<DTO> create(DTO entity);
	Optional<DTO> get(Long id);
	Optional<List<DTO>> list();
	Optional<DTO> update(Long id, DTO entity);
	boolean delete(Long id);
}
