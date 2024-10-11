package com.media4all.timeline.entities;

import jakarta.persistence.*;

import lombok.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private String title;

	@NonNull
	private String description;

	@NonNull
	private String timestamp;

	@ManyToOne
	@JoinColumn(name = "type_id")
	@NonNull
	private EventType type;

	@NonNull
	private String icon;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@NonNull
	private User user;
}
