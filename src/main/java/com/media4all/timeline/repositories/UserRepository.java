package com.media4all.timeline.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.media4all.timeline.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByName(String name);
}
