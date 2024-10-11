package com.media4all.timeline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.media4all.timeline.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
