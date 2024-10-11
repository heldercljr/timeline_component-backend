package com.media4all.timeline.entities;

import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private String name;

	@OneToMany(mappedBy = "user")
	private List<Event> events;
}
