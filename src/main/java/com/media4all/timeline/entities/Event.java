package com.media4all.timeline.entities;

import jakarta.persistence.*;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	private String timestamp;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private EventType type;

	private String icon;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
