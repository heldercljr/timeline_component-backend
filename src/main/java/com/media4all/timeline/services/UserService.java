package com.media4all.timeline.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media4all.timeline.dtos.UserDTO;
import com.media4all.timeline.entities.User;
import com.media4all.timeline.repositories.UserRepository;

@Service
public class UserService implements IService<UserDTO> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Optional<UserDTO> create(UserDTO userDTO) {

		Optional<User> existingUser = this.userRepository.findByName(userDTO.getName());
	
		if (existingUser.isPresent()) {
			return Optional.empty();
		} 
		else {
			User newUser = this.userRepository.save(
				new User(
					userDTO.getName()
				)
			);
			
			return Optional.of(modelMapper.map(newUser, UserDTO.class));
		}
	}

	public Optional<UserDTO> get(Long id) {

		Optional<User> user = this.userRepository.findById(id);

		if (user.isPresent()) {
			return Optional.of(modelMapper.map(user.get(), UserDTO.class));
		}
		else {
			return Optional.empty();
		}
	}

	public Optional<List<UserDTO>> list() {

		List<User> users = this.userRepository.findAll();

		if (users.size() > 0) {
			return Optional.of(users.stream().map(user -> modelMapper.map(user, UserDTO.class)).toList());
		}
		else {
			return Optional.empty();
		}
	}

	public Optional<UserDTO> update(Long id, UserDTO userDTO) {

		Optional<User> existingUser = this.userRepository.findById(id);

		if (existingUser.isPresent()) {
			User updatedUser = existingUser.get();

			if (userDTO.getName() != null && !userDTO.getName().isEmpty()) {
				updatedUser.setName(userDTO.getName());
			}

			this.userRepository.save(updatedUser);
			
			return Optional.of(modelMapper.map(updatedUser, UserDTO.class));
		}
		else {
			return Optional.empty();
		}
	}

	public boolean delete(Long id) {

		Optional<User> existingUser = this.userRepository.findById(id);

		if (existingUser.isPresent()) {
			this.userRepository.deleteById(id);

			return true;
		}
		else {
			return false;
		}
	}
}
