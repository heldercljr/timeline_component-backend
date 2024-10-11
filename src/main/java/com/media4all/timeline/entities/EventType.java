package com.media4all.timeline.entities;

import jakarta.persistence.*;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
public class EventType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private String color;
}
