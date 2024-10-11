package com.media4all.timeline.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.media4all.timeline.dtos.UserDTO;
import com.media4all.timeline.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController implements IController<UserDTO> {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO user) {

		Optional<UserDTO> newUser = this.userService.create(user);

		if (newUser.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(newUser.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> get(@PathVariable Long id) {

		Optional<UserDTO> user = this.userService.get(id);

		if (user.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(user.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> list() {

		Optional<List<UserDTO>> users = this.userService.list();

		if (users.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(users.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO user) {

		Optional<UserDTO> updatedUser = this.userService.update(id, user);

		if (updatedUser.isPresent()) {
			return ResponseEntity.ok(updatedUser.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		boolean deleted = this.userService.delete(id);

		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
