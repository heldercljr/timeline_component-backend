package com.media4all.timeline.entities.Event;

import com.media4all.timeline.entities.User.User;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	private String timestamp;

	private String icon;

	private String type;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
