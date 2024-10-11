package com.media4all.timeline.entities;

import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "user")
	private List<Event> events;
}
